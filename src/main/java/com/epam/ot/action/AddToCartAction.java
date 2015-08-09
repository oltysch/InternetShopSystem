package com.epam.ot.action;

import com.epam.ot.dao.DaoFactory;
import com.epam.ot.dao.GunDao;
import com.epam.ot.products.Gun;
import com.epam.ot.users.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

public class AddToCartAction implements Action {
    private static final Logger logger = LogManager.getLogger(AddToCartAction.class);
    private ActionResult result;

    public AddToCartAction() {
        //TODO need page refresh instead redirect;
        result = new ActionResult("shop", true);
    }

    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) {
        Integer selectedGun = (Integer) Integer.parseInt((String) req.getParameter("selectedGunId"));
        logger.info("selectedGunId=" + selectedGun);
        User user = (User) req.getSession().getAttribute("user");
        logger.info("userLogin=" + user.getLogin());
        DaoFactory daoFactory = DaoFactory.getInstance();
        GunDao gunDao = daoFactory.createGunDao();
        Gun gun = gunDao.findById(selectedGun);
        user.addProduct(gun);
//        req.getSession().setAttribute("cart", guns);
        return result;
    }
}
