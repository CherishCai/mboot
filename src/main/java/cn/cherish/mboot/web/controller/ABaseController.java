package cn.cherish.mboot.web.controller;

import cn.cherish.mboot.web.MResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 联系方式：18826137274/785427346@qq.com
 * @author Cherish
 * @date 2016年8月17日 下午6:51:31
 * @version 1.0
 */
public class ABaseController {

	protected Logger LOGGER = LoggerFactory.getLogger(getClass());

	protected static final Integer PAGE_SIZE = 20;
	protected static final Integer NOT_LOGIN_CODE = 100;
	protected static final Integer SUCCESS_CODE = 200;
	protected static final Integer PARAM_ERROR_CODE = 400;
	protected static final Integer FORBIDDEN_CODE = 403;
	protected static final Integer NOT_FOUND_CODE = 404;
	protected static final Integer ERROR_CODE = 500;

	protected static final String BUSY_MSG = "系统繁忙";

	protected MResponse buildResponse(Integer code, String message, Object data) {
		return buildResponse(code, null, message, data);
	}
	
	protected MResponse buildResponse(Boolean success, String message, Object data) {
		return buildResponse(null, success, message, data);
	}

	protected MResponse buildResponse(Integer code, Boolean success, String message, Object data) {
		return new MResponse(code, success, message, data);
	}

	protected Map<String, String> getErrors(BindingResult result) {
		Map<String, String> map = new HashMap<>();
		List<FieldError> list = result.getFieldErrors();
		for (FieldError error : list) {
			LOGGER.debug("error: {} -> {}", error.getField(), error.getDefaultMessage());
			map.put(error.getField(), error.getDefaultMessage());
		}
		return map;
	}



}
