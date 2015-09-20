package com.epam.ot.action;

import com.epam.ot.action.tools.EntityLoader;
import com.epam.ot.dao.BulletDao;
import com.epam.ot.dao.DaoFactory;
import com.epam.ot.entity.Bullet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

public class ChangeBulletAction implements Action {
    ActionResult actionResult;

    public ChangeBulletAction(String page) {
        actionResult = new ActionResult(page, true);
    }

    @Override
    public ActionResult execute(HttpServletRequest request, HttpServletResponse response) {
        DaoFactory daoFactory = DaoFactory.getInstance();
        BulletDao bulletDao = daoFactory.createBulletDao();
        bulletDao.beginTransaction();

        Bullet bullet = EntityLoader.loadBulletFromRequest(request);
        bulletDao.update(bullet);

        bulletDao.endTransaction();
        return actionResult;
    }
}
