/**
 * 
 */
package br.com.cams7.crud.cw.controller.report;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.cams7.crud.cw.controller.report.AbstractReportController;
import br.com.cams7.crud.entity.UsuarioEntity;
import br.com.cams7.crud.service.UsuarioService;

/**
 * @author cesar
 *
 */
@Controller
@RequestMapping("/usuario/report")
public class UsuarioReportController extends AbstractReportController<UsuarioService, UsuarioEntity> {

	private final String PDF_VIEW = "usuarioPdfView";

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
	protected String getPdfView() {
		return PDF_VIEW;
	}

}
