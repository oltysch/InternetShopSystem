package com.epam.ot.security;

import com.epam.ot.dao.DaoFactory;
import com.epam.ot.dao.UserDao;
import com.epam.ot.entity.ShoppingCart;
import com.epam.ot.entity.User;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Authorizer {
    public static void authorizeUser(User user, HttpServletRequest req, HttpServletResponse resp) {
        DaoFactory daoFactory = DaoFactory.getInstance();
        UserDao userDao = daoFactory.createUserDao();
        ShoppingCart cart = new ShoppingCart();
        //TODO make this random
        String xid = "udfyuiosdyty6tafuifteyfsef";
        user.setXid(xid);
        userDao.beginTransaction();
        userDao.updateUser(user);
        userDao.endTransaction();
        req.getSession().setAttribute("user", user);
        req.getSession().setAttribute("cart", cart);
        Cookie cookie = new Cookie("xid", xid);
        cookie.setMaxAge(60 * 60 * 24 * 7);
        resp.addCookie(cookie);
    }
}
