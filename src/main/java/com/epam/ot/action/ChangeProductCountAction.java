package com.epam.ot.action;

import com.epam.ot.entity.ShoppingCart;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

public class ChangeProductCountAction implements Action {
    ActionResult result;

    public ChangeProductCountAction() {
        result = new ActionResult("shopcart", true);
    }

    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) {
        String productUuid = req.getParameter("selectedProductUuid");
        Integer newCount = null;
        try {
            newCount = Integer.parseInt(req.getParameter("newCount"));
        } catch (NumberFormatException e) {
        }
        ShoppingCart cart = (ShoppingCart) req.getSession().getAttribute("cart");

        if (newCount != null) {
            cart.setProductCount(UUID.fromString(productUuid), newCount);
        }

        return result;
    }
}
