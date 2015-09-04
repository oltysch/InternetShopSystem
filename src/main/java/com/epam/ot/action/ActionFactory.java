package com.epam.ot.action;

import com.epam.ot.util.PropertyManager;
import org.apache.log4j.Logger;

import java.util.HashMap;
import java.util.Map;

public class ActionFactory {
    public static final Logger logger = Logger.getLogger(PropertyManager.class);
    static Map<String, Action> actions;

    static {
        actions = new HashMap<>();
        actions.put("GET/gunshop/", new ShowLoginPageOrAuthorize());
        actions.put("GET/gunshop/login", new ShowPageAction("login"));
        actions.put("POST/gunshop/login", new LoginAction());
        actions.put("GET/gunshop/logout", new LogoutAction());
        actions.put("GET/gunshop/register", new ShowPageAction("register"));
        actions.put("POST/gunshop/register", new RegisterAction());
        actions.put("GET/gunshop/success_register", new ShowPageAction("success_register"));
        actions.put("GET/gunshop/products", new ShowProductsAction());
        actions.put("POST/gunshop/add_to_cart", new AddToCartAction());
        actions.put("POST/gunshop/remove_from_cart", new RemoveFromCartAction());
        actions.put("GET/gunshop/shopcart", new ShowCartAction());
        actions.put("GET/gunshop/shopcart/change_product_count", new ChangeProductCountAction());
        actions.put("GET/gunshop/paid_shopcart", new PaidCartAction());
        actions.put("GET/gunshop/clear_shopcart", new ClearCartAction());
        actions.put("GET/gunshop/view_product", new ShowProductAction("view_product"));

        actions.put("GET/admin/product_editor", new ShowProductAction("select"));

        actions.put("GET/admin/users", new ShowUsersAction("admin/db/view_users"));
        actions.put("GET/admin/guns", new ShowGunsAction("admin/db/view_guns"));
        actions.put("GET/admin/bullets", new ShowBulletsAction("admin/db/view_bullets"));

        actions.put("GET/admin/edit_users", new ShowUsersAction("admin/db/edit_users"));
        actions.put("GET/admin/edit_guns", new ShowGunsAction("admin/db/edit_guns"));
        actions.put("GET/admin/edit_bullets", new ShowBulletsAction("admin/db/edit_bullets"));

        actions.put("GET/admin/create_user", new CreateUserAction());
        actions.put("GET/admin/change_user", new ChangeUserAction());
        actions.put("GET/admin/make_user", new MakeUserAction());
        actions.put("GET/admin/make_admin", new MakeAdminAction());
        actions.put("GET/admin/ban_user", new BanUserAction());
        actions.put("GET/admin/unban_user", new UnbanUserAction());
        actions.put("GET/admin/delete_user", new DeleteUserAction());

        actions.put("GET/admin/create_gun", new CreateGunAction());
        actions.put("GET/admin/change_gun", new ChangeGunAction("product_editor"));
        actions.put("GET/admin/change_gun_db", new ChangeGunAction("edit_guns"));
        actions.put("GET/admin/delete_gun", new DeleteGunAction());

        actions.put("GET/admin/create_bullet", new CreateBulletAction());
        actions.put("GET/admin/change_bullet", new ChangeBulletAction("product_editor"));
        actions.put("GET/admin/change_bullet_db", new ChangeBulletAction("edit_bullets"));
        actions.put("GET/admin/delete_bullet", new DeleteBulletAction());
    }

    public Action getAction(String actionName) {
        logger.info(actionName);
        return actions.get(actionName);
    }
}
