package cn.cherish.mboot.util;

import cn.cherish.mboot.dal.entity.User;


/**
 * @author Cherish
 */
public class SessionUtil {
	
	private static String SESSION_USER = "sessionUser";

	public static User getUser() {
		return (User) RequestHolder.getSession().getAttribute(SESSION_USER);
	}

	public static void addUser(User user) {
		RequestHolder.getSession().setAttribute(SESSION_USER, user);
	}
	
	public static Object get(String attrName) {
		return RequestHolder.getSession().getAttribute(attrName);
	}

	public static void add(String attrName, Object val) {
		RequestHolder.getSession().setAttribute(attrName, val);
	}
	
}
