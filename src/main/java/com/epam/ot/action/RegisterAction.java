package com.epam.ot.action;

import com.epam.ot.dao.DaoFactory;
import com.epam.ot.dao.DaoManager;
import com.epam.ot.users.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class RegisterAction implements Action {
    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) {
        String login = req.getParameter("login");
        String email = req.getParameter("email");
        String password = req.getParameter("password");

        DaoFactory daoFactory = DaoFactory.getInstance();
        DaoManager daoManager = daoFactory.createDaoManager();
        User user = new User(login, email, password);
        daoManager.beginTransaction();
        daoManager.getUserDao().insert(user);
        daoManager.commit();
//        daoManager.rollback();
        daoFactory.releaseConnection(daoManager);
        req.getSession().setAttribute("login", login);
        return new ActionResult("success_register", true);

        /*TODO make login email and password checking
        if (user != null) {
            req.getSession().setAttribute("user", user);
            return new ActionResult("shop", true);
        } else {
            req.setAttribute("loginError", "Wrong login or password!");
            return new ActionResult("login");
        }*/
    }
}
