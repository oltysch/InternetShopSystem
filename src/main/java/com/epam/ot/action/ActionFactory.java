package com.epam.ot.action;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

public class ActionFactory {
    public static final Logger logger = LogManager.getLogger(ActionFactory.class);
    static Map<String, Action> actions;

    static {
        actions = new HashMap<>();
        actions.put("GET/", new ShowPageAction("start_page"));
        actions.put("GET/login", new ShowPageAction("login"));
        actions.put("POST/login", new LoginAction());
        actions.put("GET/logout", new LogoutAction());
        actions.put("GET/register", new ShowPageAction("register"));
        actions.put("POST/register", new RegisterAction());
        actions.put("GET/success_register", new ShowPageAction("success_register"));
        actions.put("GET/products", new ShowShopAction());
        actions.put("POST/addToCart", new AddToCartAction());
        actions.put("POST/removeFromCart", new RemoveFromCartAction());
        actions.put("GET/shopcart", new ShowCartAction());

        actions.put("GET/admin/users", new ShowUsersAction("admin/view_users"));
        actions.put("GET/admin/guns", new ShowGunsAction("admin/view_guns"));
        actions.put("GET/admin/bullets", new ShowBulletsAction("admin/view_bullets"));

        actions.put("GET/admin/edit_users", new ShowUsersAction("admin/edit_users"));
        actions.put("GET/admin/edit_guns", new ShowGunsAction("admin/edit_guns"));
        actions.put("GET/admin/edit_bullets", new ShowBulletsAction("admin/edit_bullets"));

        actions.put("GET/admin/createUser", new CreateUserAction());
        actions.put("GET/admin/changeUser", new ChangeUserAction());
        actions.put("GET/admin/makeUser", new MakeUserAction());
        actions.put("GET/admin/makeAdmin", new MakeAdminAction());
        actions.put("GET/admin/deleteUser", new DeleteUserAction());

        actions.put("GET/admin/createGun", new CreateGunAction());
        actions.put("GET/admin/changeGun", new ChangeGunAction());
        actions.put("GET/admin/deleteGun", new DeleteGunAction());

        actions.put("GET/admin/createBullet", new CreateBulletAction());
        actions.put("GET/admin/changeBullet", new ChangeBulletAction());
        actions.put("GET/admin/deleteBullet", new DeleteBulletAction());
    }

    public Action getAction(String actionName) {
        logger.info(actionName);
        return actions.get(actionName);
    }
}
