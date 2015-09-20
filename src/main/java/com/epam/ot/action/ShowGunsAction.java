package com.epam.ot.action;

import com.epam.ot.dao.DaoFactory;
import com.epam.ot.dao.GunDao;
import com.epam.ot.dao.GunsTypesDao;
import com.epam.ot.entity.Gun;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

public class ShowGunsAction implements Action {
    private ActionResult result;

    public ShowGunsAction(String path) {
        result = new ActionResult(path);
    }

    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) {
        DaoFactory daoFactory = DaoFactory.getInstance();
        GunDao gunDao = daoFactory.createGunDao();
        GunsTypesDao gunsTypesDao = daoFactory.createGunsTypesDao();
        gunDao.beginTransaction();
        gunsTypesDao.beginTransaction();

        List<Gun> guns = new ArrayList<>();
        List<String> types = new ArrayList<>();
        guns.addAll(gunDao.findAll());
        types.addAll(gunsTypesDao.findAll());
        req.setAttribute("guns", guns);
        req.setAttribute("types", types);

        gunDao.endTransaction();
        gunsTypesDao.endTransaction();
        return result;
    }
}
