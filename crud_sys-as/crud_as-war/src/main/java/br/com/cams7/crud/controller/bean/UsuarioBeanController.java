/**
 * 
 */
package br.com.cams7.crud.controller.bean;

import static br.com.cams7.crud.controller.bean.UsuarioBeanController.CONTROLLER_NAME;
import static javax.faces.application.FacesMessage.SEVERITY_ERROR;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.ComponentSystemEvent;

import org.primefaces.event.SelectEvent;

import br.com.cams7.crud.entity.PessoaEntity;
import br.com.cams7.crud.entity.UsuarioEntity;
import br.com.cams7.crud.entity.UsuarioEntity.Autorizacao;
import br.com.cams7.crud.service.PessoaService;
import br.com.cams7.crud.service.UsuarioService;
import br.com.cams7.sys.controller.AbstractBeanController;

/**
 * @author cesar
 *
 */
@ManagedBean(name = CONTROLLER_NAME)
@ViewScoped
public class UsuarioBeanController extends AbstractBeanController<UsuarioService, UsuarioEntity> {

	private static final long serialVersionUID = 1L;

	public final static String CONTROLLER_NAME = "usuarioMB";

	private final String LIST_PAGE = "listaUsuarios";

	/**
	 * Utiliza a injeção de dependência do <code>EJB</code> para resolver a
	 * instância do <code>UsuarioService</code>.
	 */
	@EJB
	private UsuarioService service;

	/**
	 * Utiliza a injeção de dependência do <code>EJB</code> para resolver a
	 * instância do <code>PessoaService</code>.
	 */
	@EJB
	private PessoaService pessoaService;

	public UsuarioBeanController() {
		super("login", "pessoa.nome");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see br.com.cams7.app.controller.AbstractBeanController#createEntity()
	 */
	@Override
	public String createEntity() {
		String listPage = super.createEntity();

		// addINFOMessage(getMessageFromI18N("msg.ok.summary.salva.usuario"),
		// getMessageFromI18N("msg.ok.detail.salva.usuario",
		// getSelectedEntity().getLogin()));

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

		addINFOMessage(getMessageFromI18N("msg.ok.summary.atualiza.usuario"),
				getMessageFromI18N("msg.ok.detail.atualiza.usuario", getSelectedEntity().getLogin()));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see br.com.cams7.app.controller.AbstractBeanController#removeEntity()
	 */
	@Override
	public void removeEntity(ActionEvent event) {
		super.removeEntity(event);

		addINFOMessage(getMessageFromI18N("msg.ok.summary.remove.usuario"),
				getMessageFromI18N("msg.ok.detail.remove.usuario", getSelectedEntity().getLogin()));
	}

	/**
	 * Busca pessoas pelo nome
	 * 
	 * @param nome
	 * @return Pessoas
	 */
	public List<PessoaEntity> buscaPessoas(String nome) {
		List<PessoaEntity> pessoas = pessoaService.buscaPessoasSemUsuarioPeloNome(nome);
		return pessoas;
	}

	/**
	 * Exibe uma mensagem
	 * 
	 * @param event
	 */
	public void onItemSelect(SelectEvent event) {
		PessoaEntity pessoa = (PessoaEntity) event.getObject();
		pessoa = pessoaService.buscaPeloId(pessoa.getId());
		getSelectedEntity().setPessoa(pessoa);

		addINFOMessage(getMessageFromI18N("msg.ok.summary.pessoa.selecionada"),
				getMessageFromI18N("msg.ok.detail.pessoa.selecionada", pessoa.getNome()));
	}

	/**
	 * Valida senha
	 * 
	 * @param event
	 */
	public void validaSenha(ComponentSystemEvent event) {
		FacesContext context = FacesContext.getCurrentInstance();

		UIComponent component = event.getComponent();

		// get password
		UIInput uiInputPassword = (UIInput) component.findComponent("usuarioSenha");
		String password = uiInputPassword.getLocalValue() == null ? ""
				: uiInputPassword.getLocalValue().toString().trim();
		String passwordId = uiInputPassword.getClientId();

		// get confirm password
		UIInput uiInputConfirmPassword = (UIInput) component.findComponent("senhaConfirmacao");
		String confirmPassword = uiInputConfirmPassword.getLocalValue() == null ? ""
				: uiInputConfirmPassword.getLocalValue().toString().trim();
		String confirmId = uiInputConfirmPassword.getClientId();

		if (password.isEmpty() || confirmPassword.isEmpty()) {
			if (password.isEmpty()) {
				FacesMessage message = new FacesMessage(getMessageFromI18N("msg.warn.usuario.senha.requiredMessage"));
				message.setSeverity(SEVERITY_ERROR);

				context.addMessage(passwordId, message);
			}

			if (confirmPassword.isEmpty()) {
				FacesMessage message = new FacesMessage(
						getMessageFromI18N("msg.warn.usuario.senhaConfirmacao.requiredMessage"));
				message.setSeverity(SEVERITY_ERROR);

				context.addMessage(confirmId, message);
			}

			context.renderResponse();
		} else if (!password.equals(confirmPassword)) {
			FacesMessage message = new FacesMessage(getMessageFromI18N("msg.warn.usuario.senhaDiferenteDaConfirmacao"));
			message.setSeverity(SEVERITY_ERROR);

			context.addMessage(passwordId, message);
			context.renderResponse();
		}
	}

	/**
	 * Verifica se o login foi cadastrado anteriormente
	 * 
	 * @param event
	 */
	public void verificaLogin(ComponentSystemEvent event) {
		UIComponent component = event.getComponent();

		UIComponent uiLogin = component.findComponent("usuarioLogin");

		if (!(uiLogin instanceof UIInput))
			return;

		UIInput uiInputLogin = (UIInput) uiLogin;
		String login = uiInputLogin.getLocalValue() == null ? "" : String.valueOf(uiInputLogin.getLocalValue());

		if (login.isEmpty())
			return;

		UsuarioEntity usuario = getSelectedEntity();
		Long usuarioId = usuario.getId();

		boolean loginValido = getService().loginValido(usuarioId, login);
		if (loginValido)
			return;

		uiInputLogin.resetValue();

		String loginId = uiInputLogin.getClientId();
		FacesMessage message = new FacesMessage(getMessageFromI18N("msg.warn.usuario.loginCadastrado", login));
		message.setSeverity(SEVERITY_ERROR);

		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage(loginId, message);
		context.renderResponse();
	}

	/**
	 * @return Autorizacoes
	 */
	public Set<Autorizacao> getAutorizacoes() {
		Set<Autorizacao> autorizacoes = new HashSet<Autorizacao>();

		for (Autorizacao autorizacao : Autorizacao.values())
			autorizacoes.add(autorizacao);

		return autorizacoes;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see br.com.cams7.app.controller.AbstractController#getService()
	 */
	@Override
	protected UsuarioService getService() {
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
