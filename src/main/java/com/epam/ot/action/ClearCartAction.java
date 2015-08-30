package com.epam.ot.action;

import com.epam.ot.entity.ShoppingCart;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ClearCartAction implements Action {
    ActionResult result;

    public ClearCartAction() {
        result = new ActionResult("shopcart", true);
    }

    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) {
        ShoppingCart cart = (ShoppingCart) req.getSession().getAttribute("cart");
        cart.clearCart();
        return result;
    }
}
