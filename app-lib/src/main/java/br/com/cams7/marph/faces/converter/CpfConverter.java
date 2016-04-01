/**
 * 
 */
package br.com.cams7.marph.faces.converter;

import static br.com.cams7.marph.faces.converter.CpfConverter.CONVERT_NAME;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 * Converte CPF
 * 
 * @author cesar
 *
 */
//@Component(CONVERT_NAME)
@FacesConverter(CONVERT_NAME)
public class CpfConverter implements Converter {

	public final static String CONVERT_NAME = "cpfConverter";

	/**
	 * 
	 */
	public CpfConverter() {
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
			// 999.999.999-99
			value = value.replaceAll("[.-]", "");

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

		// 012 345 678 90
		// 999.999.999-99
		valueAsString = valueAsString.substring(0, 3) + "." + valueAsString.substring(3, 6) + "."
				+ valueAsString.substring(6, 9) + "-" + valueAsString.substring(9);

		return valueAsString;
	}

}
