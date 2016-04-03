/**
 * 
 */
package br.com.cams7.app.utils;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import br.com.cams7.app.entity.AbstractEntity;

/**
 * Classe utilitaria
 * 
 * @author cesar
 */
public final class AppHelper {

	/**
	 * Retorna o tipo de classe
	 * 
	 * @param entityType
	 *            Tipo de classe Entity
	 * @param fieldName
	 *            Nome do atributo da entidade
	 * @return Tipo de classe
	 * @throws AppException
	 */
	private static Class<?> getType(Class<?> entityType, String fieldName) throws AppException {
		int index = fieldName.indexOf(".");
		String fieldName2 = null;

		if (index > -1) {
			fieldName2 = fieldName.substring(index + 1);
			fieldName = fieldName.substring(0, index);
		}

		try {
			Field field = entityType.getDeclaredField(fieldName);
			Class<?> type = field.getType();

			if (fieldName2 != null)
				return getType(type, fieldName2);

			return type;
		} catch (NoSuchFieldException | SecurityException e) {
			throw new AppException(e.getMessage(), e.getCause());
		}
	}

	/**
	 * Corrige o valor
	 * 
	 * @param value
	 * @return
	 */
	private static Object getCorrectValue(Object value) {
		if (value == null)
			return null;

		if (value instanceof String) {
			String stringValue = (String) value;
			if (stringValue.isEmpty())
				return null;

			return stringValue.trim();
		}

		return value;
	}

	/**
	 * Converte o valor para o tipo correto
	 * 
	 * @param entityType
	 *            Tipo da entidade
	 * @param fieldName
	 *            Nome do atributo
	 * @param fieldValue
	 *            Vator do atributo
	 * @return
	 */
	public static Object getFieldValue(Class<?> entityType, String fieldName, Object fieldValue) throws AppException {
		Class<?> type = getType(entityType, fieldName);

		fieldValue = getCorrectValue(fieldValue);

		if (fieldValue == null)
			return null;

		if (type.equals(String.class))
			return fieldValue;

		try {
			Constructor<?> constructor = type.getDeclaredConstructor(String.class);
			Object value = constructor.newInstance(String.valueOf(fieldValue));
			return value;
		} catch (SecurityException | IllegalArgumentException | NoSuchMethodException | InstantiationException
				| IllegalAccessException | InvocationTargetException e) {
			throw new AppException(e.getMessage(), e.getCause());
		}
	}

	/**
	 * Retorna o tipo da classe usada como Template
	 * 
	 * @param type
	 *            Tipo de classe que pode ser Repository, Service e Controller
	 * @param argumentNumber
	 *            Indice do Template
	 * @return Tipo de classe que normalmente e uma Entity
	 */
	public static Class<?> getType(Class<?> type, byte argumentNumber) {
		Class<?> returnType = (Class<?>) ((ParameterizedType) type.getGenericSuperclass())
				.getActualTypeArguments()[argumentNumber];
		return returnType;
	}

	/**
	 * Cria uma nova entidade
	 * 
	 * @param entityType
	 *            Tipo de classe Entity
	 * @return Entity
	 * @throws AppException
	 */
	public static <E extends AbstractEntity> E getNewEntity(Class<E> entityType) throws AppException {
		try {
			E entity = entityType.newInstance();
			return entity;
		} catch (InstantiationException | IllegalAccessException e) {
			throw new AppException(e.getMessage(), e.getCause());
		}
	}

	/**
	 * @param changedEntity
	 * @param entity
	 * @throws AppException
	 */
	public static <E extends AbstractEntity> void changeValues(E changedEntity, E entity) throws AppException {
		if (changedEntity == null || entity == null)
			throw new AppException("Entidade NULA");

		@SuppressWarnings("unchecked")
		Class<E> entityType = (Class<E>) changedEntity.getClass();

		final String PREFIX_GET_METHOD = "get";
		final String PREFIX_IS_METHOD = "is";

		Method[] allMethods = entityType.getDeclaredMethods();
		for (Method getMethod : allMethods) {
			String getMethodName = getMethod.getName();

			String prefix = null;

			if (getMethodName.startsWith(PREFIX_GET_METHOD))
				prefix = PREFIX_GET_METHOD;
			else if (getMethodName.startsWith(PREFIX_IS_METHOD))
				prefix = PREFIX_IS_METHOD;

			if (prefix == null)
				continue;

			String setMethodName = getMethodName.replaceFirst(prefix, "set");

			try {
				Object value = getMethod.invoke(entity);

				Method setMethod = entityType.getDeclaredMethod(setMethodName,
						new Class<?>[] { getMethod.getReturnType() });
				setMethod.invoke(changedEntity, value);
			} catch (InvocationTargetException | IllegalAccessException | IllegalArgumentException
					| NoSuchMethodException | SecurityException e) {
				continue;
			}
		}
	}

