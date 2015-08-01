package com.epam.ot.action;

import com.epam.ot.dao.DaoFactory;
import com.epam.ot.dao.JdbcDaoFactory;
import com.epam.ot.dao.UserDao;
import com.epam.ot.users.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginAction implements Action {
    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) {
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        //TODO fix this all
        DaoFactory daoFactory = DaoFactory.getInstance();
        daoFactory.beginConnectionScope();
        daoFactory.beginTransaction();
        UserDao userDao = daoFactory.createUserDao();
        User user = userDao.findByLogin(login);
        daoFactory.endTransaction();
        daoFactory.endConnectionScope();
//        User user = new User("test", "", "test");

        if (user != null) {
            req.getSession().setAttribute("user", user);
            return new ActionResult("shop", true);
        } else {
            req.setAttribute("loginError", "wrong password");
            return new ActionResult("login");
        }
    }
}
