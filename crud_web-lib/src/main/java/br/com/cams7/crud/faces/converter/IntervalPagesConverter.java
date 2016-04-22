/**
 * 
 */
package br.com.cams7.crud.faces.converter;

import static br.com.cams7.crud.faces.converter.IntervalPagesConverter.CONVERT_NAME;

import javax.faces.convert.EnumConverter;
import javax.faces.convert.FacesConverter;

import br.com.cams7.sys.ReportView.IntervalPages;

/**
 * @author cesar
 *
 */
@FacesConverter(CONVERT_NAME)
public class IntervalPagesConverter extends EnumConverter {

	public final static String CONVERT_NAME = "intervalPagesConverter";

	/**
	 * @param targetClass
	 */
	public IntervalPagesConverter() {
		super(IntervalPages.class);
	}

}
