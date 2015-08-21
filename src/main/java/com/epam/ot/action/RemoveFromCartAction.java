package com.epam.ot.action;

import com.epam.ot.users.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RemoveFromCartAction implements Action {
    private static final Logger logger = LogManager.getLogger(RemoveFromCartAction.class);
    private ActionResult result = new ActionResult("shopcart", true);

    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) {
        String selectedProduct = (String) req.getParameter("selectedProductUuid");
        logger.info("selectedProductUuid=" + selectedProduct);
        User user = (User) req.getSession().getAttribute("user");
        logger.info("userLogin=" + user.getLogin());
        user.removeProduct(selectedProduct);
//        req.getSession().setAttribute("cart", guns);
        return result;
    }
}
