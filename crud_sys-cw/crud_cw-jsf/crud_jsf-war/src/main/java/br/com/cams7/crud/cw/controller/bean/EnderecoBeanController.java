/**
 * 
 */
package br.com.cams7.crud.cw.controller.bean;

import static br.com.cams7.crud.cw.controller.bean.EnderecoBeanController.CONTROLLER_NAME;
import static br.com.cams7.sys.controller.AbstractBeanController.CONTROLLER_SCOPE;

import java.util.List;

import javax.faces.event.ActionEvent;

import org.primefaces.event.SelectEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.com.cams7.crud.entity.EnderecoEntity;
import br.com.cams7.crud.entity.PessoaEntity;
import br.com.cams7.crud.service.EnderecoService;
import br.com.cams7.crud.service.PessoaService;
import br.com.cams7.sys.controller.AbstractBeanController;

/**
 * @author cesar
 *
 */

@Controller(CONTROLLER_NAME)
@Scope(CONTROLLER_SCOPE)
public class EnderecoBeanController extends AbstractBeanController<EnderecoService, EnderecoEntity> {

	private static final long serialVersionUID = 1L;

	public final static String CONTROLLER_NAME = "enderecoMB";

	private final String LIST_PAGE = "listaEnderecos";

	/**
	 * Utiliza a injeção de dependência do <code>Spring Framework</code> para
	 * resolver a instância do <code>EnderecoService</code>.
	 */
	@Autowired
	private EnderecoService service;

	/**
	 * Utiliza a injeção de dependência do <code>Spring Framework</code> para
	 * resolver a instância do <code>PessoaService</code>.
	 */
	@Autowired
	private PessoaService pessoaService;

	public EnderecoBeanController() {
		super("bairro", "rua", "telefone", "pessoa.nome");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see br.com.cams7.app.controller.AbstractBeanController#createEntity()
	 */
	@Override
	public String createEntity() {
		String listPage = super.createEntity();

		// addINFOMessage(getMessageFromI18N("msg.ok.summary.salva.endereco"),
		// getMessageFromI18N(
		// "msg.ok.detail.salva.endereco", getSelectedEntity().getBairro(),
		// getSelectedEntity().getRua()));

		return listPage;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see br.com.cams7.app.controller.AbstractBeanController#updateEntity()
	 */
	@Override
	public void updateEntity(ActionEvent event) {
		super.updateEntity(event);

		addINFOMessage(getMessageFromI18N("msg.ok.summary.atualiza.endereco"), getMessageFromI18N(
				"msg.ok.detail.atualiza.endereco", getSelectedEntity().getBairro(), getSelectedEntity().getRua()));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see br.com.cams7.app.controller.AbstractBeanController#removeEntity()
	 */
	@Override
	public void removeEntity(ActionEvent event) {
		super.removeEntity(event);

		addINFOMessage(getMessageFromI18N("msg.ok.summary.remove.endereco"), getMessageFromI18N(
				"msg.ok.detail.remove.endereco", getSelectedEntity().getBairro(), getSelectedEntity().getRua()));
	}

	/**
	 * Busca pessoas pelo nome
	 * 
	 * @param nome
	 * @return Pessoas
	 */
	public List<PessoaEntity> buscaPessoas(String nome) {
		List<PessoaEntity> pessoas = pessoaService.getPessoaPeloNome(nome);
		return pessoas;
	}

	/**
	 * Exibe uma mensagem
	 * 
	 * @param event
	 */
	public void onItemSelect(SelectEvent event) {
		PessoaEntity pessoa = (PessoaEntity) event.getObject();
		pessoa = pessoaService.getEntityById(pessoa.getId());
		getSelectedEntity().setPessoa(pessoa);

		addINFOMessage(getMessageFromI18N("msg.ok.summary.pessoa.selecionada"),
				getMessageFromI18N("msg.ok.detail.pessoa.selecionada", pessoa.getNome()));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see br.com.cams7.app.controller.AbstractController#getService()
	 */
	@Override
	protected EnderecoService getService() {
		return service;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see br.com.cams7.app.controller.AbstractBeanController#getListPage()
	 */
	@Override
	protected String getListPage() {
		return LIST_PAGE;
	}

}
