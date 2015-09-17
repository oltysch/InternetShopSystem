package com.epam.ot.action.tools;

import com.epam.ot.entity.Bullet;
import com.epam.ot.entity.Gun;
import com.epam.ot.entity.Role;
import com.epam.ot.entity.User;
import com.epam.ot.security.PasswordHashing;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

public class EntityLoader {
    public static final Logger logger = Logger.getLogger(EntityLoader.class);

    /**
     * @param request
     * @return loaded gun from request
     */
    public static Gun loadGunFromRequest(HttpServletRequest request) {
        UUID uuid;
        double price;
        Integer magazineCapacity;
        Integer fireRate;
        Integer firingRange;
        Integer effectiveFiringRange;
        try {
            uuid = UUID.fromString(request.getParameter("selectedProductUuid"));
        } catch (NumberFormatException | NullPointerException e) {
            uuid = null;
        }
        try {
            price = Double.parseDouble(request.getParameter("price"));
        } catch (NumberFormatException | NullPointerException e) {
            price = 0;
        }
        try {
            magazineCapacity = Integer.parseInt(request.getParameter("magazineCapacity"));
        } catch (NumberFormatException | NullPointerException e) {
            magazineCapacity = null;
        }
        try {
            fireRate = Integer.parseInt(request.getParameter("fireRate"));
        } catch (NumberFormatException | NullPointerException e) {
            fireRate = null;
        }
        try {
            firingRange = Integer.parseInt(request.getParameter("firingRange"));
        } catch (NumberFormatException | NullPointerException e) {
            firingRange = null;
        }
        try {
            effectiveFiringRange = Integer.parseInt(request.getParameter("effectiveFiringRange"));
        } catch (NumberFormatException | NullPointerException e) {
            effectiveFiringRange = null;
        }
        Gun gun = new Gun(request.getParameter("type"), request.getParameter("model"), price);
        gun.setUuid(uuid);
        gun.setDescription(request.getParameter("description"));
        gun.setOrigin(request.getParameter("origin"));
        gun.setCaliber(request.getParameter("caliber"));
        gun.setMagazineCapacity(magazineCapacity);
        gun.setFireRate(fireRate);
        gun.setFiringRange(firingRange);
        gun.setEffectiveFiringRange(effectiveFiringRange);
        return gun;
    }

    /**
     * @param request
     * @return loaded bullet from request
     */
    public static Bullet loadBulletFromRequest(HttpServletRequest request) {
        UUID uuid;
        double price;
        Integer qty;
        try {
            uuid = UUID.fromString(request.getParameter("selectedProductUuid"));
        } catch (NumberFormatException | NullPointerException e) {
            uuid = null;
        }
        try {
            price = Double.parseDouble(request.getParameter("price"));
        } catch (NumberFormatException | NullPointerException e) {
            price = 0;
        }
        try {
            qty = Integer.parseInt(request.getParameter("qty"));
        } catch (NumberFormatException | NullPointerException e) {
            qty = null;
        }
        Bullet bullet = new Bullet(request.getParameter("caliber"), request.getParameter("name"), request.getParameter("type"), price);
        bullet.setUuid(uuid);
        bullet.setDescription(request.getParameter("description"));
        bullet.setQty(qty);
        return bullet;
    }

    /**
     * @param request
     * @return loaded user from request
     */
    public static User loadUserFromRequest(HttpServletRequest request) {
        UUID uuid;
        User user = new User(request.getParameter("login"), request.getParameter("email"), Role.valueOf(request.getParameter("role")), PasswordHashing.generatePasswordHash(request.getParameter("password")));
        String cash = request.getParameter("cash");
        try {
            uuid = UUID.fromString(request.getParameter("uuid"));
        } catch (NumberFormatException | NullPointerException e) {
            uuid = null;
        }
        try {
            user.setCash(Double.parseDouble(cash));
        } catch (NumberFormatException | NullPointerException e) {
            user.setCash(0);
        }
        user.setUuid(uuid);
        user.setBanned(Boolean.parseBoolean(request.getParameter("banned")));
        return user;
    }
}
