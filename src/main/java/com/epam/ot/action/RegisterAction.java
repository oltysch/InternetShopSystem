package com.epam.ot.action;

import com.epam.ot.dao.DaoFactory;
import com.epam.ot.dao.UserDao;
import com.epam.ot.users.Role;
import com.epam.ot.users.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.UUID;

public class RegisterAction implements Action {
    private ActionResult result;

    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) {
        String login = req.getParameter("login");
        String email = req.getParameter("email");
        String password = req.getParameter("password");

        DaoFactory daoFactory = DaoFactory.getInstance();
        UserDao userDao = daoFactory.createUserDao();
        User user = new User(login, email, Role.USER, password);
        user.setUuid(UUID.randomUUID());
        userDao.insert(user);
        req.setAttribute("login", user.getLogin());
        req.setAttribute("password", user.getPassword());
        return new ActionResult("success_register");

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
