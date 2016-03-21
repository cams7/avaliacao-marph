/**
 * 
 */
package br.com.cams7.marph.controller.bean;

import static br.com.cams7.marph.controller.bean.EnderecoBeanController.CONTROLLER_NAME;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.event.SelectEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import br.com.cams7.app.controller.AbstractBeanController;
import br.com.cams7.marph.entity.EnderecoEntity;
import br.com.cams7.marph.entity.PessoaEntity;
import br.com.cams7.marph.service.EnderecoService;
import br.com.cams7.marph.service.PessoaService;

/**
 * @author cesar
 *
 */

@Controller(CONTROLLER_NAME)
@ManagedBean(name = CONTROLLER_NAME)
@ViewScoped
public class EnderecoBeanController extends AbstractBeanController<EnderecoService, EnderecoEntity> {

	private static final long serialVersionUID = 1L;

	public final static String CONTROLLER_NAME = "enderecoMB";

	private final String LIST_PAGE = "listaEnderecos";

	@Autowired
	private PessoaService pessoaService;

	public EnderecoBeanController() {
		super();
	}

	@Override
	public String createEntity() {
		String listPage = super.createEntity();

		addINFOMessage("O endereco foi cadastrado com sucesso!!!",
				String.format("O endereco no qual bairro e rua sao \"%s e %s\" respectivamente foi salvo.",
						getSelectedEntity().getBairro(), getSelectedEntity().getRua()));

		return listPage;
	}

	@Override
	public void updateEntity() {
		super.updateEntity();

		addINFOMessage("O endereco foi atualizado com sucesso!!!",
				String.format("O endereco no qual bairro e rua sao \"%s e %s\" respectivamente foi atualizado.",
						getSelectedEntity().getBairro(), getSelectedEntity().getRua()));
	}

	@Override
	public void removeEntity() {
		super.removeEntity();

		addINFOMessage("O endereco foi removido com sucesso!!!",
				String.format("O endereco no qual bairro e rua sao \"%s e %s\" respectivamente foi removido.",
						getSelectedEntity().getBairro(), getSelectedEntity().getRua()));
	}

	public List<PessoaEntity> buscaPessoas(String query) {
		List<PessoaEntity> pessoas = pessoaService.buscaPeloNome(query);
		return pessoas;
	}

	public void onItemSelect(SelectEvent event) {
		addINFOMessage("Pessoa selecionada!!!", String.format("A pessoa cujo id e \"%s\" foi selecionada.",
				((PessoaEntity) event.getObject()).getId()));
	}

	@Override
	protected String getListPage() {
		return LIST_PAGE;
	}

}
