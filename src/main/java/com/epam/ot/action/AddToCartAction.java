package com.epam.ot.action;

import com.epam.ot.action.tools.ShoppingCartSerializer;
import com.epam.ot.dao.DaoFactory;
import com.epam.ot.dao.UserDao;
import com.epam.ot.entity.ShoppingCart;
import com.epam.ot.entity.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

public class AddToCartAction implements Action {
    private static final Logger logger = LogManager.getLogger(AddToCartAction.class);
    private ActionResult result;

    public AddToCartAction() {
        result = new ActionResult("products", true);
    }

    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) {
        UUID selectedProductUuid = UUID.fromString(req.getParameter("selectedProductUuid"));
        logger.info("selectedProductUuid=" + selectedProductUuid);
        ShoppingCart cart = (ShoppingCart) req.getSession().getAttribute("cart");
        User user = (User) req.getSession().getAttribute("user");
        cart.addProduct(selectedProductUuid);
        user.setCart(ShoppingCartSerializer.writeCartInString(cart));
        DaoFactory daoFactory = DaoFactory.getInstance();
        UserDao userDao = daoFactory.createUserDao();
        userDao.beginTransaction();
        userDao.updateUser(user);
        userDao.endTransaction();
        return result;
    }
}
