/**
 * 
 */
package br.com.cams7.marph.controller.rest;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import br.com.cams7.as.controller.AbstractRestController;
import br.com.cams7.marph.entity.PessoaEntity;
import br.com.cams7.marph.service.PessoaService;

/**
 * @author cesar
 *
 */
@Path("/pessoa")
@Produces(APPLICATION_JSON)
@RequestScoped
public class PessoaRestController extends AbstractRestController<PessoaService, PessoaEntity> {

	public PessoaRestController() {
		super();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * br.com.cams7.app.controller.AbstractController#setService(br.com.cams7.
	 * app.service.BaseService)
	 */
	@EJB
	@Override
	protected void setService(PessoaService service) {
		super.setService(service);
	}
}
