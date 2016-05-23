package br.com.cams7.app.event;

import br.com.cams7.app.model.MercadoriaEntity;

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
