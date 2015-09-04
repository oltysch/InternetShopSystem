package com.epam.ot;

import com.epam.ot.action.Action;
import com.epam.ot.action.ActionFactory;
import com.epam.ot.action.ActionResult;
import com.epam.ot.action.tools.CookieManager;
import com.epam.ot.entity.Role;
import com.epam.ot.entity.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AdminServlet extends HttpServlet {
    private ActionFactory actionFactory;

    @Override
    public void init() throws ServletException {
        actionFactory = new ActionFactory();
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String actionName = req.getMethod() + req.getServletPath() + req.getPathInfo();
        User user = (User) req.getSession().getAttribute("user");
        if (user != null && user.getRole() == Role.ADMIN) {

            Action action = actionFactory.getAction(actionName);
            ActionResult result = action.execute(req, resp);

            CookieManager.refreshLanguageCookies(req, resp);

            if (result.isRedirect()) {
                resp.sendRedirect(req.getContextPath() + req.getServletPath() + "/" + result.getView());
            } else {
                req.getRequestDispatcher("/WEB-INF/jsp/" + result.getView() + ".jsp").forward(req, resp);
            }
        } else {
            resp.sendError(HttpServletResponse.SC_FORBIDDEN);
        }
    }
}
