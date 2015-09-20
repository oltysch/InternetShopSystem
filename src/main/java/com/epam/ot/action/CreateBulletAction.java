package com.epam.ot.action;

import com.epam.ot.action.tools.EntityLoader;
import com.epam.ot.dao.BulletDao;
import com.epam.ot.dao.DaoFactory;
import com.epam.ot.entity.Bullet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

public class CreateBulletAction implements Action {
    ActionResult actionResult;

    public CreateBulletAction() {
        actionResult = new ActionResult("edit_bullets", true);
    }

    @Override
    public ActionResult execute(HttpServletRequest request, HttpServletResponse response) {
        DaoFactory daoFactory = DaoFactory.getInstance();
        BulletDao bulletDao = daoFactory.createBulletDao();
        bulletDao.beginTransaction();

        Bullet bullet = EntityLoader.loadBulletFromRequest(request);
        bullet.setUuid(UUID.randomUUID());
        bulletDao.insert(bullet);

        bulletDao.endTransaction();
        return actionResult;
    }
}
