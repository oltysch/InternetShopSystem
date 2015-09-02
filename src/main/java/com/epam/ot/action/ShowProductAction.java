package com.epam.ot.action;

import com.epam.ot.dao.*;
import com.epam.ot.entity.Product;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ShowProductAction implements Action {
    ActionResult result;
    DaoFactory daoFactory;
    String page;

    public ShowProductAction(String page) {
        this.page = page;
        daoFactory = DaoFactory.getInstance();
    }

    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) {
        UUID selectedProductUuid = UUID.fromString(req.getParameter("selectedProductUuid"));
        String selectedProductType = req.getParameter("selectedProductType");
        Product product = null;
        if (selectedProductType.equals("gun")) {
            GunDao gunDao = daoFactory.createGunDao();
            gunDao.beginTransaction();
            product = gunDao.findByUuid(selectedProductUuid);
            gunDao.endTransaction();
        } else if (selectedProductType.equals("bullet")) {
            BulletDao bulletDao = daoFactory.createBulletDao();
            bulletDao.beginTransaction();
            product = bulletDao.findByUuid(selectedProductUuid);
            bulletDao.endTransaction();
        }
        if (page.equals("select") && product != null) {
            selectPage(selectedProductType, req);
            req.setAttribute("product", product);
        } else if (product != null) {
            req.setAttribute("product", product.toBlock());
            result = new ActionResult(page);
        }
        return result;
    }

    private void selectPage(String selectedProductType, HttpServletRequest req) {
        List<String> types = new ArrayList<>();
        if (selectedProductType.equals("gun")) {
            result = new ActionResult("admin/edit_gun");
            GunsTypesDao gunsTypesDao = daoFactory.createGunsTypesDao();
            gunsTypesDao.beginTransaction();
            types.addAll(gunsTypesDao.findAll());
            gunsTypesDao.endTransaction();
        } else if (selectedProductType.equals("bullet")) {
            result = new ActionResult("admin/edit_bullet");
            BulletsTypesDao bulletsTypesDao = daoFactory.createBulletsTypesDao();
            bulletsTypesDao.beginTransaction();
            types.addAll(bulletsTypesDao.findAll());
            bulletsTypesDao.endTransaction();
        }
        req.setAttribute("types", types);
    }
}
