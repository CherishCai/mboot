package cn.cherish.mboot.extra.shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import cn.cherish.mboot.extra.shiro.MShiroRealm.ShiroUser;

public class ShiroUserUtil {

    public static ShiroUser getShiroUser() {
        Subject subject = SecurityUtils.getSubject();
        if (subject == null) {
            return null;
        }
        ShiroUser shiroUser = (ShiroUser) subject.getPrincipal();
        if (shiroUser == null) {
            return null;
        }
        return shiroUser;
    }

    public static Session getSession() {
        Subject subject = SecurityUtils.getSubject();
        if (subject == null) {
            return null;
        }
        Session session = subject.getSession();
        return session;
    }

    public static Long getUserId() {
        ShiroUser shiroUser = getShiroUser();
        if (shiroUser == null) {
            return null;
        }
        return shiroUser.id;
    }

    public static String getUsername() {
        ShiroUser shiroUser = getShiroUser();
        if (shiroUser == null) {
            return null;
        }
        return shiroUser.toString();
    }

    public static String getNickname() {
        ShiroUser shiroUser = getShiroUser();
        if (shiroUser == null) {
            return null;
        }
        return shiroUser.nickname;
    }

}
