package com.epam.ot.action;

public class ActionResult {
    private final String view;
    private final Boolean redirect;

    public ActionResult(String page, boolean redirect) {
        this.view = page;
        this.redirect = redirect;
    }

    public ActionResult(String view) {
        this.view = view;
        this.redirect = false;
    }

    public Boolean isRedirect() {
        return redirect;
    }

    public String getView() {
        return view;
    }
}
