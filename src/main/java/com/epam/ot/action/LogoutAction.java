package com.epam.ot.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LogoutAction implements Action {
    ActionResult result;

    public LogoutAction() {
        result = new ActionResult("", true);
    }

    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) {
        req.getSession().invalidate();
        return result;
    }
}
