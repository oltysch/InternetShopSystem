package com.epam.ot.action.tools;

import com.epam.ot.entity.Gun;

import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

public class EntityLoader {
    public static Gun loadGunFromRequest(HttpServletRequest req) {
        double price;
        Integer magazineCapacity;
        Integer fireRate;
        Integer firingRange;
        Integer effectiveFiringRange;
        try {
            price = Double.parseDouble(req.getParameter("price"));
        } catch (NumberFormatException | NullPointerException e) {
            price = 0;
        }
        try {
            magazineCapacity = Integer.parseInt(req.getParameter("magazineCapacity"));
        } catch (NumberFormatException | NullPointerException e) {
            magazineCapacity = null;
        }
        try {
            fireRate = Integer.parseInt(req.getParameter("fireRate"));
        } catch (NumberFormatException | NullPointerException e) {
            fireRate = null;
        }
        try {
            firingRange = Integer.parseInt(req.getParameter("firingRange"));
        } catch (NumberFormatException | NullPointerException e) {
            firingRange = null;
        }
        try {
            effectiveFiringRange = Integer.parseInt(req.getParameter("effectiveFiringRange"));
        } catch (NumberFormatException | NullPointerException e) {
            effectiveFiringRange = null;
        }
        Gun gun = new Gun(req.getParameter("type"), req.getParameter("model"), price);
        gun.setUuid(UUID.fromString(req.getParameter("selectedProductUuid")));
        gun.setDescription(req.getParameter("description"));
        gun.setOrigin(req.getParameter("origin"));
        gun.setCaliber(req.getParameter("caliber"));
        gun.setMagazineCapacity(magazineCapacity);
        gun.setFireRate(fireRate);
        gun.setFiringRange(firingRange);
        gun.setEffectiveFiringRange(effectiveFiringRange);
        return gun;
    }
}
