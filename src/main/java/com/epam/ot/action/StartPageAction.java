package com.epam.ot.action;

import com.epam.ot.users.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class StartPageAction implements Action {
    private ActionResult result;
    //TODO check names everywhere - it's not show___, because redirect
    private String showIfLogged;
    private String showIfNotLogged;

    public StartPageAction(String showIfLogged, String showIfNotLogged) {
        this.showIfLogged = showIfLogged;
        this.showIfNotLogged = showIfNotLogged;
    }

    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) {
        User user = (User) req.getSession().getAttribute("user");
        if (user != null) {
            result = new ActionResult(showIfLogged, true);
        } else {
            result = new ActionResult(showIfNotLogged);
        }
        return result;
    }
}
