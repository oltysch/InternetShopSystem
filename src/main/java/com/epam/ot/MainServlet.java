package com.epam.ot;

import com.epam.ot.action.Action;
import com.epam.ot.action.ActionFactory;
import com.epam.ot.action.ActionResult;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
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
        if (result.isRedirect()) {
            resp.sendRedirect(req.getContextPath() + "/gunshop/" + result.getView());
        } else {
            req.getRequestDispatcher("/" + result.getView() + ".jsp").forward(req, resp);
        }
    }
}
