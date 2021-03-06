/**
 * 
 */
package br.com.cams7.crud.cw.controller.report;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.cams7.crud.cw.controller.report.AbstractReportController;
import br.com.cams7.crud.entity.PessoaEntity;
import br.com.cams7.crud.service.PessoaService;

/**
 * @author cesar
 *
 */
@Controller
@RequestMapping("/pessoa/report")
public class PessoaReportController extends AbstractReportController<PessoaService, PessoaEntity> {

	private final String PDF_VIEW = "pessoaPdfView";

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
	protected String getPdfView() {
		return PDF_VIEW;
	}

}
