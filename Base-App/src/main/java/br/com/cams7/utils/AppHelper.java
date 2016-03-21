/**
 * 
 */
package br.com.cams7.utils;

import java.lang.reflect.ParameterizedType;
import java.util.Arrays;
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
	 * Retorna um map que nao esteja vazio
	 * 
	 * @param map
	 *            Map
	 * @return Map
	 */
	public static <K, V> Map<K, V> removeEmptyArray(Map<K, V> map) {
		if (map != null && !map.isEmpty()) {
			Iterator<Map.Entry<K, V>> i = map.entrySet().iterator();

			while (i.hasNext()) {
				Map.Entry<K, V> entry = i.next();
				V value = entry.getValue();

				if ((value instanceof Object[]) && ((Object[]) value).length == 0)
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

}
