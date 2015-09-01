package com.epam.ot.action;

import com.epam.ot.dao.DaoFactory;
import com.epam.ot.dao.UserDao;
import com.epam.ot.entity.User;
import com.epam.ot.security.Authorizer;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ShowLoginPageOrAuthorize implements Action {
    private ActionResult result;

    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) {
        String redirect_url = (String) req.getAttribute("redirect_url");
        if (redirect_url == null || redirect_url == "") {
            redirect_url = "products";
        }

        Cookie[] cookies = req.getCookies();
        Cookie myCookie = null;
        if (cookies != null && cookies.length > 0) {
            for (int i = 0; i < cookies.length; i++) {
                if (cookies[i].getName().equals("xid")) {
                    myCookie = cookies[i];
                    break;
                }
            }
        }
        if (myCookie != null) {
            DaoFactory daoFactory = DaoFactory.getInstance();
            UserDao userDao = daoFactory.createUserDao();
            userDao.beginTransaction();
            User user = userDao.findByXid(myCookie.getValue());
            if (user != null) {
                Authorizer.authorizeUser(user, req, resp);
                result = new ActionResult(redirect_url, true);
            } else {
                result = new ActionResult("login");
            }
            userDao.endTransaction();
        } else {
            result = new ActionResult("login");
        }

        return result;
    }
}
