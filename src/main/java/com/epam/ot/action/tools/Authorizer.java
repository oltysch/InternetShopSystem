package com.epam.ot.action.tools;

import com.epam.ot.dao.DaoFactory;
import com.epam.ot.dao.UserDao;
import com.epam.ot.entity.ShoppingCart;
import com.epam.ot.entity.User;
import com.epam.ot.security.HashGenerator;
import com.epam.ot.util.PropertyManager;
import org.apache.log4j.Logger;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Authorizer {
    private static PropertyManager propertyManager = new PropertyManager("connection.properties");
    public static final Logger logger = Logger.getLogger(PropertyManager.class);

    public static void authorizeUser(User user, HttpServletRequest req, HttpServletResponse resp) {
        DaoFactory daoFactory = DaoFactory.getInstance();
        UserDao userDao = daoFactory.createUserDao();
        ShoppingCart cart;
        String xid = HashGenerator.generateHash(req.getSession().getId());
        user.setXid(xid);

        try {
            cart = ShoppingCartSerializer.readCartFromString(user.getCart());
        } catch (Throwable e) {
            logger.error("reading cart error" + e);
            cart = new ShoppingCart();
        }

        try {
            user.setCart(ShoppingCartSerializer.writeCartInString(cart));
        } catch (IOException e) {
            e.printStackTrace();
        }

        userDao.beginTransaction();
        userDao.updateUser(user);
        userDao.endTransaction();

        req.getSession().setAttribute("user", user);
        req.getSession().setAttribute("cart", cart);
        Cookie xidCookie = new Cookie("xid", xid);

        Integer maxLife;
        try {
            maxLife = Integer.parseInt(propertyManager.getProperty("cookie.max.life"));
        } catch (NumberFormatException e) {
            maxLife = 60 * 60 * 24 * 7;
        }
        xidCookie.setMaxAge(maxLife);
        resp.addCookie(xidCookie);
    }

    public static void refreshUserData(User user) {
        DaoFactory daoFactory = DaoFactory.getInstance();
        UserDao userDao = daoFactory.createUserDao();
        userDao.beginTransaction();
        User userInDB = userDao.findByUuid(user.getUuid());
        user.setCash(userInDB.getCash());
        user.setBanned(userInDB.isBanned());
        userInDB.setCart(user.getCart());
        userDao.updateUser(userInDB);
        userDao.endTransaction();
    }
}
