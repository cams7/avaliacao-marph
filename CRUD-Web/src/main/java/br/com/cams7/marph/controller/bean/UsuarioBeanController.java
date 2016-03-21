/**
 * 
 */
package br.com.cams7.marph.controller.bean;

import static br.com.cams7.marph.controller.bean.UsuarioBeanController.CONTROLLER_NAME;
import static javax.faces.application.FacesMessage.SEVERITY_ERROR;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.event.ComponentSystemEvent;

import org.primefaces.event.SelectEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import br.com.cams7.app.controller.AbstractBeanController;
import br.com.cams7.marph.entity.PessoaEntity;
import br.com.cams7.marph.entity.UsuarioEntity;
import br.com.cams7.marph.entity.UsuarioEntity.Autorizacao;
import br.com.cams7.marph.service.PessoaService;
import br.com.cams7.marph.service.UsuarioService;

/**
 * @author cesar
 *
 */
@Controller(CONTROLLER_NAME)
@ManagedBean(name = CONTROLLER_NAME)
@ViewScoped
public class UsuarioBeanController extends AbstractBeanController<UsuarioService, UsuarioEntity> {

	private static final long serialVersionUID = 1L;

	public final static String CONTROLLER_NAME = "usuarioMB";

	private final String LIST_PAGE = "listaUsuarios";

	@Autowired
	private PessoaService pessoaService;

	public UsuarioBeanController() {
		super();
	}

	@Override
	public String createEntity() {
		String listPage = super.createEntity();

		addINFOMessage("Os dados de usuario foram cadastrados com sucesso!!!",
				String.format("Os dados do usuario \"%s\" foram salvos.", getSelectedEntity().getLogin()));

		return listPage;
	}

	@Override
	public void updateEntity() {
		super.updateEntity();

		addINFOMessage("Os dados de usuario foram atualizados com sucesso!!!",
				String.format("Os dados do usuario \"%s\" foram atualizados.", getSelectedEntity().getLogin()));
	}

	@Override
	public void removeEntity() {
		super.removeEntity();

		addINFOMessage("O usuario foi removido com sucesso!!!",
				String.format("O usuario \"%s\" foi removido.", getSelectedEntity().getLogin()));
	}

	public List<PessoaEntity> buscaPessoas(String query) {
		List<PessoaEntity> pessoas = pessoaService.buscaPessoasSemUsuarioPeloNome(query);
		return pessoas;
	}

	public void onItemSelect(SelectEvent event) {
		addINFOMessage("Pessoa selecionada!!!", String.format("A pessoa cujo id e \"%s\" foi selecionada.",
				((PessoaEntity) event.getObject()).getId()));
	}

	public void validaSenha(ComponentSystemEvent event) {
		FacesContext context = FacesContext.getCurrentInstance();

		UIComponent component = event.getComponent();

		// get password
		UIInput uiInputPassword = (UIInput) component.findComponent("usuarioSenha");
		String password = uiInputPassword.getLocalValue() == null ? ""
				: uiInputPassword.getLocalValue().toString().trim();
		String passwordId = uiInputPassword.getClientId();

		// get confirm password
		UIInput uiInputConfirmPassword = (UIInput) component.findComponent("confirmaoSenha");
		String confirmPassword = uiInputConfirmPassword.getLocalValue() == null ? ""
				: uiInputConfirmPassword.getLocalValue().toString().trim();
		String confirmId = uiInputConfirmPassword.getClientId();

		if (password.isEmpty() || confirmPassword.isEmpty()) {
			if (password.isEmpty()) {
				FacesMessage message = new FacesMessage("O campo 'Senha' e obrigatorio");
				message.setSeverity(SEVERITY_ERROR);

				context.addMessage(passwordId, message);
			}

			if (confirmPassword.isEmpty()) {
				FacesMessage message = new FacesMessage("O campo 'Confirmacao' e obrigatorio");
				message.setSeverity(SEVERITY_ERROR);

				context.addMessage(confirmId, message);
			}

			context.renderResponse();
		} else if (!password.equals(confirmPassword)) {
			FacesMessage message = new FacesMessage("A 'Senha' deve coincidir com a 'Confirmacao'");
			message.setSeverity(SEVERITY_ERROR);

			context.addMessage(passwordId, message);
			context.renderResponse();
		}
	}

	public Set<Autorizacao> getAutorizacoes() {
		Set<Autorizacao> autorizacoes = new HashSet<Autorizacao>();

		for (Autorizacao autorizacao : Autorizacao.values())
			autorizacoes.add(autorizacao);

		return autorizacoes;
	}

	@Override
	protected String getListPage() {
		return LIST_PAGE;
	}

}
