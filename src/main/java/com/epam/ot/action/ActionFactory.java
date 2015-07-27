package com.epam.ot.action;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

//TODO make action factory
public class ActionFactory {

    static Map<String, Action> actions;

    static {
        //TODO make actions classes
        actions.put("", new LoginAction());
    }

    public Action getAction(HttpServletRequest req) {
        return actions.get(req);
    }
}
