package com.epam.ot.action;

import com.epam.ot.dao.DaoFactory;
import com.epam.ot.dao.GunDao;
import com.epam.ot.products.Gun;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

public class ShowGunsAction implements Action {
    private ActionResult result = new ActionResult("WEB-INF/admin/view_guns");

    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) {
        List<Gun> guns = new ArrayList<>();
        DaoFactory daoFactory = DaoFactory.getInstance();
        GunDao gunDao = daoFactory.createGunDao();
        guns.addAll(gunDao.findAll());
        req.setAttribute("guns", guns);
        return result;
    }
}
