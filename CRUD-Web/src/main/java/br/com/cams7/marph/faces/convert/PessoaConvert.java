/**
 * 
 */
package br.com.cams7.marph.faces.convert;

import static br.com.cams7.marph.faces.convert.PessoaConvert.CONVERT_NAME;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

import org.springframework.stereotype.Component;

import br.com.cams7.marph.entity.PessoaEntity;

/**
 * Converte Pessoa
 * 
 * @author cesar
 *
 */
@Component(CONVERT_NAME)
@FacesConverter(CONVERT_NAME)
public class PessoaConvert implements Converter {

	public final static String CONVERT_NAME = "pessoaConvert";

	public PessoaConvert() {
		super();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.faces.convert.Converter#getAsObject(javax.faces.context.
	 * FacesContext, javax.faces.component.UIComponent, java.lang.String)
	 */
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		if (value != null && !value.isEmpty())
			try {
				Long id = Long.parseLong(value);
				return new PessoaEntity(id);
			} catch (NumberFormatException e) {
				throw new ConverterException(
						new FacesMessage(FacesMessage.SEVERITY_ERROR, "Conversion Error", "Not a valid theme."));
			}

		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.faces.convert.Converter#getAsString(javax.faces.context.
	 * FacesContext, javax.faces.component.UIComponent, java.lang.Object)
	 */
	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value != null) {
			Long id = ((PessoaEntity) value).getId();
			return String.valueOf(id);
		}

		return null;
	}

}
