package com.epam.ot.action;

import com.epam.ot.dao.DaoFactory;
import com.epam.ot.dao.GunDao;
import com.epam.ot.products.Gun;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

public class AddToCartAction implements Action {
    private ActionResult result;
    private static final Logger logger = LogManager.getLogger(AddToCartAction.class);

    public AddToCartAction() {
        result = new ActionResult("shop");
    }

    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) {
        //TODO create add logger and fix this
        int gunId = (int) req.getAttribute("gunId");
        logger.info("gunId=" + gunId);
        DaoFactory daoFactory = DaoFactory.getInstance();
        List<Gun> guns = new ArrayList<>();
        GunDao gunDao = daoFactory.createGunDao();
        guns.add(gunDao.findById(gunId));
        req.getSession().setAttribute("cart", guns);
        return result;
    }
}
