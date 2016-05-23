package br.com.cams7.app.validation;

/**
 * Define um contrato para validar o estado (dados) de uma entidade persistente.
 * 
 * @author YaW Tecnologia
 *
 * @param <E>
 *            indica o tipo da Entidade.
 */

public interface EntityValidator<E> {

	/**
	 * Método que aplica o mecanismo de validação a entidade.
	 * 
	 * @param entity
	 *            entidade a ser validada.
	 * @return String <i>vazia</i>, caso não exista problemas de validação. Ou
	 *         retorna uma string com as mensagens de validação.
	 */
	public String validate(E entity);

}
