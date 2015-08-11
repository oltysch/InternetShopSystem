package com.epam.ot.action;

import com.epam.ot.dao.BulletDao;
import com.epam.ot.dao.DaoFactory;
import com.epam.ot.dao.GunDao;
import com.epam.ot.products.Gun;
import com.epam.ot.products.Product;
import com.epam.ot.users.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

public class ShowShopAction implements Action {
    private ActionResult result;

    public ShowShopAction() {
        result = new ActionResult("shop");
    }

    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) {
        //TODO Use List of Products
        List<Product> products = new ArrayList<>();
        DaoFactory daoFactory = DaoFactory.getInstance();
        GunDao gunDao = daoFactory.createGunDao();
        BulletDao bulletDao = daoFactory.createBulletDao();
        products.addAll(gunDao.findAll());
        products.addAll(bulletDao.findAll());
        req.setAttribute("products", products);
        return result;
    }
}
