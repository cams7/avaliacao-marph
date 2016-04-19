/**
 * 
 */
package br.com.cams7.marph.controller.report;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.cams7.cw.controller.report.AbstractReportController;
import br.com.cams7.marph.entity.UsuarioEntity;
import br.com.cams7.marph.service.UsuarioService;

/**
 * @author cesar
 *
 */
@Controller
@RequestMapping("/usuario/report")
public class UsuarioReportController extends AbstractReportController<UsuarioService, UsuarioEntity> {

	private final String PDF_REPORT = "pdf_usuarios";
	private final String XLS_REPORT = "xls_usuarios";
	private final String CSV_REPORT = "csv_usuarios";
	private final String HTML_REPORT = "html_usuarios";

	/**
	 * Utiliza a injeção de dependência do <code>Spring Framework</code> para
	 * resolver a instância do <code>UsuarioService</code>.
	 */
	@Autowired
	private UsuarioService service;

	public UsuarioReportController() {
		super();
	}

	@Override
	protected UsuarioService getService() {
		return service;
	}

	@Override
	protected String getPdfReport() {
		return PDF_REPORT;
	}

	@Override
	protected String getXlsReport() {
		return XLS_REPORT;
	}

	@Override
	protected String getCsvReport() {
		return CSV_REPORT;
	}

	@Override
	protected String getHtmlReport() {
		return HTML_REPORT;
	}

}
