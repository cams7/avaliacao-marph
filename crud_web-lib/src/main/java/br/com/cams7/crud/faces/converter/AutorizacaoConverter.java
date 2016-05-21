/**
 * 
 */
package br.com.cams7.crud.faces.converter;

import static br.com.cams7.crud.faces.converter.AutorizacaoConverter.CONVERT_NAME;

import javax.faces.convert.EnumConverter;
import javax.faces.convert.FacesConverter;

import br.com.cams7.crud.entity.UsuarioEntity.Autorizacao;

/**
 * Converte Autorizacao
 * 
 * @author cesar
 *
 */
@FacesConverter(CONVERT_NAME)
public class AutorizacaoConverter extends EnumConverter {

	public final static String CONVERT_NAME = "autorizacaoConverter";

	public AutorizacaoConverter() {
		super(Autorizacao.class);
	}

}
