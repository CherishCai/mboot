package cn.cherish.mboot.util;


import cn.cherish.mboot.dal.entity.Customer;
import cn.cherish.mboot.dal.entity.User;
import cn.cherish.mboot.dal.entity.WxUser;

public class SessionUtil {
	
	private static String SESSION_USER = "sessionUser";
	private static String SESSION_CUSTOMER = "sessionCustomer";
	private static String SESSION_WXUSER = "sessionWxUser";

	public static Customer getCustomer() {
		return (Customer) get(SESSION_CUSTOMER);
	}

	public static void addCustomer(Customer customer) {
		add(SESSION_CUSTOMER, customer);
	}

	public static WxUser getWxUser() {
		return (WxUser) get(SESSION_WXUSER);
	}

	public static void addWxUser(WxUser weixinUser) {
		add(SESSION_WXUSER, weixinUser);
    }

	public static User getUser() {
		return (User) get(SESSION_USER);
	}

	public static void addUser(User user) {
		add(SESSION_USER, user);
	}

	public static Object get(String attrName) {
		return RequestHolder.getSession().getAttribute(attrName);
	}

	public static void add(String attrName, Object val) {
		RequestHolder.getSession().setAttribute(attrName, val);
	}



}
