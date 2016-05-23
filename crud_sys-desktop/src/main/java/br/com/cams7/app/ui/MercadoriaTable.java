package br.com.cams7.app.ui;

import java.util.List;

import javax.swing.JTable;

import br.com.cams7.app.model.MercadoriaEntity;

/**
 * <code>JTable</code> com operações customizadas para entidade
 * <code>Mercadoria</code>.
 * 
 * @see br.com.yaw.sjc.ui.MercadoriaTableModel
 * 
 * @author YaW Tecnologia
 */
@SuppressWarnings("serial")
public class MercadoriaTable extends JTable {

	private MercadoriaTableModel modelo;

	public MercadoriaTable() {
		modelo = new MercadoriaTableModel();
		setModel(modelo);
	}

	/**
	 * @return <code>Mercadoria</code> selecionada na tabela. Caso a tabela não
	 *         tenha elementos, retorna <code>null</code>.
	 */
	public MercadoriaEntity getMercadoriaSelected() {
		int i = getSelectedRow();
		if (i < 0) {
			return null;
		}
		return modelo.getMercadoriaAt(i);
	}

	/**
	 * Recarrega a tabela de <code>Mercadoria</code> com a lista
	 * <code>mercadorias</code>.
	 * 
	 * @param mercadorias
	 *            <code>List</code> com os elementos <code>Mercadoria</code> que
	 *            devem ser exibidos na tabela.
	 */
	public void reload(List<MercadoriaEntity> mercadorias) {
		modelo.reload(mercadorias);
	}
}
