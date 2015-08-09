package com.epam.ot.action;

import com.epam.ot.dao.DaoFactory;
import com.epam.ot.dao.UserDao;
import com.epam.ot.users.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginAction implements Action {
    private String showIfSuccess;
    private String shofIfWrongLogin;

    public LoginAction(String showIfSuccess, String shofIfWrongLogin) {
        this.showIfSuccess = showIfSuccess;
        this.shofIfWrongLogin = shofIfWrongLogin;
    }

    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) {
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        DaoFactory daoFactory = DaoFactory.getInstance();
        UserDao userDao = daoFactory.createUserDao();
        User user = userDao.findByAccount(login, password);

        if (user != null) {
            req.getSession().setAttribute("user", user);
            return new ActionResult(showIfSuccess, true);
        } else {
            req.setAttribute("login", login);
            req.setAttribute("loginError", "Wrong login or password!");
            return new ActionResult(shofIfWrongLogin);
        }
    }
}
