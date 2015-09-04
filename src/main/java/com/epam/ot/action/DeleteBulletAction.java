package com.epam.ot.action;

import com.epam.ot.dao.BulletDao;
import com.epam.ot.dao.DaoFactory;
import com.epam.ot.entity.Bullet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

public class DeleteBulletAction implements Action {
    ActionResult actionResult;

    public DeleteBulletAction() {
        actionResult = new ActionResult("edit_bullets", true);
    }

    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) {
        double price;
        int qty;
        try {
            price = Double.parseDouble(req.getParameter("price"));
        } catch (NumberFormatException | NullPointerException e) {
            price = 0;
        }
        try {
            qty = Integer.parseInt(req.getParameter("qty"));
        } catch (NumberFormatException | NullPointerException e) {
            qty = 0;
        }
        Bullet bullet = new Bullet(req.getParameter("caliber"), req.getParameter("name"), req.getParameter("bulletType"), price, qty);
        bullet.setUuid(UUID.fromString(req.getParameter("uuid")));
        bullet.setDescription(req.getParameter("description"));
        DaoFactory daoFactory = DaoFactory.getInstance();
        BulletDao bulletDao = daoFactory.createBulletDao();
        bulletDao.beginTransaction();
        bulletDao.remove(bullet);
        bulletDao.endTransaction();
        return actionResult;
    }
}
