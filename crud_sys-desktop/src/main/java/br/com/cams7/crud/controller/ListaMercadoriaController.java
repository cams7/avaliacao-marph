package br.com.cams7.crud.controller;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.swing.SwingUtilities;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import br.com.cams7.crud.action.AbstractAction;
import br.com.cams7.crud.dao.MercadoriaDAO;
import br.com.cams7.crud.entity.MercadoriaEntity;
import br.com.cams7.crud.event.AbstractEventListener;
import br.com.cams7.crud.event.AtualizarListarMercadoriaEvent;
import br.com.cams7.crud.event.BuscarMercadoriaEvent;
import br.com.cams7.crud.event.DeletarMercadoriaEvent;
import br.com.cams7.crud.event.IncluirMercadoriaEvent;
import br.com.cams7.crud.ui.ListaMercadoriasFrame;
import br.com.cams7.crud.ui.SobreFrame;

/**
 * Define a <code>Controller</code> principal do sistema, responsável por gerir
 * a tela com a lista de <code>Mercadoria</code>.
 * 
 * <p>
 * <code>ListaMercadoriaController</code> é mapeada como <code>@Component</code>
 * do Spring. Dessa forma uma instância de
 * <code>ListaMercadoriaController</code> pode ser criada e gerenciada pelo
 * Spring, favorecendo a Inversão de Controle <i>(IoC)</i> e Injeção de
 * Dependência <i>(DI)</i>.
 * </p>
 * 
 * <p>
 * Essa <code>Controller</code> depende de outros componentes da camada
 * <code>Model</code> (DAO) e <code>View</code> (telas). As dependências são
 * resolvidas pelo Spring, através da <strong>Injeção de Dependência</strong> c/
 * a anotação <code>@Autowired</code>.
 * </p>
 * 
 * @see br.com.yaw.sjpac.controller.PersistenceController
 * 
 * @author YaW Tecnologia
 */
@Component
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class ListaMercadoriaController extends AbstractController {

	@Autowired
	private ListaMercadoriasFrame frame;

	@Autowired
	private SobreFrame sobreFrame;

	@Autowired
	private IncluirMercadoriaController incluirController;

	@Autowired
	private BuscarMercadoriaController buscarController;

	@Autowired
	private MercadoriaDAO dao;

	public ListaMercadoriaController() {
	}

	/**
	 * Método executado pelo <code>Spring</code>, depois de criar a instância de
	 * <code>ListaMercadoriaController</code>.
	 * 
	 * <p>
	 * Faz o registro das ações e tratadores de eventos.
	 * </p>
	 */
	@PostConstruct
	private void init() {
		this.frame.addWindowListener(this);

		registerAction(frame.getNewButton(), new AbstractAction() {
			public void action() {
				incluirController.show();
			}
		});

		registerAction(frame.getRefreshButton(), new AbstractAction() {
			public void action() {
				fireEvent(new AtualizarListarMercadoriaEvent());
			}
		});

		registerAction(frame.getFindButton(), new AbstractAction() {
			public void action() {
				buscarController.show();
			}
		});

		AbstractAction actionSobre = new AbstractAction() {
			@Override
			protected void action() {
				sobreFrame.setVisible(true);
			}
		};
		registerAction(frame.getMenuSobre(), actionSobre);
		this.frame.getMenuAjuda().addListener(actionSobre);

		this.frame.getTable().addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent event) {
				if (event.getClickCount() == 2) {
					MercadoriaEntity m = frame.getTable().getMercadoriaSelected();
					if (m != null) {
						incluirController.show(m);
					}
				}
			}
		});

		registerEventListener(IncluirMercadoriaEvent.class, new AbstractEventListener<IncluirMercadoriaEvent>() {
			public void handleEvent(IncluirMercadoriaEvent event) {
				SwingUtilities.invokeLater(new Runnable() {
					public void run() {
						refreshTable();
					}
				});
			}
		});

		registerEventListener(DeletarMercadoriaEvent.class, new AbstractEventListener<DeletarMercadoriaEvent>() {
			public void handleEvent(DeletarMercadoriaEvent event) {
				SwingUtilities.invokeLater(new Runnable() {
					public void run() {
						refreshTable();
					}
				});
			}
		});

		registerEventListener(AtualizarListarMercadoriaEvent.class,
				new AbstractEventListener<AtualizarListarMercadoriaEvent>() {
					public void handleEvent(AtualizarListarMercadoriaEvent event) {
						SwingUtilities.invokeLater(new Runnable() {
							public void run() {
								refreshTable();
							}
						});
					}
				});

		registerEventListener(BuscarMercadoriaEvent.class, new AbstractEventListener<BuscarMercadoriaEvent>() {
			public void handleEvent(final BuscarMercadoriaEvent event) {
				SwingUtilities.invokeLater(new Runnable() {
					public void run() {
						List<MercadoriaEntity> list = event.getTarget();
						if (list != null) {
							frame.refreshTable(list);
						}
					}
				});
			}
		});

		this.frame.setVisible(true);
		refreshTable();
	}

	private void refreshTable() {
		this.frame.refreshTable(this.dao.findAll());
	}

}
