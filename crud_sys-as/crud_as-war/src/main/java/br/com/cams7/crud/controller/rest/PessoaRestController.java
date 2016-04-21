/**
 * 
 */
package br.com.cams7.crud.controller.rest;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import br.com.cams7.as.controller.rest.AbstractRestController;
import br.com.cams7.crud.entity.PessoaEntity;
import br.com.cams7.crud.service.PessoaService;

/**
 * @author cesar
 *
 */
@Path("/pessoa")
@Produces(APPLICATION_JSON)
@RequestScoped
public class PessoaRestController extends AbstractRestController<PessoaService, PessoaEntity> {

	@EJB
	private PessoaService service;

	public PessoaRestController() {
		super();
	}

	/* (non-Javadoc)
	 * @see br.com.cams7.app.controller.AbstractController#getService()
	 */
	@Override
	protected PessoaService getService() {
		return service;
	}

}
