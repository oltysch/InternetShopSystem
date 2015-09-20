package com.epam.ot.action;

import com.epam.ot.action.tools.Authorizer;
import com.epam.ot.dao.DaoFactory;
import com.epam.ot.dao.UserDao;
import com.epam.ot.entity.User;
import com.epam.ot.security.PasswordHashing;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginAction implements Action {
    ActionResult result;

    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) {
        DaoFactory daoFactory = DaoFactory.getInstance();
        UserDao userDao = daoFactory.createUserDao();
        userDao.beginTransaction();

        String login = req.getParameter("login");
        User user = userDao.findByLogin(login);
        String password = req.getParameter("password");
        if (user == null || !(PasswordHashing.validatePassword(password, user.getPassword()))) {
            req.setAttribute("login", login);
            req.setAttribute("loginError", "error.wrong_login");
            result = new ActionResult("login");
        } else if (!user.isBanned()) {
            Authorizer.authorizeUser(user, req, resp);
            result = new ActionResult("products", true);
        } else {
            req.setAttribute("login", login);
            req.setAttribute("loginError", "error.user_banned");
            result = new ActionResult("login");
        }

        userDao.endTransaction();
        return result;
    }
}
