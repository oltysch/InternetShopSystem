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
    public static final Logger logger = Logger.getLogger(Authorizer.class);
    private static PropertyManager propertyManager = new PropertyManager("connection.properties");

    /**
     * function for user authorization, before using this - need to check the user authentication
     *
     * @param user object for authorization
     * @param request
     * @param response
     */
    public static void authorizeUser(User user, HttpServletRequest request, HttpServletResponse response) {
        DaoFactory daoFactory = DaoFactory.getInstance();
        UserDao userDao = daoFactory.createUserDao();
        ShoppingCart cart;
        String xid = HashGenerator.generateHash(request.getSession().getId());
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

        request.getSession().setAttribute("user", user);
        request.getSession().setAttribute("cart", cart);
        Cookie xidCookie = new Cookie("xid", xid);

        Integer maxLife;
        try {
            maxLife = Integer.parseInt(propertyManager.getProperty("cookie.max.life"));
        } catch (NumberFormatException e) {
            maxLife = 60 * 60 * 24 * 7;
        }
        xidCookie.setMaxAge(maxLife);
        response.addCookie(xidCookie);
    }

    /**
     * just refreshes user's data from db <br>
     * <br>
     * loads user's cash and ban status from db <br>
     * and saves user cart into db
     *
     * @param user object which will be synchronized with db
     */
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
