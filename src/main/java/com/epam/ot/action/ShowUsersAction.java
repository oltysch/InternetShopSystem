package com.epam.ot.action;

import com.epam.ot.dao.DaoFactory;
import com.epam.ot.dao.UserDao;
import com.epam.ot.users.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

public class ShowUsersAction implements Action {
    private ActionResult result;

    public ShowUsersAction() {
        result = new ActionResult("WEB-INF/admin/edit_users");
    }

    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) {
        List<User> users = new ArrayList<>();
        DaoFactory daoFactory = DaoFactory.getInstance();
        UserDao userDao = daoFactory.createUserDao();
        users.addAll(userDao.findAll());
        req.setAttribute("users", users);
        return result;
    }
}
