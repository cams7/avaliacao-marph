package br.com.cams7.crud.ui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.KeyEvent;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.springframework.stereotype.Component;

/**
 * Tela utilizada para realizar a pesquisa de <code>Mercadoria</code> com filtro
 * no campo <code>nome</code>.
 * 
 * <p>
 * <code>BuscaMercadoriaFrame</code> é mapeada como <code>@Component</code> do
 * Spring. Dessa forma uma instância de <code>BuscaMercadoriaFrame</code> pode
 * ser criada e gerenciada pelo Spring, favorecendo a Inversão de Controle
 * <i>(IoC)</i> e Injeção de Dependência <i>(DI)</i>.
 * </p>
 * 
 * @author YaW Tecnologia
 */
@SuppressWarnings("serial")
@Component
public class BuscaMercadoriaFrame extends JFrame {

	private JTextField tfNome;
	private JButton bBuscar;

	public BuscaMercadoriaFrame() {
		setTitle("Buscar");
		setSize(250, 250);
		setLocationRelativeTo(null);
		setResizable(false);
		inicializaComponentes();
	}

	private void inicializaComponentes() {
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		panel.add(montaPanelBuscaMercadoria(), BorderLayout.CENTER);
		panel.add(montaPanelBotoesBusca(), BorderLayout.SOUTH);
		this.add(panel);

		GroupLayout layout = new GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addGroup(layout.createSequentialGroup().addContainerGap()
						.addComponent(panel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addContainerGap()));
		layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addGroup(layout.createSequentialGroup().addContainerGap()
						.addComponent(panel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addContainerGap()));
	}

	private JPanel montaPanelBotoesBusca() {
		JPanel panel = new JPanel();
		bBuscar = new JButton("Buscar");
		bBuscar.setActionCommand("buscarMercadoriasAction");
		bBuscar.setMnemonic(KeyEvent.VK_B);
		panel.add(bBuscar);
		return panel;
	}

	private JPanel montaPanelBuscaMercadoria() {
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(8, 1));

		tfNome = new JTextField();
		panel.add(new JLabel("Nome:"));
		panel.add(tfNome);
		return panel;
	}

	public void resetForm() {
		tfNome.setText("");
	}

	public JButton getBuscarButton() {
		return bBuscar;
	}

	/**
	 * @return o texto preenchido no campo de consulta.
	 */
	public String getText() {
		return tfNome.getText();
	}

}
