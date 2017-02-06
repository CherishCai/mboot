package cn.cherish.mboot.util;

import cn.cherish.mboot.dal.entity.User;

import javax.servlet.http.HttpSession;


/**
 * @author Cherish
 */
public class SessionUtil {
	
	private static String SESSION_USER = "sessionUser";

	public static User getUser(HttpSession session) {
		return (User) session.getAttribute(SESSION_USER);
	}

	public static void addUser(HttpSession session, User user) {
		session.setAttribute(SESSION_USER, user);
	}
	
	public static Object get(HttpSession session, String attrName) {
		return session.getAttribute(attrName);
	}

	public static void add(HttpSession session, String attrName, Object val) {
		session.setAttribute(attrName, val);
	}
	
}
