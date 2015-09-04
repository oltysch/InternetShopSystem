package com.epam.ot.action.tools;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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

    public static void refreshLanguageCookies(HttpServletRequest req, HttpServletResponse resp) {
        String lang = req.getParameter("lang");
//        find the language settings
        if (lang != null) {
//            if settings founded in request parameters - create cookie and put language settings into session
            Cookie cookie = new Cookie("lang", lang); //cookie creating
            cookie.setMaxAge(60 * 60 * 24 * 3); //cookie expire - 3 days
            resp.addCookie(cookie); //add cookie into response
            req.getSession().setAttribute("lang", lang); //and create attribute in session
        } else {
//            if settings not founded - then use a cookie
            Cookie myCookie = CookieManager.findCookie(req, "lang"); //getting cookies
            if (myCookie != null) {
                //if cookie founded - create attribute in session
                req.getSession().setAttribute("lang", myCookie.getValue());
                myCookie.setMaxAge(60 * 60 * 24 * 3);
                //and refresh cookie expire date
                resp.addCookie(myCookie);
            }
        }
    }
}
