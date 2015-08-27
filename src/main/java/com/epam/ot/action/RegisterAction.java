package com.epam.ot.action;

import com.epam.ot.dao.DaoFactory;
import com.epam.ot.dao.UserDao;
import com.epam.ot.entity.Role;
import com.epam.ot.entity.User;
import com.epam.ot.util.PropertyManager;
import com.epam.ot.validator.Validator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;
import java.util.UUID;

public class RegisterAction implements Action {
    private ActionResult result;

    public RegisterAction() {
        result = new ActionResult("success_register");
    }

    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) {
        String login = req.getParameter("login");
        String email = req.getParameter("email");
        String password = req.getParameter("password");

        String registerError;
        registerError = Validator.isLoginValid(login);
        if (registerError == null) registerError = Validator.isEmailValid(email);
        if (registerError == null) registerError = Validator.isPasswordValid(password);

        if (registerError != null) {
            req.setAttribute("registerError", registerError);
            return new ActionResult("register");
        }

        DaoFactory daoFactory = DaoFactory.getInstance();
        UserDao userDao = daoFactory.createUserDao();
        User user = new User(login, email, Role.USER, password);
        user.setUuid(UUID.randomUUID());
        userDao.insert(user);
        req.setAttribute("login", user.getLogin());
        req.setAttribute("password", user.getPassword());
        return result;
    }
}
