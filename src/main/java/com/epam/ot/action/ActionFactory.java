package com.epam.ot.action;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

public class ActionFactory {

    static Map<String, Action> actions;

    static {
        actions = new HashMap<>();
        actions.put("GET/", new ShowPageAction("guns"));
        actions.put("GET/login", new ShowPageAction("login"));
        actions.put("POST/login", new LoginAction("products", "login"));
        actions.put("GET/logout", new LogoutAction());
        actions.put("GET/register", new ShowPageAction("register"));
        actions.put("POST/register", new RegisterAction());
        actions.put("GET/success_register", new ShowPageAction("success_register"));
        actions.put("GET/products", new ShowShopAction());
        actions.put("POST/addToCart", new AddToCartAction());
        actions.put("POST/removeFromCart", new RemoveFromCartAction());
        actions.put("GET/shopcart", new ShowPageAction("shopcart"));
    }

    public Action getAction(HttpServletRequest req) {
        return actions.get(req.getMethod() + req.getPathInfo());
    }
}
