/**
 * 
 */
package br.com.cams7.marph.controller.bean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.springframework.stereotype.Controller;

import br.com.cams7.app.controller.AbstractBeanController;
import br.com.cams7.marph.entity.PessoaEntity;
import br.com.cams7.marph.service.PessoaService;

/**
 * @author cesar
 *
 */
@Controller(PessoaBeanController.CONTROLLER_NAME)
@ManagedBean(name = PessoaBeanController.CONTROLLER_NAME)
@ViewScoped
public class PessoaBeanController extends AbstractBeanController<PessoaService, PessoaEntity> {

	private static final long serialVersionUID = 1L;

	public final static String CONTROLLER_NAME = "pessoaMB";

	private final String LIST_PAGE = "listaPessoas";

	public PessoaBeanController() {
		super();
	}

	@Override
	public String createEntity() {
		String listPage = super.createEntity();

		addINFOMessage("Os dados pessoais foram cadastrados com sucesso!!!",
				String.format("Os dados pessoais de \"%s\" foram salvos.", getSelectedEntity().getNome()));

		return listPage;
	}

	@Override
	public void updateEntity() {
		super.updateEntity();

		addINFOMessage("Os dados pessoais foram atualizados com sucesso!!!",
				String.format("Os dados pessoais de \"%s\" foram atualizados.", getSelectedEntity().getNome()));
	}

	@Override
	public void removeEntity() {
		super.removeEntity();

		addINFOMessage("Os dados pessoais foram removidos com sucesso!!!",
				String.format("Os dados pessoais de \"%s\" foram removidos.", getSelectedEntity().getNome()));
	}

	@Override
	protected String getListPage() {
		return LIST_PAGE;
	}

}
