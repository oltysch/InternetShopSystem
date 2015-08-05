package com.epam.ot.action;

import com.epam.ot.dao.DaoFactory;
import com.epam.ot.dao.GunDao;
import com.epam.ot.products.Gun;
import com.epam.ot.users.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

public class ShowShopAction implements Action {
    private ActionResult result;

    public ShowShopAction() {
        result = new ActionResult("shop");
    }

    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) {
        List<Gun> guns;
        DaoFactory daoFactory = DaoFactory.getInstance();
        GunDao gunDao = daoFactory.createGunDao();
        guns = gunDao.findAll();
        req.setAttribute("guns", guns);
        return result;
    }
}
