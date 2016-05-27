/**
 * 
 */
package br.com.cams7.crud.controller.rest;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import br.com.cams7.crud.as.controller.rest.AbstractRestController;
import br.com.cams7.crud.entity.EnderecoEntity;
import br.com.cams7.crud.service.EnderecoService;

/**
 * @author cesar
 *
 */
@Path("/endereco")
@Produces(APPLICATION_JSON)
@RequestScoped
public class EnderecoRestController extends AbstractRestController<EnderecoService, EnderecoEntity> {

	@EJB
	private EnderecoService service;

	public EnderecoRestController() {
		super();
	}

	/* (non-Javadoc)
	 * @see br.com.cams7.app.controller.AbstractController#getService()
	 */
	@Override
	protected EnderecoService getService() {
		return service;
	}

}
