/**
 * 
 */
package br.com.cams7.sys.controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.text.MessageFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.logging.Level;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.FacesEvent;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.primefaces.context.RequestContext;
import org.primefaces.event.CloseEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.json.JSONException;
import org.primefaces.json.JSONObject;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import br.com.cams7.sys.ReportView;
import br.com.cams7.sys.ReportView.IntervalPages;
import br.com.cams7.sys.ReportView.Pagination;
import br.com.cams7.sys.SearchParams;
import br.com.cams7.sys.entity.AbstractEntity;
import br.com.cams7.sys.service.BaseService;
import br.com.cams7.sys.utils.AppException;
import br.com.cams7.sys.utils.AppHelper;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRParameter;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

/**
 * @author cesar
 *
 */
public abstract class AbstractBeanController<S extends BaseService<E>, E extends AbstractEntity>
		extends AbstractController<S, E> implements Serializable {

	private static final long serialVersionUID = 1L;

	private final String RESOURCE_BUNDLE = "i18n.messages";
	protected final String PARAM_CHANGED = "entityChanged";
	private final String PARAM_MESSAGE = "messageAfterAction";

	public final static String CONTROLLER_SCOPE = "view";

	/**
	 * Mantém as entidades apresentadas na listagem.
	 */
	private LazyDataModel<E> lazyModel;

	/**
	 * Referência para a entidade que utilizada na seleção de um registro,
	 * inclusão (nova), edição e exclusão.
	 */
	private E selectedEntity;
	private boolean entityRemoved = false;

	private final int FIRST_PAGE = 0;
	private final short SIZE_PAGE = 10;

	private int totalRows;

	private int lastFirstPage;
	private short lastSizePage;

	private String lastSortField;
	private SortOrder lastSortOrder;

	private Map<String, Object> lastFilters;

	private String[] globalFilters;

	private ReportView report;

	// private int countPreRenderComponent;
	// private int countPreRenderView;
	// private int countPostAddToView;
	// private int countPreValidate;
	// private int countPostValidate;

	public AbstractBeanController(String... globalFilters) {
		super();
		this.globalFilters = globalFilters;
	}

	/**
	 * Método chamado na criação do componente
	 */
	@PostConstruct
	protected void initialize() {
		super.initialize();

		lastFirstPage = FIRST_PAGE;
		lastSizePage = SIZE_PAGE;

		lastSortOrder = SortOrder.UNSORTED;
		lastFilters = new HashMap<>();

		report = new ReportView();
		report.setInterval(IntervalPages.CURRENT_PAGE);

		lazyModel = new LazyDataModel<E>() {

			private static final long serialVersionUID = 1L;

			private List<E> entities;

			@Override
			public E getRowData(String rowKey) {
				for (E entity : entities) {
					if (String.valueOf(entity.getId()).equals(rowKey))
						return entity;
				}

				return null;
			}

			@Override
			public Object getRowKey(E entity) {
				return String.valueOf(entity.getId());
			}

			@Override
			public List<E> load(int first, int pageSize, String sortField, SortOrder sortOrder,
					Map<String, Object> filters) {
				filters = AppHelper.removeEmptyValue(filters);

				boolean totalRowsChanged = false;

				if (pageSize != lastSizePage)
					lastSizePage = (short) pageSize;
				else if (sortField != null && !(sortField.equals(lastSortField) && sortOrder.equals(lastSortOrder))) {
					lastSortField = sortField;
					lastSortOrder = sortOrder;
				} else if (!AppHelper.equalMaps(filters, lastFilters)) {
					if (!filters.isEmpty())
						lastFilters = filters;
					else
						lastFilters = new HashMap<>();

					totalRowsChanged = true;
				}

				lastFirstPage = first;

				SearchParams params = new SearchParams(lastFirstPage, lastSizePage, lastSortField,
						getSortOrder(lastSortOrder), lastFilters, globalFilters);
				entities = getService().search(params);

				int totalRows = getRowCount();
				if (totalRowsChanged || entityRemoved) {
					totalRows = getService().getTotalElements(lastFilters, globalFilters);
					setRowCount(totalRows);
					entityRemoved = false;
				}

				int firstPage = getPage(lastFirstPage) + 1;
				int totalPages = getPage(totalRows);

				report.setFirstPage(firstPage);
				report.setLastPage(firstPage);
				report.setTotalPages(totalPages);

				return entities;
			}

		};
		getLog().log(Level.INFO, "Componet initialized");
	}

	private br.com.cams7.sys.SearchParams.SortOrder getSortOrder(SortOrder sortOrder) {
		if (sortOrder == null)
			return br.com.cams7.sys.SearchParams.SortOrder.UNSORTED;

		return br.com.cams7.sys.SearchParams.SortOrder.valueOf(sortOrder.name());
	}

	private int getPage(int page) {
		return page % lastSizePage == 0 ? page / lastSizePage : page / lastSizePage + 1;
	}

	private boolean isPostback() {
		FacesContext context = FacesContext.getCurrentInstance();
		return context.isPostback();
	}

	/**
	 * Operação acionada toda a vez que a tela de listagem for carregada.
	 */
	public void count() {
		if (isPostback())
			return;

		totalRows = getService().count();
		lazyModel.setRowCount(totalRows);
	}

	/**
	 * Acao executada quando a página de inclusão da entidade for carregada.
	 */
	public void includeNewEntity() {
		if (isPostback())
			return;

		try {
			setSelectedEntity(AppHelper.getNewEntity(getEntityType()));
			getLog().info("Nova entidade");
		} catch (AppException e) {
			getLog().log(Level.SEVERE, e.getMessage(), e);
		}
	}

	/**
	 * Operacao acionada pela tela de inclusão, através do
	 * <code>commandButton</code> <strong>Salva</strong>.
	 * 
	 * @return Se a inclusão for realizada, irá para listagem, senão permanece
	 *         na mesma tela.
	 */
	public String createEntity() {
		getService().save(getSelectedEntity());
		getLog().info("Foi criada uma nova entidade");

		return getListPage();
	}

	/**
	 * Operação acionada pela tela de edição, através do
	 * <code>commandButton</code> <strong>Atualiza</strong>.
	 * 
	 * obs.: Se a alteração for realizada, irá para a listagem, senão permanece
	 * na mesma tela.
	 */
	public void updateEntity(ActionEvent event) {
		RequestContext context = RequestContext.getCurrentInstance();

		getService().update(getSelectedEntity());

		context.addCallbackParam(PARAM_CHANGED, true);
		getLog().info(String.format("A entidade \"%s\" foi atualizada", getSelectedEntity()));
	}

	/**
	 * Operação acionada pela tela de edição, através do
	 * <code>commandButton</code> <strong>Exclui</strong>.
	 * 
	 * obs.: Se a exclusão for realizada, irá para a listagem, senão permanece
	 * na mesma tela.
	 */
	public void removeEntity(ActionEvent event) {
		getService().remove(getSelectedEntity().getId());

		RequestContext context = RequestContext.getCurrentInstance();
		context.addCallbackParam(PARAM_CHANGED, true);

		entityRemoved = true;
		getLog().info(String.format("A entidade \"%s\" foi excluida", getSelectedEntity()));
	}

	public void generatePdfReport(ActionEvent event) {
		Pagination pagination = report.getPagination(lastFirstPage, lastSizePage);

		SearchParams searchParams = new SearchParams(pagination.getFirstPage(), pagination.getSizePage(), lastSortField,
				getSortOrder(lastSortOrder), lastFilters, globalFilters);
		List<E> entities = getService().search(searchParams);

		FacesContext context = FacesContext.getCurrentInstance();

		String viewId = context.getViewRoot().getViewId();
		String page = viewId.substring(viewId.indexOf("/") + 1, viewId.lastIndexOf("/"));

		final String REPORT_TYPE = "jasper";
		String fileName = "relatorio_" + page + "." + REPORT_TYPE;
		String pdfName = fileName.replaceFirst("." + REPORT_TYPE, ".pdf");

		ExternalContext externalContext = context.getExternalContext();
		HttpServletResponse response = (HttpServletResponse) externalContext.getResponse();
		response.addHeader("Content-disposition", "attachment; filename=" + pdfName);

		ClassLoader classLoader = this.getClass().getClassLoader();
		InputStream reportStream = classLoader.getResourceAsStream("/META-INF/report/" + fileName);

		Map<String, Object> params = new HashMap<String, Object>();
		params.put(JRParameter.REPORT_LOCALE, new Locale("pt", "BR"));

		try {
			JasperPrint jasperPrint = JasperFillManager.fillReport(reportStream, params,
					new JRBeanCollectionDataSource(entities));

			ServletOutputStream os = response.getOutputStream();
			try {
				JasperExportManager.exportReportToPdfStream(jasperPrint, os);
			} finally {
				os.flush();
				os.close();
			}
		} catch (JRException | IOException e) {
			getLog().log(Level.SEVERE, e.getMessage());
		} finally {
			context.responseComplete();
		}

	}

	/**
	 * Seleciona a entidade
	 * 
	 * @param event
	 */
	public void onRowSelect(SelectEvent event) {
		@SuppressWarnings("unchecked")
		E selectedEntity = (E) event.getObject();
		setSelectedEntity(selectedEntity);

		getLog().info(String.format("A entidade \"%s\" foi selecionada", getSelectedEntity()));
	}

	/**
	 * Limpa os campos do formulário
	 * 
	 * @param children
	 */
	private void resetInputValues(List<UIComponent> children) {
		for (UIComponent component : children)
			if (component instanceof UIInput) {
				UIInput input = (UIInput) component;
				input.resetValue();
				input.setValid(true);
			} else if (component.getChildCount() > 0)
				resetInputValues(component.getChildren());
	}

	/**
	 * Limpa a entidade selecionada e os campos do fomúlario
	 * 
	 * @param event
	 */
	private void resetEntityAndInputValues(FacesEvent event) {
		setSelectedEntity(null);
		resetInputValues(event.getComponent().getChildren());
	}

	/**
	 * Método chamado para limpa a entidade selecionada e o formulário
	 * 
	 * @param event
	 */
	public void handleClose(CloseEvent event) {
		resetEntityAndInputValues(event);
	}

	/**
	 * Operação acionada pela tela de edição, através do
	 * <code>commandButton</code> <strong>Cancela</strong>.
	 * 
	 * @param event
	 */
	public void handleClose(ActionEvent event) {
		resetEntityAndInputValues(event);
	}

	/**
	 * Cria um objeto JSON
	 * 
	 * @param message
	 * @return
	 */
	private JSONObject getMessage(FacesMessage message) {
		try {
			JSONObject object = new JSONObject();
			object.put("summary", message.getSummary());
			object.put("detail", message.getDetail());
			object.put("severity", MessageSeverity.values()[message.getSeverity().getOrdinal()].name().toLowerCase());
			return object;
		} catch (JSONException e) {
			getLog().log(Level.SEVERE, e.getMessage(), e);
		}
		return null;
	}

	/**
	 * @param severity
	 * @param summary
	 * @param detail
	 */
	private void addMessage(Severity severity, String summary, String detail) {
		FacesMessage message = new FacesMessage(severity, summary, detail);
		FacesContext.getCurrentInstance().addMessage(null, message);

		RequestContext context = RequestContext.getCurrentInstance();
		context.addCallbackParam(PARAM_MESSAGE, getMessage(message));
	}

	/**
	 * @param summary
	 * @param detail
	 */
	protected void addINFOMessage(String summary, String detail) {
		addMessage(FacesMessage.SEVERITY_INFO, summary, detail);
		getLog().info(detail);
	}

	/**
	 * @param summary
	 * @param detail
	 */
	protected void addWARNMessage(String summary, String detail) {
		addMessage(FacesMessage.SEVERITY_WARN, summary, detail);
		getLog().log(Level.WARNING, detail);
	}

	/**
	 * @param summary
	 * @param detail
	 */
	protected void addERRORMessage(String summary, String detail) {
		addMessage(FacesMessage.SEVERITY_ERROR, summary, detail);
		getLog().log(Level.SEVERE, detail);
	}

	/**
	 * @param summary
	 * @param detail
	 */
	protected void addFATALMessage(String summary, String detail) {
		addMessage(FacesMessage.SEVERITY_FATAL, summary, detail);
		getLog().log(Level.SEVERE, detail);
	}

	/**
	 * @param key
	 * @return Recupera a mensagem do arquivo properties
	 *         <code>ResourceBundle</code>.
	 */
	protected String getMessageFromI18N(String key, Object... params) {
		Locale locale = FacesContext.getCurrentInstance().getViewRoot().getLocale();

		ClassLoader classLoader = this.getClass().getClassLoader();
		ResourceBundle bundle = ResourceBundle.getBundle(RESOURCE_BUNDLE, locale, classLoader);

		String message;
		if (params.length > 0)
			message = MessageFormat.format(bundle.getString(key), params);
		else
			message = bundle.getString(key);

		return message;
	}

	public LazyDataModel<E> getLazyModel() {
		return lazyModel;
	}

	public E getSelectedEntity() {
		return selectedEntity;
	}

	public void setSelectedEntity(E selectedEntity) {
		this.selectedEntity = selectedEntity;
	}

	/**
	 * @return the first
	 */
	public int getFirst() {
		return lastFirstPage;
	}

	/**
	 * @return
	 */
	public short getRows() {
		return lastSizePage;
	}

	/**
	 * @return the rowCount
	 */
	public int getTotalRows() {
		return totalRows;
	}

	public ReportView getReport() {
		return report;
	}

	/**
	 * @return
	 */
	public IntervalPages[] getIntervals() {
		return IntervalPages.values();
	}

	protected abstract String getListPage();

	private enum MessageSeverity {
		INFO, WARN, ERROR, FATAL
	}

	/**
	 * 1 - postAddToView: runs right after the component is added to view during
	 * view build time (which is usually during restore view phase, but can also
	 * be during render response phase, e.g. navigation).
	 */
	// public void postAddToView() {
	// getLog().log(Level.INFO, String.format("invoke postAddToView(): %s",
	// countPostAddToView));
	// countPostAddToView++;
	// }

	/**
	 * 2 - preRenderView: runs right before the view is rendered during render
	 * response phase.
	 */
	// public void preRenderView() {
	// getLog().log(Level.INFO, String.format("invoke preRenderView(): %s",
	// countPreRenderView));
	// countPreRenderView++;
	// }

	/**
	 * 3 - preRenderComponent: runs right before the component is rendered
	 * during render response phase.
	 */
	// public void preRenderComponent() {
	// getLog().log(Level.INFO, String.format("invoke preRenderComponent(): %s",
	// countPreRenderComponent));
	// countPreRenderComponent++;
	// }

	/**
	 * 4 - preValidate: runs right before the component is to be validated
	 * (which is usually during validations phase, but can also be apply request
	 * values phase if immediate="true").
	 */
	// public void preValidate(ComponentSystemEvent event) {
	// getLog().log(Level.INFO, String.format("invoke preValidate(%s): %s",
	// event, countPreValidate));
	// countPreValidate++;
	// }

	/**
	 * 5 - postValidate: runs right after the component is been validated (which
	 * is usually during validations phase, but can also be apply request values
	 * phase if immediate="true").
	 */
	// public void postValidate(ComponentSystemEvent event) {
	// getLog().log(Level.INFO, String.format("invoke postValidate(%s): %s",
	// event, countPostValidate));
	// countPostValidate++;
	// }

}
