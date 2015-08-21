package com.epam.ot.action;

import com.epam.ot.dao.DaoFactory;
import com.epam.ot.dao.UserDao;
import com.epam.ot.users.Role;
import com.epam.ot.users.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

public class CreateUserAction implements Action {
    ActionResult actionResult = new ActionResult("users", true);

    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) {
        User user = new User(req.getParameter("login"), req.getParameter("email"), Role.valueOf(req.getParameter("role")), req.getParameter("password"));
        user.setUuid(UUID.randomUUID());
        DaoFactory daoFactory = DaoFactory.getInstance();
        UserDao userDao = daoFactory.createUserDao();
        userDao.beginTransaction();
        userDao.insert(user);
        userDao.commitConnection();
        return actionResult;
    }
}
