/**
 * 
 */
package br.com.cams7.cw.controller.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.hibernate5.Hibernate5Module;

/**
 * @author cesar
 *
 */
public class HibernateAwareObjectMapper extends ObjectMapper {

	private static final long serialVersionUID = 1L;

	public HibernateAwareObjectMapper() {
		registerModule(new Hibernate5Module());
	}

}