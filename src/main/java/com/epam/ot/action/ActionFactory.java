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
        actions.put("POST/login", new LoginAction("products", "login"));
        actions.put("GET/logout", new LogoutAction());
        actions.put("GET/register", new ShowPageAction("register"));
        actions.put("POST/register", new RegisterAction());
        actions.put("GET/success_register", new ShowPageAction("success_register"));
        actions.put("GET/products", new ShowShopAction());
        actions.put("POST/addToCart", new AddToCartAction());
        actions.put("POST/removeFromCart", new RemoveFromCartAction());
        actions.put("GET/shopcart", new ShowPageAction("shopcart"));

        actions.put("GET/users", new ShowUsersAction());
        actions.put("GET/guns", new ShowGunsAction());
        actions.put("GET/bullets", new ShowBulletsAction());

        actions.put("GET/createUser", new CreateUserAction());
        actions.put("GET/changeUser", new ChangeUserAction());
        actions.put("GET/makeUser", new MakeUserAction());
        actions.put("GET/makeAdmin", new MakeAdminAction());
        actions.put("GET/deleteUser", new DeleteUserAction());

        actions.put("GET/createGun", new CreateGunAction());
        actions.put("GET/changeGun", new ChangeGunAction());
        actions.put("GET/deleteGun", new DeleteGunAction());

        actions.put("GET/createBullet", new CreateBulletAction());
        actions.put("GET/changeBullet", new ChangeBulletAction());
        actions.put("GET/deleteBullet", new DeleteBulletAction());
    }

    public Action getAction(String actionName) {
        logger.info(actionName);
        return actions.get(actionName);
    }
}
