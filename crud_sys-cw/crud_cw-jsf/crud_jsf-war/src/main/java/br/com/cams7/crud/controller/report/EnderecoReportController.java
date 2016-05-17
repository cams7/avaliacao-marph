/**
 * 
 */
package br.com.cams7.crud.controller.report;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.cams7.crud.entity.EnderecoEntity;
import br.com.cams7.crud.service.EnderecoService;
import br.com.cams7.cw.controller.report.AbstractReportController;

/**
 * @author cesar
 *
 */
@Controller
@RequestMapping("/endereco/report")
public class EnderecoReportController extends AbstractReportController<EnderecoService, EnderecoEntity> {

	private final String PDF_VIEW = "enderecoPdfView";

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
	protected String getPdfView() {
		return PDF_VIEW;
	}

}
