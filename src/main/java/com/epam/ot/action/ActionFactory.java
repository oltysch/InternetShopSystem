package com.epam.ot.action;

import org.apache.log4j.Logger;

import java.util.HashMap;
import java.util.Map;

public class ActionFactory {
    public static final Logger logger = Logger.getLogger(ActionFactory.class);
    static Map<String, Action> actions; //actions mapping

    static {
        actions = new HashMap<>();
        actions.put("GET/", new ShowLoginPageOrAuthorize());
        actions.put("GET/login", new ShowPageAction("login"));
        actions.put("POST/login", new LoginAction());
        actions.put("GET/logout", new LogoutAction());
        actions.put("GET/register", new ShowPageAction("register"));
        actions.put("POST/register", new RegisterAction());
        actions.put("GET/success_register", new ShowPageAction("success_register"));
        actions.put("GET/products", new ShowProductsAction());
        actions.put("POST/add_to_cart", new AddToCartAction());
        actions.put("POST/remove_from_cart", new RemoveFromCartAction());
        actions.put("GET/shopcart", new ShowCartAction());
        actions.put("GET/shopcart/change_product_count", new ChangeProductCountAction());
        actions.put("GET/paid_shopcart", new PaidCartAction());
        actions.put("GET/clear_shopcart", new ClearCartAction());
        actions.put("GET/view_product", new ShowProductAction("view_product"));

        actions.put("GET/product_editor", new ShowProductAction("select"));

        actions.put("GET/users", new ShowUsersAction("admin/db/view_users"));
        actions.put("GET/guns", new ShowGunsAction("admin/db/view_guns"));
        actions.put("GET/bullets", new ShowBulletsAction("admin/db/view_bullets"));

        actions.put("GET/edit_users", new ShowUsersAction("admin/db/edit_users"));
        actions.put("GET/edit_guns", new ShowGunsAction("admin/db/edit_guns"));
        actions.put("GET/edit_bullets", new ShowBulletsAction("admin/db/edit_bullets"));

        actions.put("GET/create_user", new CreateUserAction());
        actions.put("GET/change_user", new ChangeUserAction());
        actions.put("GET/make_user", new MakeUserAction());
        actions.put("GET/make_admin", new MakeAdminAction());
        actions.put("GET/ban_user", new BanUserAction());
        actions.put("GET/unban_user", new UnbanUserAction());
        actions.put("GET/delete_user", new DeleteUserAction());

        actions.put("GET/create_gun", new CreateGunAction());
        actions.put("GET/change_gun", new ChangeGunAction("products"));
        actions.put("GET/change_gun_db", new ChangeGunAction("edit_guns"));
        actions.put("GET/delete_gun", new DeleteGunAction());

        actions.put("GET/create_bullet", new CreateBulletAction());
        actions.put("GET/change_bullet", new ChangeBulletAction("products"));
        actions.put("GET/change_bullet_db", new ChangeBulletAction("edit_bullets"));
        actions.put("GET/delete_bullet", new DeleteBulletAction());
    }

    /**
     * returns an action by name
     *
     * @param actionName
     * @return
     */
    public Action getAction(String actionName) {
        logger.info(actionName);
        Action result = actions.get(actionName);
        if (result == null) {
            result = actions.get("GET/");
        }
        return result;
    }
}
