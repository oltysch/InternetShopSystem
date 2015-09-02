package com.epam.ot;

import com.epam.ot.action.Action;
import com.epam.ot.action.ActionFactory;
import com.epam.ot.action.ActionResult;
import com.epam.ot.action.tools.CookieManager;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class MainServlet extends HttpServlet {
    private ActionFactory actionFactory;

    @Override
    public void init() throws ServletException {
        actionFactory = new ActionFactory();
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String actionName = req.getMethod() + req.getPathInfo();
        Action action = actionFactory.getAction(actionName);
        ActionResult result = action.execute(req, resp);
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

        if (result.isRedirect()) {
            resp.sendRedirect(req.getContextPath() + req.getServletPath() + "/" + result.getView());
        } else {
            req.getRequestDispatcher("/" + result.getView() + ".jsp").forward(req, resp);
        }
    }
}
