package br.com.cams7.crud.event;

import br.com.cams7.crud.entity.MercadoriaEntity;

/**
 * Evento deve ser gerado durante a inclusão de uma <code>Mercadoria</code>.
 * 
 * <p>
 * Recebe a referência da <code>Mercadoria</code> que foi incluida.
 * </p>
 * 
 * @author YaW Tecnologia
 */
public class IncluirMercadoriaEvent extends AbstractEvent<MercadoriaEntity> {

	public IncluirMercadoriaEvent(MercadoriaEntity m) {
		super(m);
	}
}
