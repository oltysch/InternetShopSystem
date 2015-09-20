package com.epam.ot.action;

import com.epam.ot.action.tools.Authorizer;
import com.epam.ot.dao.BulletDao;
import com.epam.ot.dao.DaoFactory;
import com.epam.ot.dao.GunDao;
import com.epam.ot.dao.UserDao;
import com.epam.ot.entity.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

public class ShowCartAction implements Action {
    private ActionResult result;

    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) {
        List<ProductBlock> products = new ArrayList<>();
        ShoppingCart shoppingCart = (ShoppingCart) req.getSession().getAttribute("cart");
        User user = (User) req.getSession().getAttribute("user");

        // refreshing user's data in db and in session
        Authorizer.refreshUserData(user);
        if (shoppingCart != null) {
            DaoFactory daoFactory = DaoFactory.getInstance();
            GunDao gunDao = daoFactory.createGunDao();
            BulletDao bulletDao = daoFactory.createBulletDao();
            gunDao.beginTransaction();
            bulletDao.beginTransaction();

            double price = 0;
            for (ShoppingCartItem item : shoppingCart.getProducts()) {
                Product product = gunDao.findByUuid(item.getProductUuid());
                if (product == null) product = bulletDao.findByUuid(item.getProductUuid());
                if (product != null) {
                    products.add(product.toBlock());
                    price += product.getPrice() * item.getCount();
                }
            }
            req.setAttribute("products", products);
            req.setAttribute("price", Math.round(price));
            req.setAttribute("paidError", req.getAttribute("paidError"));
            result = new ActionResult("shopcart");

            bulletDao.endTransaction();
            gunDao.endTransaction();
        } else {
            req.setAttribute("redirect_url", "shopcart");
            result = new ActionResult("", true);
        }
        return result;
    }
}
