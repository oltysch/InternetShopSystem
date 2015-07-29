package com.epam.ot;

import com.epam.ot.action.ActionFactory;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Servlet extends HttpServlet {
    ActionFactory actionFactory;

    @Override
    public void init() throws ServletException {
        actionFactory = new ActionFactory();
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        /*String res = "test info\n" + req + "\n" + req.getLocalName() + "\n" + req.getHeaderNames()
                + "\n" + req.getPathInfo() + "\n" + req.getMethod();
        resp.sendError(HttpServletResponse.SC_NOT_FOUND, res);*/
    }
}
