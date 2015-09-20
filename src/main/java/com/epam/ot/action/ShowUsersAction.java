package com.epam.ot.action;

import com.epam.ot.dao.DaoFactory;
import com.epam.ot.dao.UserDao;
import com.epam.ot.dao.UsersRolesDao;
import com.epam.ot.entity.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

public class ShowUsersAction implements Action {
    private ActionResult result;

    public ShowUsersAction(String path) {
        result = new ActionResult(path);
    }

    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) {
        DaoFactory daoFactory = DaoFactory.getInstance();
        UserDao userDao = daoFactory.createUserDao();
        UsersRolesDao usersRolesDao = daoFactory.createUsersRolesDao();
        userDao.beginTransaction();
        usersRolesDao.beginTransaction();

        List<User> users = new ArrayList<>();
        List<String> roles = new ArrayList<>();
        users.addAll(userDao.findAll());
        roles.addAll(usersRolesDao.findAll());
        req.setAttribute("users", users);
        req.setAttribute("roles", roles);

        usersRolesDao.endTransaction();
        userDao.endTransaction();
        return result;
    }
}
