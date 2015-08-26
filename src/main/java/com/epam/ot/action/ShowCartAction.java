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

    public ShowCartAction() {
        this.result = new ActionResult("shopcart");
    }

    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) {
        List<Product> products = new ArrayList<>();
        ShoppingCart shoppingCart = (ShoppingCart) req.getSession().getAttribute("cart");
        DaoFactory daoFactory = DaoFactory.getInstance();
        GunDao gunDao = daoFactory.createGunDao();
        BulletDao bulletDao = daoFactory.createBulletDao();
        for (ShoppingCartItem item : shoppingCart.getProducts()) {
            Product product = gunDao.findByUuid(item.getProductUuid());
            if (product == null) product = bulletDao.findByUuid(item.getProductUuid());
            if (product != null) products.add(product);
        }
        req.setAttribute("products", products);
        return result;
    }
}
