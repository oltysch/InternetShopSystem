package com.epam.ot.action;

import com.epam.ot.users.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RemoveFromCartCation implements Action {
    private static final Logger logger = LogManager.getLogger(RemoveFromCartCation.class);
    private ActionResult result;

    public RemoveFromCartCation() {
        result = new ActionResult("shopcart", true);
    }

    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) {
        Integer selectedGun = (Integer) Integer.parseInt((String) req.getParameter("selectedGunId"));
        logger.info("selectedGunId=" + selectedGun);
        User user = (User) req.getSession().getAttribute("user");
        logger.info("userLogin=" + user.getLogin());
        user.removeProduct(selectedGun);
//        req.getSession().setAttribute("cart", guns);
        return result;
    }
}
