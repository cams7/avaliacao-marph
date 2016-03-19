/**
 * 
 */
package br.com.cams7.marph.controller.report;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import br.com.cams7.marph.entity.UsuarioEntity;
import br.com.cams7.marph.service.UsuarioService;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRParameter;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

/**
 * @author cesar
 *
 */
@Controller
@RequestMapping("/relatorio/")
public class ReportController {

	private static final Logger LOG = Logger.getLogger(ReportController.class.getName());

	public ReportController() {
		super();
	}

	private Map<String, Object> getParameterMap() {
		Map<String, Object> parameterMap = new HashMap<String, Object>();

		List<UsuarioEntity> usuarios = service.buscaTodosDadosPessoais();

		JRDataSource JRdataSource = new JRBeanCollectionDataSource(usuarios);

		parameterMap.put(JRParameter.REPORT_LOCALE, new Locale("pt", "BR"));
		parameterMap.put("datasource", JRdataSource);
		return parameterMap;
	}

	@Autowired
	private UsuarioService service;

	@RequestMapping(method = RequestMethod.GET, value = "pdf")
	public ModelAndView generatePdfReport(ModelAndView modelAndView) {

		LOG.log(Level.INFO, "--------------generate PDF report----------");

		Map<String, Object> parameterMap = getParameterMap();

		// pdfReport bean has ben declared in the jasper-views.xml file
		modelAndView = new ModelAndView("pdfReport", parameterMap);

		return modelAndView;

	}// generatePdfReport

	@RequestMapping(method = RequestMethod.GET, value = "xls")
	public ModelAndView generateXlsReport(ModelAndView modelAndView) {

		LOG.log(Level.INFO, "--------------generate XLS report----------");

		Map<String, Object> parameterMap = getParameterMap();

		// xlsReport bean has ben declared in the jasper-views.xml file
		modelAndView = new ModelAndView("xlsReport", parameterMap);

		return modelAndView;

	}// generatePdfReport

	@RequestMapping(method = RequestMethod.GET, value = "csv")
	public ModelAndView generateCsvReport(ModelAndView modelAndView) {

		LOG.log(Level.INFO, "--------------generate CSV report----------");

		Map<String, Object> parameterMap = getParameterMap();

		// xlsReport bean has ben declared in the jasper-views.xml file
		modelAndView = new ModelAndView("csvReport", parameterMap);

		return modelAndView;

	}// generatePdfReport

	@RequestMapping(method = RequestMethod.GET, value = "html")
	public ModelAndView generateHtmlReport(ModelAndView modelAndView) {

		LOG.log(Level.INFO, "--------------generate HTML report----------");

		Map<String, Object> parameterMap = getParameterMap();

		// xlsReport bean has ben declared in the jasper-views.xml file
		modelAndView = new ModelAndView("htmlReport", parameterMap);

		return modelAndView;

	}// generatePdfReport

}// ReportController
