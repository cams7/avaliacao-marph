/**
 * 
 */
package br.com.cams7.sys.utils;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import br.com.cams7.sys.SearchParams;
import br.com.cams7.sys.SearchParams.SortOrder;
import br.com.cams7.sys.entity.AbstractEntity;

/**
 * @author cesar
 *
 */
public final class URIHelper {

	public static final String PAGE_FIRST = "page_first";
	public static final String PAGE_SIZE = "page_size";
	public static final String SORT_FIELD = "sort_field";
	public static final String SORT_ORDER = "sort_order";
	public static final String FILTER_FIELD = "filter_field";
	public static final String GLOBAL_FILTER = "globalFilter";

	/**
	 * @param messages
	 * @param paramName
	 * @param paramValues
	 * @return
	 */
	private static boolean onlyOneParameter(Map<String, String> messages, String paramName, String[] paramValues) {
		if (paramValues.length > 1) {
			messages.put(paramName,
					String.format("URI inválida, porque o parâmetro foi passado %s vezes", paramValues.length));
			return false;
		}
		return true;
	}

	/**
	 * @param messages
	 * @param paramName
	 */
	private static void validParameter(Map<String, String> messages, String paramName) {
		messages.put(paramName, "URI inválida, porque o parâmetro não é válido");
	}

	public static <E extends AbstractEntity> SearchParams getParams(Class<E> entityType,
			Map<String, String[]> allParams) {

		SearchParams params = new SearchParams();
		params.setFilters(new HashMap<String, Object>());

		Map<String, String> messages = new HashMap<>();

		for (Entry<String, String[]> param : allParams.entrySet()) {
			String paramName = param.getKey();
			String[] paramValues = param.getValue();

			switch (paramName) {
			case PAGE_FIRST:
				if (onlyOneParameter(messages, paramName, paramValues))
					try {
						params.setPageFirst(Integer.parseInt(paramValues[0]));
					} catch (NumberFormatException e) {
						validParameter(messages, paramName);
					}
				break;
			case PAGE_SIZE:
				if (onlyOneParameter(messages, paramName, paramValues))
					try {
						params.setPageSize(Short.parseShort(paramValues[0]));
					} catch (NumberFormatException e) {
						validParameter(messages, paramName);
					}
				break;
			case SORT_FIELD:
				if (onlyOneParameter(messages, paramName, paramValues))
					params.setSortField(paramValues[0]);
				break;
			case SORT_ORDER:
				if (onlyOneParameter(messages, paramName, paramValues))
					try {
						params.setSortOrder(SortOrder.valueOf(paramValues[0]));
					} catch (IllegalArgumentException e) {
						validParameter(messages, paramName);
					}
				break;
			case FILTER_FIELD:
				params.setGlobalFilters(paramValues);
				break;
			default:
				if (onlyOneParameter(messages, paramName, paramValues)) {
					Object value = paramValues[0];
					if (!GLOBAL_FILTER.equals(paramName))
						try {
							value = AppHelper.getFieldValue(entityType, paramName, paramValues[0]);
						} catch (AppException e) {
							validParameter(messages, paramName);
							break;
						}
					params.getFilters().put(paramName, value);
				}
				break;
			}
		}

		if (!messages.isEmpty())
			throw new AppException(messages);

		return params;
	}

	public static String getQueryDelimiter(boolean isQueryDelimiter) {
		return isQueryDelimiter ? "&" : "?";
	}

}
