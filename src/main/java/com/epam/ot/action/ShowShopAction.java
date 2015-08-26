package com.epam.ot.action;

import com.epam.ot.dao.BulletDao;
import com.epam.ot.dao.DaoFactory;
import com.epam.ot.dao.GunDao;
import com.epam.ot.entity.Product;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

public class ShowShopAction implements Action {
    private ActionResult result = new ActionResult("shop");

    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) {
        List<Product> products = new ArrayList<>();
        String selectType = (String) req.getParameter("seltp");
        String productType = (String) req.getParameter("prType");
        DaoFactory daoFactory = DaoFactory.getInstance();
        GunDao gunDao = daoFactory.createGunDao();
        BulletDao bulletDao = daoFactory.createBulletDao();
        if (selectType != null) {
            products.addAll(gunDao.findByType(selectType));
            products.addAll(bulletDao.findByType(selectType));
        } else if (productType != null && productType.equals("guns")) {
            products.addAll(gunDao.findAll());
        } else if (productType != null && productType.equals("bullets")) {
            products.addAll(bulletDao.findAll());
        } else {
            products.addAll(gunDao.findAll());
            products.addAll(bulletDao.findAll());
        }
        req.setAttribute("products", products);
        return result;
    }
}
