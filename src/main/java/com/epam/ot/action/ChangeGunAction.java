package com.epam.ot.action;

import com.epam.ot.action.tools.EntityLoader;
import com.epam.ot.dao.DaoFactory;
import com.epam.ot.dao.GunDao;
import com.epam.ot.entity.Gun;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ChangeGunAction implements Action {
    ActionResult actionResult;

    public ChangeGunAction(String page) {
        actionResult = new ActionResult(page, true);
    }

    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) {
        DaoFactory daoFactory = DaoFactory.getInstance();
        GunDao gunDao = daoFactory.createGunDao();
        gunDao.beginTransaction();

        Gun gun = EntityLoader.loadGunFromRequest(req);
        gunDao.update(gun);

        gunDao.endTransaction();
        return actionResult;
    }
}
