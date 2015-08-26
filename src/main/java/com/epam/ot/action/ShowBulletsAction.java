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
        List<Bullet> bullets = new ArrayList<>();
        List<String> types = new ArrayList<>();
        DaoFactory daoFactory = DaoFactory.getInstance();
        BulletDao bulletDao = daoFactory.createBulletDao();
        BulletsTypesDao bulletsTypesDao = daoFactory.createBulletsTypesDao();
        bulletDao.beginTransaction();
        bullets.addAll(bulletDao.findAll());
        bulletDao.endTransaction();
        bulletsTypesDao.beginTransaction();
        types.addAll(bulletsTypesDao.findAll());
        bulletsTypesDao.endTransaction();
        req.setAttribute("bullets", bullets);
        req.setAttribute("types", types);
        return result;
    }
}
