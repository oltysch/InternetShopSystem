package com.epam.ot.action;

import com.epam.ot.action.tools.EntityLoader;
import com.epam.ot.dao.DaoFactory;
import com.epam.ot.dao.GunDao;
import com.epam.ot.entity.Gun;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

public class CreateGunAction implements Action {
    ActionResult actionResult;

    public CreateGunAction() {
        actionResult = new ActionResult("edit_guns", true);
    }

    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) {
        DaoFactory daoFactory = DaoFactory.getInstance();
        GunDao gunDao = daoFactory.createGunDao();
        gunDao.beginTransaction();

        Gun gun = EntityLoader.loadGunFromRequest(req);
        gun.setUuid(UUID.randomUUID());
        gunDao.insert(gun);

        gunDao.endTransaction();
        return actionResult;
    }
}
