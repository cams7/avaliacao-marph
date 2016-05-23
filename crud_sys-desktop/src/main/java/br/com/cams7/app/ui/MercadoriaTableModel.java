package br.com.cams7.app.ui;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import br.com.cams7.app.model.MercadoriaEntity;

/**
 * Define um TableModel para entidade <code>Mercadoria</code>, considerando as
 * colunas:
 * <ul>
 * <li>Nome;</li>
 * <li>Descrição;</li>
 * <li>Preço;</li>
 * <li>Quantidade;</li>
 * </ul>
 * 
 * @author YaW Tecnologia
 */
@SuppressWarnings("serial")
public class MercadoriaTableModel extends AbstractTableModel {

	private List<MercadoriaEntity> mercadorias;

	private String[] colNomes = { "Nome", "Descricao", "Preco", "Quantidade" };

	private Class<?>[] colTipos = { String.class, String.class, String.class, Integer.class };

	public MercadoriaTableModel() {
	}

	public void reload(List<MercadoriaEntity> mercadorias) {
		this.mercadorias = mercadorias;
		// atualiza o componente na tela
		fireTableDataChanged();
	}

	@Override
	public Class<?> getColumnClass(int coluna) {
		return colTipos[coluna];
	}

	@Override
	public int getColumnCount() {
		return 4;
	}

	@Override
	public String getColumnName(int coluna) {
		return colNomes[coluna];
	}

	@Override
	public int getRowCount() {
		if (mercadorias == null) {
			return 0;
		}
		return mercadorias.size();
	}

	@Override
	public Object getValueAt(int linha, int coluna) {
		MercadoriaEntity mercadoria = mercadorias.get(linha);
		switch (coluna) {
		case 0:
			return mercadoria.getNome();
		case 1:
			return mercadoria.getDescricao();
		case 2:
			return MercadoriaEntity.convertPrecoToString(mercadoria.getPreco());
		case 3:
			return mercadoria.getQuantidade();
		default:
			return null;
		}
	}

	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return false;
	}

	public MercadoriaEntity getMercadoriaAt(int index) {
		return mercadorias.get(index);
	}

}
