package com.epam.ot.action;

import com.epam.ot.action.tools.ShoppingCartSerializer;
import com.epam.ot.dao.DaoFactory;
import com.epam.ot.dao.UserDao;
import com.epam.ot.entity.ShoppingCart;
import com.epam.ot.entity.User;
import com.epam.ot.util.PropertyManager;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ClearCartAction implements Action {
    public static final Logger logger = Logger.getLogger(PropertyManager.class);
    ActionResult result;

    public ClearCartAction() {
        result = new ActionResult("shopcart", true);
    }

    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) {
        ShoppingCart cart = (ShoppingCart) req.getSession().getAttribute("cart");

        cart.clearCart();
        try {
            User user = (User) req.getSession().getAttribute("user");
            user.setCart(ShoppingCartSerializer.writeCartInString(cart));

            DaoFactory daoFactory = DaoFactory.getInstance();
            UserDao userDao = daoFactory.createUserDao();
            userDao.beginTransaction();
            userDao.updateUser(user);
            userDao.endTransaction();
        } catch (IOException e) {
            logger.error("write cart error" + e);
        }
        return result;
    }
}
