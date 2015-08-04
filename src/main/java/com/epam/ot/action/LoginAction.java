package com.epam.ot.action;

import com.epam.ot.dao.DaoFactory;
import com.epam.ot.dao.DaoManager;
import com.epam.ot.users.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginAction implements Action {
    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) {
        String login = req.getParameter("login");
        String password = req.getParameter("password");

        DaoFactory daoFactory = DaoFactory.getInstance();
        DaoManager daoManager = daoFactory.createDaoManager();
        daoManager.beginTransaction();
        User user = daoManager.getUserDao().findByAccount(login, password);
        daoManager.commit();
//        daoManager.rollback();
        daoFactory.releaseConnection(daoManager);

        if (user != null) {
            req.getSession().setAttribute("login", login);
            return new ActionResult("shop", true);
        } else {
            req.setAttribute("loginError", "Wrong login or password!");
            return new ActionResult("login");
        }
    }
}
