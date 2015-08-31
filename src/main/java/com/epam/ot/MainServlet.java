package com.epam.ot;

import com.epam.ot.action.Action;
import com.epam.ot.action.ActionFactory;
import com.epam.ot.action.ActionResult;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Locale;

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
        if (lang != null) {
            req.getSession().setAttribute("lang", lang);
        }/*
        Cookie[] cookies = req.getCookies();
        Cookie myCookie = null;
        if (cookies != null) {
            for (int i = 0; i < cookies.length; i++) {
                if (cookies[i].getName().equals("lang")) {
                    myCookie = cookies[i];
                    break;
                }
            }
        }
        if (myCookie != null) {
            req.getSession().setAttribute("lang", myCookie.getValue());
        }*/

        if (result.isRedirect()) {
            resp.sendRedirect(req.getContextPath() + req.getServletPath() + "/" + result.getView());
        } else {
            req.getRequestDispatcher("/" + result.getView() + ".jsp").forward(req, resp);
        }
    }
}
