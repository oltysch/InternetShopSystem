package com.epam.ot.action;

import com.epam.ot.dao.DaoFactory;
import com.epam.ot.dao.UserDao;
import com.epam.ot.entity.Role;
import com.epam.ot.entity.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

public class DeleteUserAction implements Action {
    ActionResult actionResult;

    public DeleteUserAction() {
        actionResult = new ActionResult("edit_users", true);
    }

    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) {
        User user = new User(req.getParameter("login"), req.getParameter("email"), Role.valueOf(req.getParameter("role")), req.getParameter("password"));
        user.setUuid(UUID.fromString(req.getParameter("uuid")));
        DaoFactory daoFactory = DaoFactory.getInstance();
        UserDao userDao = daoFactory.createUserDao();
        userDao.beginTransaction();
        userDao.remove(user);
        userDao.endTransaction();
        return actionResult;
    }
}
