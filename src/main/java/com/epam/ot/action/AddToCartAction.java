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
import java.util.UUID;

public class AddToCartAction implements Action {
    public static final Logger logger = Logger.getLogger(PropertyManager.class);
    private ActionResult result;

    public AddToCartAction() {
        result = new ActionResult("products", true);
    }

    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) {
        // getting the uuid of selected product
        UUID selectedProductUuid = UUID.fromString(req.getParameter("selectedProductUuid"));
        logger.info("selectedProductUuid=" + selectedProductUuid);
        // getting shopping cart and user
        ShoppingCart cart = (ShoppingCart) req.getSession().getAttribute("cart");
        User user = (User) req.getSession().getAttribute("user");

        // add selected product into cart
        cart.addProduct(selectedProductUuid);
        try {
            user.setCart(ShoppingCartSerializer.writeCartInString(cart));
        } catch (IOException e) {
            logger.error("write cart error" + e);
        }

        // update user's data in db
        DaoFactory daoFactory = DaoFactory.getInstance();
        UserDao userDao = daoFactory.createUserDao();
        userDao.beginTransaction();
        userDao.updateUser(user);
        userDao.endTransaction();
        return result;
    }
}