	/**
	 * Retorna um map que nao esteja vazio
	 * 
	 * @param map
	 *            Map
	 * @return Map
	 */
	public static <K, V> Map<K, V> removeEmptyValue(Map<K, V> map) {
		if (map != null && !map.isEmpty()) {
			Iterator<Map.Entry<K, V>> i = map.entrySet().iterator();

			while (i.hasNext()) {
				Map.Entry<K, V> entry = i.next();
				V value = entry.getValue();

				if (value == null || (value instanceof String && ((String) value).isEmpty())
						|| ((value instanceof Object[]) && ((Object[]) value).length == 0))
					i.remove();
			}

		}

		return map;
	}

	/**
	 * Verifica entre dois map se eles tem os mesmos valores
	 * 
	 * @param map1
	 *            Map
	 * @param map2
	 *            Map
	 * @return boolean
	 */
	public static <K, V> boolean equalMaps(Map<K, V> map1, Map<K, V> map2) {

		if (map1 == null && map2 == null)
			return true;

		if ((map1 == null && map2 != null) || (map1 != null && map2 == null))
			return false;

		if (map1.isEmpty() && map2.isEmpty())
			return true;

		if ((map1.isEmpty() && !map2.isEmpty()) || (!map1.isEmpty() && map2.isEmpty()))
			return false;

		boolean isEquals = true;

		if (map1.size() == map2.size()) {
			keyMap: for (K key : map1.keySet()) {
				if (!map2.containsKey(key)) {
					isEquals = false;
					break;
				}

				V value1 = map1.get(key);
				V value2 = map2.get(key);

				if (value1 instanceof Object[] && value2 instanceof Object[]) {
					Object[] array1 = (Object[]) value1;
					Object[] array2 = (Object[]) value2;

					if (array1.length != array2.length) {
						isEquals = false;
						break;
					}

					List<?> list = Arrays.asList(array1);

					for (Object value : array2) {
						if (!list.contains(value)) {
							isEquals = false;
							break keyMap;
						}
					}

				} else if (!value1.equals(value2)) {
					isEquals = false;
					break;
				}

			}
		} else
			isEquals = false;

		return isEquals;
	}

	/**
	 * Verifica se o tipo infomado é "Boolean"
	 * 
	 * @param type
	 *            Tipo de classe
	 * @return
	 */
	public static boolean isBoolean(Class<?> type) {
		return type.equals(Boolean.class) || type.equals(Boolean.TYPE);
	}

	private static boolean isInteger(Class<?> type) {
		if (type.equals(Byte.class) || type.equals(Byte.TYPE))
			return true;

		if (type.equals(Short.class) || type.equals(Short.TYPE))
			return true;

		if (type.equals(Integer.class) || type.equals(Integer.TYPE))
			return true;

		if (type.equals(Long.class) || type.equals(Long.TYPE))
			return true;

		return false;
	}

	/**
	 * Verifica se o tipo infomado é "Float"
	 * 
	 * @param type
	 *            Tipo de classe
	 * @return
	 */
	private static boolean isFloat(Class<?> type) {
		if (type.equals(Float.class) || type.equals(Float.TYPE))
			return true;

		if (type.equals(Double.class) || type.equals(Double.TYPE))
			return true;

		return false;
	}

	/**
	 * Verifica se o tipo infomado é "Number"
	 * 
	 * @param type
	 *            Tipo de classe
	 * @return
	 */
	public static boolean isNumber(Class<?> type) {
		if (isInteger(type))
			return true;

		if (isFloat(type))
			return true;

		return false;
	}

	/**
	 * Verifica se o tipo infomado é "Date"
	 * 
	 * @param type
	 *            Tipo de classe
	 * @return
	 */
	public static boolean isDate(Class<?> type) {
		return type.equals(Date.class);
	}

	/**
	 * Verifica se o tipo infomado é "enum"
	 * 
	 * @param type
	 *            Tipo de classe
	 * @return
	 */
	public static boolean isEnum(Class<?> type) {
		return type.isEnum();
	}

}
