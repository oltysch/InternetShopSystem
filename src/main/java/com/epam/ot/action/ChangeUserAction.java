package com.epam.ot.action;

import com.epam.ot.action.tools.EntityLoader;
import com.epam.ot.dao.DaoFactory;
import com.epam.ot.dao.UserDao;
import com.epam.ot.entity.Role;
import com.epam.ot.entity.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

public class ChangeUserAction implements Action {
    ActionResult actionResult;

    public ChangeUserAction() {
        actionResult = new ActionResult("edit_users", true);
    }

    @Override
    public ActionResult execute(HttpServletRequest request, HttpServletResponse response) {
        DaoFactory daoFactory = DaoFactory.getInstance();
        UserDao userDao = daoFactory.createUserDao();
        userDao.beginTransaction();

        User user = EntityLoader.loadUserFromRequest(request);
        userDao.updateUser(user);

        userDao.endTransaction();
        return actionResult;
    }
}
