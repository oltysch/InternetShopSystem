package com.epam.ot.action;

import com.epam.ot.dao.BulletDao;
import com.epam.ot.dao.DaoFactory;
import com.epam.ot.dao.GunDao;
import com.epam.ot.products.Gun;
import com.epam.ot.products.Product;
import com.epam.ot.users.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class AddToCartAction implements Action {
    private static final Logger logger = LogManager.getLogger(AddToCartAction.class);
    private ActionResult result;

    public AddToCartAction() {
        //TODO need page refresh instead redirect;
        result = new ActionResult("products", true);
    }

    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) {
        String selectedProductUuid = req.getParameter("selectedProduct");
        String productType = req.getParameter("productType");
        logger.info("selectedProduct=" + selectedProductUuid);
        logger.info("productType=" + productType);
        User user = (User) req.getSession().getAttribute("user");
        logger.info("userLogin=" + user.getLogin());
        DaoFactory daoFactory = DaoFactory.getInstance();
        Product product;
        //TODO simplify this
        if (productType.equals("Bullet")) {
            BulletDao bulletDao = daoFactory.createBulletDao();
            product = bulletDao.findByUuid(UUID.fromString(selectedProductUuid));
        } else /*(productType.equals("Gun"))*/ {
            GunDao gunDao = daoFactory.createGunDao();
            product = gunDao.findByUuid(UUID.fromString(selectedProductUuid));
        }
        user.addProduct(product);
//        req.getSession().setAttribute("cart", guns);
        return result;
    }
}
