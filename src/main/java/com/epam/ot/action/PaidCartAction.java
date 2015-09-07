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
        List<Product> products = new ArrayList<>();
        User user = (User) req.getSession().getAttribute("user");

        DaoFactory daoFactory = DaoFactory.getInstance();

        Authorizer.refreshUserData(user);

        ShoppingCart shoppingCart = (ShoppingCart) req.getSession().getAttribute("cart");
        GunDao gunDao = daoFactory.createGunDao();
        BulletDao bulletDao = daoFactory.createBulletDao();
        gunDao.beginTransaction();
        bulletDao.beginTransaction();
        double price = 0;
        for (ShoppingCartItem item : shoppingCart.getProducts()) {
            Product product = gunDao.findByUuid(item.getProductUuid());
            if (product == null) product = bulletDao.findByUuid(item.getProductUuid());
            if (product != null) {
                products.add(product);
                price += product.getPrice() * item.getCount();
            }
        }
        bulletDao.endTransaction();
        gunDao.endTransaction();
        double cash = user.getCash();
        if (cash >= price) {
            cash -= price;
            user.setCash(cash);
            shoppingCart.clearCart();
            try {
                user.setCart(ShoppingCartSerializer.writeCartInString(shoppingCart));
            } catch (IOException e) {
                logger.error("write cart error" + e);
            }
            req.setAttribute("paidResult", "paid.result.success");
            result = new ActionResult("paid_result");
            UserDao userDao = daoFactory.createUserDao();
            userDao.beginTransaction();
            userDao.updateUser(user);
            userDao.endTransaction();
        } else {
            result = new ActionResult("paid_result");
            req.setAttribute("paidResult", "paid.result.fail");
        }
        return result;
    }
}
