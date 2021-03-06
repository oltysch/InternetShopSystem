package com.epam.ot.action;

import com.epam.ot.dao.DaoFactory;
import com.epam.ot.dao.UserDao;
import com.epam.ot.entity.Role;
import com.epam.ot.entity.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

public class MakeUserAction implements Action {
    private ActionResult result;

    public MakeUserAction() {
        result = new ActionResult("users", true);
    }

    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) {
        DaoFactory daoFactory = DaoFactory.getInstance();
        UserDao userDao = daoFactory.createUserDao();
        userDao.beginTransaction();

        String userUuid = req.getParameter("uuid");
        User user = userDao.findByUuid(UUID.fromString(userUuid));
        user.setRole(Role.USER);

        userDao.updateUser(user);
        userDao.endTransaction();
        return result;
    }
}
