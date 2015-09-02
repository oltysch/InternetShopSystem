package com.epam.ot.action.tools;

import com.epam.ot.dao.DaoFactory;
import com.epam.ot.dao.UserDao;
import com.epam.ot.entity.ShoppingCart;
import com.epam.ot.entity.User;
import com.epam.ot.security.HashGenerator;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Authorizer {
    public static void authorizeUser(User user, HttpServletRequest req, HttpServletResponse resp) {
        DaoFactory daoFactory = DaoFactory.getInstance();
        UserDao userDao = daoFactory.createUserDao();
        ShoppingCart cart;
        String xid = HashGenerator.generateHash(req.getSession().getId());
        user.setXid(xid);

        if (user.getCart() != null && !user.getCart().equals("")) {
            cart = ShoppingCartSerializer.readCartFromString(user.getCart());
        } else {
            cart = new ShoppingCart();
        }
        user.setCart(ShoppingCartSerializer.writeCartInString(cart));

        userDao.beginTransaction();
        userDao.updateUser(user);
        userDao.endTransaction();

        req.getSession().setAttribute("user", user);
        req.getSession().setAttribute("cart", cart);
        Cookie xidCookie = new Cookie("xid", xid);
        //TODO load from properties
        xidCookie.setMaxAge(60 * 60 * 24 * 7);
        resp.addCookie(xidCookie);
    }
}
