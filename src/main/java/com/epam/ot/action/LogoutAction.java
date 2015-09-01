package com.epam.ot.action;

import com.epam.ot.dao.DaoFactory;
import com.epam.ot.dao.UserDao;
import com.epam.ot.entity.User;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LogoutAction implements Action {
    ActionResult result;

    public LogoutAction() {
        result = new ActionResult("", true);
    }

    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) {
//        Cookie cookie = new Cookie("xid", "");
//        cookie.setMaxAge(0);
//        resp.addCookie(cookie);
        HttpSession session = req.getSession();
        if (session != null) {
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
                myCookie.setValue("");
                myCookie.setMaxAge(0);
                resp.addCookie(myCookie);
            }
            User user = (User) req.getSession().getAttribute("user");
            user.setXid("");
            DaoFactory daoFactory = DaoFactory.getInstance();
            UserDao userDao = daoFactory.createUserDao();
            userDao.beginTransaction();
            userDao.updateUser(user);
            userDao.endTransaction();
            req.getSession().invalidate();
        }
        return result;
    }
}
