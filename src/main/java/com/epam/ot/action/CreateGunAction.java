package com.epam.ot.action;

import com.epam.ot.dao.DaoFactory;
import com.epam.ot.dao.GunDao;
import com.epam.ot.products.Gun;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

public class CreateGunAction implements Action {
    ActionResult actionResult = new ActionResult("edit_guns", true);

    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) {
        Gun gun = new Gun(req.getParameter("type"), req.getParameter("model"), Double.parseDouble(req.getParameter("price")), req.getParameter("origin"), req.getParameter("caliber"), Integer.parseInt(req.getParameter("magazineCapacity")), Integer.parseInt(req.getParameter("fireRate")), Integer.parseInt(req.getParameter("firingRange")), Integer.parseInt(req.getParameter("effectiveFiringRange")));
        gun.setUuid(UUID.randomUUID());
        DaoFactory daoFactory = DaoFactory.getInstance();
        GunDao gunDao = daoFactory.createGunDao();
        gunDao.beginTransaction();
        gunDao.insert(gun);
        gunDao.commitConnection();
        return actionResult;
    }
}
