/**
 * 
 */
package br.com.cams7.crud.controller.bean;

import static br.com.cams7.crud.controller.bean.PessoaBeanController.CONTROLLER_NAME;
import static javax.faces.application.FacesMessage.SEVERITY_ERROR;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.ComponentSystemEvent;

import br.com.cams7.crud.entity.PessoaEntity;
import br.com.cams7.crud.service.PessoaService;
import br.com.cams7.sys.controller.AbstractBeanController;

/**
 * @author cesar
 *
 */
@ManagedBean(name = CONTROLLER_NAME)
@ViewScoped
public class PessoaBeanController extends AbstractBeanController<PessoaService, PessoaEntity> {

	private static final long serialVersionUID = 1L;

	public final static String CONTROLLER_NAME = "pessoaMB";

	private final String LIST_PAGE = "listaPessoas";

	/**
	 * Utiliza a injeção de dependência do <code>Spring Framework</code> para
	 * resolver a instância do <code>PessoaService</code>.
	 */
	@EJB
	private PessoaService service;

	public PessoaBeanController() {
		super("nome", "cpf");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see br.com.cams7.app.controller.AbstractBeanController#createEntity()
	 */
	@Override
	public String createEntity() {
		String listPage = super.createEntity();

		// addINFOMessage(getMessageFromI18N("msg.ok.summary.salva.pessoa"),
		// getMessageFromI18N("msg.ok.detail.salva.pessoa",
		// getSelectedEntity().getNome()));

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

		addINFOMessage(getMessageFromI18N("msg.ok.summary.atualiza.pessoa"),
				getMessageFromI18N("msg.ok.detail.atualiza.pessoa", getSelectedEntity().getNome()));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see br.com.cams7.app.controller.AbstractBeanController#removeEntity()
	 */
	@Override
	public void removeEntity(ActionEvent event) {
		super.removeEntity(event);

		addINFOMessage(getMessageFromI18N("msg.ok.summary.remove.pessoa"),
				getMessageFromI18N("msg.ok.detail.remove.pessoa", getSelectedEntity().getNome()));
	}

	/**
	 * Verifica se o CPF foi cadastrado anteriormente
	 * 
	 * @param event
	 */
	public void verificaCpf(ComponentSystemEvent event) {
		UIComponent component = event.getComponent();

		UIInput uiInputCpf = (UIInput) component.findComponent("pessoaCpf");
		String cpf = uiInputCpf.getLocalValue() == null ? "" : String.valueOf(uiInputCpf.getLocalValue());

		if (cpf.isEmpty())
			return;

		PessoaEntity pessoa = getSelectedEntity();
		Long pessoaId = pessoa.getId();

		boolean cpfValido = getService().isCpfValido(pessoaId, cpf);
		if (cpfValido)
			return;

		uiInputCpf.resetValue();

		String cpfId = uiInputCpf.getClientId();
		FacesMessage message = new FacesMessage(getMessageFromI18N("msg.warn.pessoa.cpfCadastrado", cpf));
		message.setSeverity(SEVERITY_ERROR);

		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage(cpfId, message);
		context.renderResponse();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see br.com.cams7.app.controller.AbstractController#getService()
	 */
	@Override
	protected PessoaService getService() {
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
