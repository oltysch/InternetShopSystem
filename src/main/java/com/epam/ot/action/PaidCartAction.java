package com.epam.ot.action;

import com.epam.ot.action.tools.Authorizer;
import com.epam.ot.action.tools.ShoppingCartSerializer;
import com.epam.ot.dao.BulletDao;
import com.epam.ot.dao.DaoFactory;
import com.epam.ot.dao.GunDao;
import com.epam.ot.dao.UserDao;
import com.epam.ot.entity.Product;
import com.epam.ot.entity.ShoppingCart;
import com.epam.ot.entity.ShoppingCartItem;
import com.epam.ot.entity.User;
import com.epam.ot.util.PropertyManager;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PaidCartAction implements Action {
    public static final Logger logger = Logger.getLogger(PropertyManager.class);
    ActionResult result;

    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) {
        DaoFactory daoFactory = DaoFactory.getInstance();
        GunDao gunDao = daoFactory.createGunDao();
        BulletDao bulletDao = daoFactory.createBulletDao();
        gunDao.beginTransaction();
        bulletDao.beginTransaction();

        User user = (User) req.getSession().getAttribute("user");
        ShoppingCart shoppingCart = (ShoppingCart) req.getSession().getAttribute("cart");

        Authorizer.refreshUserData(user); // load's user cash from db
        double price = 0;
        // calculate total price
        for (ShoppingCartItem item : shoppingCart.getProducts()) {
            Product product = gunDao.findByUuid(item.getProductUuid()); // find product in gun's table
            if (product == null) product = bulletDao.findByUuid(item.getProductUuid()); // find product in bullets table
            if (product != null) {
                price += product.getPrice() * item.getCount(); // when we found - increment the total price on product price
            }
        }
        bulletDao.endTransaction();
        gunDao.endTransaction();
        double cash = user.getCash();
        // check whether the user has enough money
        if (cash >= price) {
            // and if true then refresh user's cash
            cash -= price;
            user.setCash(cash);
            // cleaning the shopping cart
            shoppingCart.clearCart();
            try {
                user.setCart(ShoppingCartSerializer.writeCartInString(shoppingCart));
            } catch (IOException e) {
                logger.error("write cart error" + e);
            }
            req.setAttribute("paidResult", "paid.result.success"); // set's message with paid results
            Authorizer.refreshUserData(user);
            result = new ActionResult("paid_result");
        } else {
            result = new ActionResult("paid_result");
            req.setAttribute("paidResult", "paid.result.fail");
        }
        return result;
    }
}
