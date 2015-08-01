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
        actions.put("POST/login", new LoginAction());
        actions.put("GET/logout", new LogoutAction());
        actions.put("GET/register", new ShowPageAction("register"));
        actions.put("GET/shop", new ShowPageAction("shop"));
    }

    public Action getAction(HttpServletRequest req) {
        return actions.get(req.getMethod() + req.getPathInfo());
    }
}
