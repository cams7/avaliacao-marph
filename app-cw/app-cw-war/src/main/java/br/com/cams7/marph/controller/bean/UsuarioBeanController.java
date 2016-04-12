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
import javax.faces.bean.RequestScoped;
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
// @ViewScoped
@RequestScoped
public class UsuarioBeanController extends AbstractBeanController<UsuarioService, UsuarioEntity> {

	private static final long serialVersionUID = 1L;

	public final static String CONTROLLER_NAME = "usuarioMB";

	private final String LIST_PAGE = "listaUsuarios";

	/**
	 * Utiliza a injeção de dependência do <code>Spring Framework</code> para
	 * resolver a instância do <code>PessoaService</code>.
	 */
	@Autowired
	private PessoaService pessoaService;

	public UsuarioBeanController() {
		super("login", "pessoa.nome");
	}

	/*
	 * Utiliza a injeção de dependência do <code>Spring Framework</code> para
	 * resolver a instância do <code>UsuarioService</code>.
	 * 
	 * @see
	 * br.com.cams7.cw.controller.AbstractController#setService(br.com.cams7.app
	 * .service.BaseService)
	 */
	@Autowired
	@Override
	protected void setService(UsuarioService service) {
		super.setService(service);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see br.com.cams7.app.controller.AbstractBeanController#createEntity()
	 */
	@Override
	public String createEntity() {
		String listPage = super.createEntity();

		addINFOMessage(getMessageFromI18N("msg.ok.summary.salva.usuario"),
				getMessageFromI18N("msg.ok.detail.salva.usuario", getSelectedEntity().getLogin()));

		return listPage;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see br.com.cams7.app.controller.AbstractBeanController#updateEntity()
	 */
	@Override
	public void updateEntity() {
		super.updateEntity();

		addINFOMessage(getMessageFromI18N("msg.ok.summary.atualiza.usuario"),
				getMessageFromI18N("msg.ok.detail.atualiza.usuario", getSelectedEntity().getLogin()));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see br.com.cams7.app.controller.AbstractBeanController#removeEntity()
	 */
	@Override
	public void removeEntity() {
		super.removeEntity();

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
		addINFOMessage(getMessageFromI18N("msg.ok.summary.pessoa.selecionada"),
				getMessageFromI18N("msg.ok.detail.pessoa.selecionada", ((PessoaEntity) event.getObject()).getId()));
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
	 * @see br.com.cams7.app.controller.AbstractBeanController#getListPage()
	 */
	@Override
	protected String getListPage() {
		return LIST_PAGE;
	}

}
