package com.epam.ot.action;

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
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) {
        String priceString = req.getParameter("price");
        double price;
        try {
            price = Double.parseDouble(priceString);
        } catch (NumberFormatException | NullPointerException e) {
            price = 0;
        }
        Bullet bullet = new Bullet(req.getParameter("caliber"), req.getParameter("name"), req.getParameter("bulletType"), price, Integer.parseInt(req.getParameter("price")));
        bullet.setUuid(UUID.randomUUID());
        DaoFactory daoFactory = DaoFactory.getInstance();
        BulletDao bulletDao = daoFactory.createBulletDao();
        bulletDao.beginTransaction();
        bulletDao.insert(bullet);
        bulletDao.endTransaction();
        return actionResult;
    }
}
