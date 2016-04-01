/**
 * 
 */
package br.com.cams7.marph.faces.converter;

import static br.com.cams7.marph.faces.converter.AutorizacaoConverter.CONVERT_NAME;

import javax.faces.convert.EnumConverter;
import javax.faces.convert.FacesConverter;

import br.com.cams7.marph.entity.UsuarioEntity.Autorizacao;

/**
 * Converte Autorizacao
 * 
 * @author cesar
 *
 */
//@Component(CONVERT_NAME)
@FacesConverter(CONVERT_NAME)
public class AutorizacaoConverter extends EnumConverter {

	public final static String CONVERT_NAME = "autorizacaoConverter";

	public AutorizacaoConverter() {
		super(Autorizacao.class);
	}

}
