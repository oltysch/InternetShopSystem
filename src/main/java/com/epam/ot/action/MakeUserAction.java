package com.epam.ot.action;

import com.epam.ot.dao.DaoFactory;
import com.epam.ot.dao.UserDao;
import com.epam.ot.users.Role;
import com.epam.ot.users.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

public class MakeUserAction implements Action {
    private ActionResult result;

    public MakeUserAction() {
        result = new ActionResult("edit_users", true);
    }

    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) {
        String userUuid = req.getParameter("uuid");
        DaoFactory daoFactory = DaoFactory.getInstance();
        UserDao userDao = daoFactory.createUserDao();
        User user = userDao.findByUuid(UUID.fromString(userUuid));
        user.setRole(Role.USER);
        userDao.updateUser(user);
        return result;
    }
}
