/**
 * 
 */
package br.com.cams7.cw.controller.report;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.cams7.sys.SearchParams;
import br.com.cams7.sys.controller.AbstractController;
import br.com.cams7.sys.entity.AbstractEntity;
import br.com.cams7.sys.service.BaseService;
import br.com.cams7.sys.utils.URIHelper;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRParameter;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

/**
 * @author cesar
 *
 */
public abstract class AbstractReportController<S extends BaseService<E>, E extends AbstractEntity>
		extends AbstractController<S, E> {

	public AbstractReportController() {
		super();
	}

	private Map<String, Object> getReportParams(SearchParams params) {
		Map<String, Object> reportParams = new HashMap<String, Object>();

		List<E> entities = getService().search(params);

		if (entities.isEmpty())
			return null;

		JRDataSource JRdataSource = new JRBeanCollectionDataSource(entities);

		reportParams.put(JRParameter.REPORT_LOCALE, new Locale("pt", "BR"));
		reportParams.put("datasource", JRdataSource);
		return reportParams;
	}

	private ModelAndView getModelAndView(Map<String, Object> reportParams, String reportName) {
		if (reportParams == null)
			return new ModelAndView();

		return new ModelAndView(reportName, reportParams);
	}

	/**
	 * Gera relatório PDF
	 * 
	 * @URL: http://localhost:8080/avaliacao_marph/req/pessoa/report/pdf?
	 *       page_first=
	 *       0&page_size=15&sort_field=nascimento&sort_order=DESCENDING&
	 *       filter_field=nome&filter_field=cpf&globalFilter=m&nome=a&cpf=6
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/pdf", method = GET)
	public ModelAndView generatePdfReport(HttpServletRequest request) {
		SearchParams params = URIHelper.getParams(getEntityType(), request.getParameterMap());
		// pdfReport foi declarado no arquivo jasper-views.xml
		return getModelAndView(getReportParams(params), getPdfView());
	}

	protected abstract String getPdfView();

}
