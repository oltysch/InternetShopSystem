package com.epam.ot.action;

import com.epam.ot.action.tools.Authorizer;
import com.epam.ot.action.tools.CookieManager;
import com.epam.ot.dao.DaoFactory;
import com.epam.ot.dao.UserDao;
import com.epam.ot.entity.User;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ShowLoginPageOrAuthorize implements Action {
    private ActionResult result;

    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) {
        User user = (User) req.getSession().getAttribute("user");
        if (user == null) {
            reopenSession(req, resp);
        } else {
            result = new ActionResult("products", true);
        }
        return result;
    }

    private void reopenSession(HttpServletRequest req, HttpServletResponse resp) {
        Cookie myCookie = CookieManager.findCookie(req, "xid");
        if (myCookie != null) {
            DaoFactory daoFactory = DaoFactory.getInstance();
            UserDao userDao = daoFactory.createUserDao();
            userDao.beginTransaction();

            User user = userDao.findByXid(myCookie.getValue());
            if (user != null) {
                Authorizer.authorizeUser(user, req, resp);
                result = new ActionResult("products", true);
            } else {
                result = new ActionResult("start_page");
            }

            userDao.endTransaction();
        } else {
            result = new ActionResult("start_page");
        }
    }
}
