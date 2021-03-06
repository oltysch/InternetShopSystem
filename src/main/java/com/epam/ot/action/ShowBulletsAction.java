package com.epam.ot.action;

import com.epam.ot.dao.BulletDao;
import com.epam.ot.dao.BulletsTypesDao;
import com.epam.ot.dao.DaoFactory;
import com.epam.ot.entity.Bullet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

public class ShowBulletsAction implements Action {
    private ActionResult result;

    public ShowBulletsAction(String path) {
        result = new ActionResult(path);
    }

    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) {
        DaoFactory daoFactory = DaoFactory.getInstance();
        BulletDao bulletDao = daoFactory.createBulletDao();
        BulletsTypesDao bulletsTypesDao = daoFactory.createBulletsTypesDao();
        bulletDao.beginTransaction();
        bulletsTypesDao.beginTransaction();

        List<Bullet> bullets = new ArrayList<>();
        List<String> types = new ArrayList<>();
        // load's all bullets and all bullet types
        bullets.addAll(bulletDao.findAll());
        types.addAll(bulletsTypesDao.findAll());

        req.setAttribute("bullets", bullets);
        req.setAttribute("types", types);
        bulletDao.endTransaction();
        bulletsTypesDao.endTransaction();
        return result;
    }
}
