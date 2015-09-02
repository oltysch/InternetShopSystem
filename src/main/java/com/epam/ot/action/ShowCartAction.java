package com.epam.ot.action;

import com.epam.ot.dao.BulletDao;
import com.epam.ot.dao.DaoFactory;
import com.epam.ot.dao.GunDao;
import com.epam.ot.entity.Gun;
import com.epam.ot.entity.Product;
import com.epam.ot.entity.ShoppingCart;
import com.epam.ot.entity.ShoppingCartItem;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

public class ShowCartAction implements Action {
    private ActionResult result;

    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) {
        List<Product> products = new ArrayList<>();
        ShoppingCart shoppingCart = (ShoppingCart) req.getSession().getAttribute("cart");
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
                    products.add(product);
                    price += product.getPrice() * item.getCount();
                }
            }
            bulletDao.endTransaction();
            gunDao.endTransaction();
            req.setAttribute("products", products);
            req.setAttribute("price", Math.round(price));
            req.setAttribute("paidError", req.getAttribute("paidError"));
            result = new ActionResult("shopcart");
        } else {
            req.setAttribute("redirect_url", "shopcart");
            result = new ActionResult("", true);
        }
        return result;
    }
}
