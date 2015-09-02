package com.epam.ot.action.tools;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

public class CookieManager {
    public static Cookie findCookie(HttpServletRequest req, String cookieName) {
        Cookie[] cookies = req.getCookies();
        Cookie result = null;
        if (cookies != null && cookies.length > 0) {
            for (int i = 0; i < cookies.length; i++) {
                if (cookies[i].getName().equals(cookieName)) {
                    result = cookies[i];
                    break;
                }
            }
        }
        return result;
    }
}
