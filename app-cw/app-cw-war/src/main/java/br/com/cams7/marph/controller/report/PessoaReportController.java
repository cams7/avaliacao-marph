/**
 * 
 */
package br.com.cams7.marph.controller.report;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.cams7.cw.controller.report.AbstractReportController;
import br.com.cams7.marph.entity.PessoaEntity;
import br.com.cams7.marph.service.PessoaService;

/**
 * @author cesar
 *
 */
@Controller
@RequestMapping("/pessoa/report")
public class PessoaReportController extends AbstractReportController<PessoaService, PessoaEntity> {

	private final String PDF_REPORT = "pdf_pessoas";
	private final String XLS_REPORT = "xls_pessoas";
	private final String CSV_REPORT = "csv_pessoas";
	private final String HTML_REPORT = "html_pessoas";

	/**
	 * Utiliza a injeção de dependência do <code>Spring Framework</code> para
	 * resolver a instância do <code>PessoaService</code>.
	 */
	@Autowired
	private PessoaService service;

	public PessoaReportController() {
		super();
	}

	@Override
	protected PessoaService getService() {
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
