/**
 * 
 */
package br.com.cams7.utils;

import java.lang.reflect.ParameterizedType;

/**
 * @author cesar
 *
 */
public final class AppHelper {

	public static Class<?> getType(Class<?> type, byte argumentNumber) {
		Class<?> returnType = (Class<?>) ((ParameterizedType) type.getGenericSuperclass())
				.getActualTypeArguments()[argumentNumber];
		return returnType;
	}

}
