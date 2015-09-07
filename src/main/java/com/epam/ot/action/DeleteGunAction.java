package com.epam.ot.action;

import com.epam.ot.action.tools.EntityLoader;
import com.epam.ot.dao.DaoFactory;
import com.epam.ot.dao.GunDao;
import com.epam.ot.entity.Gun;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

public class DeleteGunAction implements Action {
    ActionResult actionResult;

    public DeleteGunAction() {
        actionResult = new ActionResult("edit_guns", true);
    }

    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) {
        Gun gun = EntityLoader.loadGunFromRequest(req);
        
        DaoFactory daoFactory = DaoFactory.getInstance();
        GunDao gunDao = daoFactory.createGunDao();
        gunDao.beginTransaction();
        gunDao.remove(gun);
        gunDao.endTransaction();
        return actionResult;
    }
}
