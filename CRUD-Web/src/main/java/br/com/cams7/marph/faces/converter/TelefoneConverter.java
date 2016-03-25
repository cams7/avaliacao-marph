/**
 * 
 */
package br.com.cams7.marph.faces.converter;

import static br.com.cams7.marph.faces.converter.TelefoneConverter.CONVERT_NAME;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import org.springframework.stereotype.Component;

/**
 * Converte Telefone
 * 
 * @author cesar
 *
 */
@Component(CONVERT_NAME)
@FacesConverter(CONVERT_NAME)
public class TelefoneConverter implements Converter {

	public final static String CONVERT_NAME = "telefoneConverter";

	/**
	 * 
	 */
	public TelefoneConverter() {
		super();
	}

	/*
	 * Remove espaços vazios, pontos, traços e parenteses
	 * 
	 * (non-Javadoc)
	 * 
	 * @see javax.faces.convert.Converter#getAsObject(javax.faces.context.
	 * FacesContext, javax.faces.component.UIComponent, java.lang.String)
	 */
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		if (value != null && !value.isEmpty())
			// (99) 9999-9999
			value = value.replaceAll("[() -]", "");

		return value;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.faces.convert.Converter#getAsString(javax.faces.context.
	 * FacesContext, javax.faces.component.UIComponent, java.lang.Object)
	 */
	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value == null)
			return null;

		String valueAsString = (String) value;

		if (valueAsString.isEmpty())
			return "";

		// 01 2345 6789
		// (99) 9999-9999
		valueAsString = "(" + valueAsString.substring(0, 2) + ") " + valueAsString.substring(2, 6) + "-"
				+ valueAsString.substring(6);

		return valueAsString;
	}
}
