package br.com.cams7.app.event;

import java.util.List;

import br.com.cams7.app.model.MercadoriaEntity;

/**
 * Evento deve ser gerado durante a pesquisa de mercadorias.
 * 
 * <p>
 * Recebe um <code>List</code> com a(s) <code>Mercadoria<code>(s) encontrada(s).
 * </p>
 * 
 * @author YaW Tecnologia
 */
public class BuscarMercadoriaEvent extends AbstractEvent<List<MercadoriaEntity>> {

	public BuscarMercadoriaEvent(List<MercadoriaEntity> m) {
		super(m);
	}

}
