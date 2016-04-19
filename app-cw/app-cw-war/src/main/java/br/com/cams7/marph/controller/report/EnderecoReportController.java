/**
 * 
 */
package br.com.cams7.marph.controller.report;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.cams7.cw.controller.report.AbstractReportController;
import br.com.cams7.marph.entity.EnderecoEntity;
import br.com.cams7.marph.service.EnderecoService;

/**
 * @author cesar
 *
 */
@Controller
@RequestMapping("/endereco/report")
public class EnderecoReportController extends AbstractReportController<EnderecoService, EnderecoEntity> {

	private final String PDF_REPORT = "pdf_enderecos";
	private final String XLS_REPORT = "xls_enderecos";
	private final String CSV_REPORT = "csv_enderecos";
	private final String HTML_REPORT = "html_enderecos";

	/**
	 * Utiliza a injeção de dependência do <code>Spring Framework</code> para
	 * resolver a instância do <code>EnderecoService</code>.
	 */
	@Autowired
	private EnderecoService service;

	public EnderecoReportController() {
		super();
	}

	@Override
	protected EnderecoService getService() {
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
