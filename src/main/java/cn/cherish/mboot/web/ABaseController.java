package cn.cherish.mboot.web;

import java.util.HashMap;
import java.util.Map;

/**
 * 联系方式：18826137274/785427346@qq.com
 * @author Cherish
 * @date 2016年8月17日 下午6:51:31
 * @version 1.0
 */
public class ABaseController {

	protected static final Integer PAGE_SIZE = 20;
	protected static final Integer NOT_LOGIN_CODE = 100;
	protected static final Integer SUCCESS_CODE = 200;
	protected static final Integer PARAM_ERROR_CODE = 400;
	protected static final Integer FORBIDDEN_CODE = 403;
	protected static final Integer NOT_FOUND_CODE = 404;
	protected static final Integer ERROR_CODE = 500;

	protected static final String BUSY_MSG = "系统繁忙";

	protected Map returnMap(Integer code, String message, Object data) {
		return returnMap(code, null, message, data);
	}
	
	protected Map returnMap(Boolean success, String message, Object data) {
		return returnMap(null, success, message, data);
	}

	protected Map returnMap(Integer code, Boolean success, String message, Object data) {
		Map<String, Object> map = new HashMap<>(5);
		map.put("code", code);
		map.put("success", success);
		map.put("message", message);
		map.put("data", data);
		return map;
	}
}
