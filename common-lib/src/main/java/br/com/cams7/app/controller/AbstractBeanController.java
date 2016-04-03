/**
 * 
 */
package br.com.cams7.app.controller;

import java.io.Serializable;
//import java.text.MessageFormat;
import java.util.List;
//import java.util.Locale;
import java.util.Map;
//import java.util.ResourceBundle;
import java.util.logging.Level;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.context.FacesContext;

import org.primefaces.context.RequestContext;
import org.primefaces.event.CloseEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import br.com.cams7.app.entity.AbstractEntity;
import br.com.cams7.app.service.BaseService;
import br.com.cams7.app.utils.AppException;
import br.com.cams7.app.utils.AppHelper;

/**
 * @author cesar
 *
 */
public abstract class AbstractBeanController<S extends BaseService<E>, E extends AbstractEntity>
		extends AbstractController<S, E> implements Serializable {

	private static final long serialVersionUID = 1L;

	// private final String RESOURCE_BUNDLE = "i18n.messages";
	protected final String PARAM_CHANGED = "changed";
	private final String PARAM_MESSAGE = "message";

	/**
	 * Mantém as entidades apresentadas na listagem.
	 */
	private LazyDataModel<E> lazyModel;

	/**
	 * Referência para a entidade que utilizada na seleção de um registro,
	 * inclusão (nova), edição e exclusão.
	 */
	private E selectedEntity;

	private final short PAGE_FIRST = 0;
	private final byte PAGE_SIZE = 10;

	private int totalRows;

	private short lastPageFirst;
	private byte lastPageSize;

	private String lastSortField;
	private SortOrder lastSortOrder;

	private Map<String, Object> lastFilters;

	private String[] globalFilters;

	public AbstractBeanController(String... globalFilters) {
		super();
		this.globalFilters = globalFilters;
	}

	/**
	 * Metodo chamado na criacao do componente
	 */
	@PostConstruct
	protected void init() {
		super.init();

		lastPageFirst = PAGE_FIRST;
		lastPageSize = PAGE_SIZE;

		lastSortOrder = SortOrder.UNSORTED;

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

				if (pageSize != lastPageSize) {
					lastPageSize = (byte) pageSize;
				} else // if ((first == 0 || first == lastPageFirst)) {
				if (sortField != null && (!sortField.equals(lastSortField) || !sortOrder.equals(lastSortOrder))) {
					setSort(sortField, sortOrder);
					// } else if (((!filters.isEmpty() || lastFilters !=
					// null)
					// && !AppHelper.equalMaps(filters, lastFilters))) {
				} else if (!AppHelper.equalMaps(filters, lastFilters)) {
					if (!filters.isEmpty())
						lastFilters = filters;
					else
						lastFilters = null;
				}
				// }

				lastPageFirst = (short) first;

				br.com.cams7.app.utils.SortOrder direction = br.com.cams7.app.utils.SortOrder.UNSORTED;

				switch (lastSortOrder) {
				case ASCENDING:
					direction = br.com.cams7.app.utils.SortOrder.ASCENDING;
					break;
				case DESCENDING:
					direction = br.com.cams7.app.utils.SortOrder.DESCENDING;
					break;
				default:
					break;
				}

				entities = search(lastPageFirst, lastPageSize, lastSortField, direction, lastFilters, globalFilters);

				if (lastFilters != null) {
					int totalRows = getTotalElements(lastFilters, globalFilters);
					setRowCount(totalRows);
				}

				return entities;
			}
		};

		reset();
	}

	/**
	 * Busca, pagina e ordena os dados das entidades
	 * 
	 * @param pageFirst
	 *            Indice
	 * @param pageSize
	 *            Total de linhas
	 * @param sortField
	 *            Nome do campo
	 * @param sortOrder
	 *            Tipo de ordenacao
	 * @param filters
	 *            filtros
	 * @return Entidades
	 */
	private List<E> search(int pageFirst, short pageSize, String sortField, br.com.cams7.app.utils.SortOrder sortOrder,
			Map<String, Object> filters, String... globalFilters) {
		return getService().search(pageFirst, pageSize, sortField, sortOrder, filters, globalFilters);
	}

	/**
	 * @param filters
	 * @param globalFilters
	 * @return
	 */
	private int getTotalElements(Map<String, Object> filters, String... globalFilters) {
		return getService().getTotalElements(filters, globalFilters);
	}

	/**
	 * 
	 * @param sortField
	 *            Nome do campo
	 * @param sortOrder
	 *            Tipo de ordenacao
	 */
	private void setSort(String sortField, SortOrder sortOrder) {
		lastSortField = sortField;
		lastSortOrder = sortOrder;
	}

	/**
	 * 
	 */
	private void setSort() {
		setSort(null, SortOrder.UNSORTED);
	}

	/**
	 * Operacao acionada toda a vez que a tela de listagem for carregada.
	 */
	public void reset() {
		totalRows = getService().count();
		lazyModel.setRowCount(totalRows);
	}

	/**
	 * Acao executada quando a pagina de inclusao da entidade for carregada.
	 */
	public void includeNewEntity() {
		try {
			setSelectedEntity(AppHelper.getNewEntity(getEntityType()));
			getLog().info("Nova entidade");
		} catch (AppException e) {
			getLog().log(Level.SEVERE, e.getMessage(), e);
		}
	}

	/**
	 * Operacao acionada pela tela de inclusao, atraves do
	 * <code>commandButton</code> <strong>Salva</strong>.
	 * 
	 * @return Se a inclusao foi realizada vai para listagem, senao permanece na
	 *         mesma tela.
	 */
	public String createEntity() {
		setSort();
		getService().salva(getSelectedEntity());
		getLog().info("Foi criada uma nova entidade");

		return getListPage();
	}

	/**
	 * Operacao acionada pela tela de edicao, atraves do
	 * <code>commandButton</code> <strong>Atualiza</strong>.
	 * 
	 * obs.: Se a alteracao for realizada vai para a listagem, senao permanece
	 * na mesma tela.
	 */
	public void updateEntity() {
		getService().atualiza(getSelectedEntity());

		RequestContext context = RequestContext.getCurrentInstance();
		context.addCallbackParam(PARAM_CHANGED, true);

		getLog().info(String.format("A entidade \"%s\" foi atualizada", getSelectedEntity()));
	}

	/**
	 * Operacao acionada pela tela de edicao, atraves do
	 * <code>commandButton</code> <strong>Exclui</strong>.
	 * 
	 * obs.: Se a exclusao for realizada vai para a listagem, senao permanece na
	 * mesma tela.
	 */
	public void removeEntity() {
		getService().remove(getSelectedEntity().getId());

		RequestContext context = RequestContext.getCurrentInstance();
		context.addCallbackParam(PARAM_CHANGED, true);

		reset();
		getLog().info(String.format("A entidade \"%s\" foi excluida", getSelectedEntity()));
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
	 * Metodo chamado para limpa a entidade selecionada
	 * 
	 * @param event
	 */
	public void handleClose(CloseEvent event) {
		setSelectedEntity(null);
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
		context.addCallbackParam(PARAM_MESSAGE, message);
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
	// protected String getMessageFromI18N(String key, Object... params) {
	// Locale locale =
	// FacesContext.getCurrentInstance().getViewRoot().getLocale();
	//
	// ResourceBundle bundle = ResourceBundle.getBundle(RESOURCE_BUNDLE, locale,
	// this.getClass().getClassLoader());
	//
	// String message;
	// if (params.length > 0)
	// message = MessageFormat.format(bundle.getString(key), params);
	// else
	// message = bundle.getString(key);
	//
	// return message;
	// }

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
	public short getFirst() {
		return lastPageFirst;
	}

	/**
	 * @return
	 */
	public byte getRows() {
		return lastPageSize;
	}

	/**
	 * @return the rowCount
	 */
	public int getTotalRows() {
		return totalRows;
	}

	protected abstract String getListPage();

}
