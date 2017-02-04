package cn.cherish.mboot.util;

import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class SearchFilter {

	public enum Operator {
		NEQ,EQ, LIKE, LLIKE, GT, LT, GTE, LTE,IN
	}

	public String fieldName;
	public Object value;
	public Operator operator;

	public SearchFilter(String fieldName, Operator operator, Object value) {
		this.fieldName = fieldName;
		this.value = value;
		this.operator = operator;
	}

	/**
	 * searchParams中key的格式为OPERATOR_FIELDNAME
	 */
	public static Map<String, SearchFilter> parse(Map<String, Object> searchParams) {
		Map<String, SearchFilter> filters = new HashMap<>();
		if (searchParams == null) {
			return filters;
		}

		for (Entry<String, Object> entry : searchParams.entrySet()) {
			// 过滤掉空值
			String key = entry.getKey();
			Object value = entry.getValue();
			if (StringUtils.isBlank(value+"")) {
				continue;
			}
            
			// 拆分operator与fieldAttribute
			String[] names = StringUtils.split(key, "_");
			if (names.length != 2) {
				throw new IllegalArgumentException(key + " is not a valid search filter name");
			}
			String filedName = names[1];
			Operator operator = Operator.valueOf(names[0]);
           //添加的特殊功能
			//if(value.equals("true")||value.equals("false")) value=Boolean.valueOf((String)value) ;
			// 创建searchFilter
			SearchFilter filter = new SearchFilter(filedName, operator, value);
			filters.put(key, filter);
		}

		return filters;
	}
}
