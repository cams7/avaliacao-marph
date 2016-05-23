package br.com.cams7.app.event;

/**
 * Evento deve ser gerado quando for necessário atualizar a tabela de
 * mercadorias.
 * 
 * @author YaW Tecnologia
 */
public class AtualizarListarMercadoriaEvent extends AbstractEvent<Object> {

	public AtualizarListarMercadoriaEvent() {
		super(null);
	}

}
