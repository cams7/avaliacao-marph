/**
 * 
 */
package br.com.cams7.marph.faces.convert;

import static br.com.cams7.marph.faces.convert.AutorizacaoConvert.CONVERT_NAME;

import javax.faces.convert.EnumConverter;
import javax.faces.convert.FacesConverter;

import org.springframework.stereotype.Component;

import br.com.cams7.marph.entity.UsuarioEntity.Autorizacao;

/**
 * @author cesar
 *
 */
@Component(CONVERT_NAME)
@FacesConverter(CONVERT_NAME)
public class AutorizacaoConvert extends EnumConverter {

	public final static String CONVERT_NAME = "autorizacaoConvert";

	public AutorizacaoConvert() {
		super(Autorizacao.class);
	}

}
