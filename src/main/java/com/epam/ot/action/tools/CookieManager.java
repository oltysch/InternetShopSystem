package com.epam.ot.action.tools;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CookieManager {
    /**
     * it's used for find the cookie by name in request, returns null if not found
     *
     * @param request
     * @param cookieName
     * @return founded cookie
     */
    public static Cookie findCookie(HttpServletRequest request, String cookieName) {
        Cookie[] cookies = request.getCookies(); // getting cookies from request
        Cookie result = null;
        if (cookies != null && cookies.length > 0) {
            // iterate over the array to find cookie by inputted name
            for (Cookie cooky : cookies) {
                // if name is matches - then we found required cookie
                if (cooky.getName().equals(cookieName)) {
                    result = cooky;
                    break;
                }
            }
        }
        return result;
    }

    /**
     * @param request
     * @param response
     */
    public static void refreshLanguageCookies(HttpServletRequest request, HttpServletResponse response) {
        String lang = request.getParameter("lang");
        String sessionLang = (String) request.getSession().getAttribute("lang");
        // find the language settings
        if (lang != null) {
            // if settings founded in request parameters - create cookie and put language settings into session
            Cookie cookie = new Cookie("lang", lang); // cookie creating
            cookie.setMaxAge(60 * 60 * 24 * 3); // cookie expire - 3 days
            response.addCookie(cookie); // add cookie into response
            request.getSession().setAttribute("lang", lang); // and create attribute in session
        } else {
            // if settings not founded - then use a cookie
            Cookie myCookie = findCookie(request, "lang"); // getting cookies
            if (myCookie != null) {
                // if cookie founded - create attribute in session
                if (sessionLang == null) request.getSession().setAttribute("lang", myCookie.getValue());
                myCookie.setMaxAge(60 * 60 * 24 * 3);
                // and refresh cookie expire date
                response.addCookie(myCookie);
            }
        }
    }
}
