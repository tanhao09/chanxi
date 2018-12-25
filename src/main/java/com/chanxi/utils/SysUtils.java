package com.chanxi.utils;

import com.mysql.jdbc.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public final class SysUtils {
    private static final Logger logger = LoggerFactory.getLogger(SysUtils.class);

    private static int USER_PWD_SALT_LENGTH = 8;

    public static HttpServletRequest getCurrentRequest() {
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
    }

    public static HttpServletResponse getCurrentResponse() {
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
    }

    public static HttpSession getSession() {
        return getCurrentRequest().getSession();
    }

    public static Cookie getCookie(String name) {
        return getCookie(getCurrentRequest(), name);
    }

    public static Cookie getCookie(HttpServletRequest request, String name) {
        if (request.getCookies() == null || request.getCookies().length == 0 || name == null || name.trim().length() == 0) return null;

        for (Cookie cookie : request.getCookies()) if (cookie.getName().equals(name)) return cookie;

        return null;
    }

    public static String getCookieValue(HttpServletRequest request, String name) {
        Cookie cookie = getCookie(request, name);

        try {
            return cookie == null ? null : URLDecoder.decode(cookie.getValue(), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            logger.error(e.getMessage());
        }
        return null;
    }

    public static String getCookieValue(String name) {
        return getCookieValue(getCurrentRequest(), name);
    }

    public static boolean removeCookie(HttpServletRequest request, HttpServletResponse response, String name, String domain) {
        //Cookie cookie = getCookie(request, name);
        Cookie cookie = new Cookie(name, null);
        if (cookie != null) {
            cookie.setValue("");
            cookie.setMaxAge(0);
            cookie.setPath("/");
            if(domain!=null)
            	cookie.setDomain(domain);
            response.addCookie(cookie);
        }

        return true;
    }
    public static boolean removeCookie(HttpServletRequest request, HttpServletResponse response, String name) {
    	return removeCookie(request, response,name,null);
    	
    }

    public static boolean removeCookie(HttpServletResponse response, String name) {
        return removeCookie(getCurrentRequest(), response, name);
    }

    //获取ip
    public static String getAddress(HttpServletRequest request) {
        if (request == null)
            return null;
        String address = null;
        address = request.getHeader("x-forwarded-for");
        if (address == null)
            address = request.getHeader("x-real-ip");
        if (address == null)
            address = request.getRemoteAddr();
        return address;
    }


    public static String createUserPasswordSalt(String key) {
        if (StringUtils.isEmptyOrWhitespaceOnly(key)) key = String.valueOf((new Date()).getTime());

        return MessageDigestUtils.md5(key).substring(0, USER_PWD_SALT_LENGTH);
    }

    public static String createUserPassword(String username, String pwd) {
        return createUserPassword(username, pwd, null);
    }

    public static String createUserPassword(String username, String pwd, String salt) {
        if (StringUtils.isEmptyOrWhitespaceOnly(salt)) salt = createUserPasswordSalt(username);

        return salt + MessageDigestUtils.sha256(salt + MessageDigestUtils.md5(pwd));
    }

    /**
     * 从数据库中保存的用户密码中获取salt
     */
    public static String getUserPasswordSalt(String pwd) {
        return pwd.length() > USER_PWD_SALT_LENGTH ? pwd.substring(0, USER_PWD_SALT_LENGTH) : null;
    }

    public static void main(String[] args) {
        String[] a = new String[]{};
        String b = "";
        List list = new ArrayList();
        System.out.println("uuid:" + ((Object) b instanceof List));
    }
}
