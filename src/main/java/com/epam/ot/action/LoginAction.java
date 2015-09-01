package com.epam.ot.action;

import com.epam.ot.dao.DaoFactory;
import com.epam.ot.dao.UserDao;
import com.epam.ot.entity.ShoppingCart;
import com.epam.ot.entity.User;
import com.epam.ot.security.PasswordHashing;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginAction implements Action {
    ActionResult result;

    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) {
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        DaoFactory daoFactory = DaoFactory.getInstance();
        UserDao userDao = daoFactory.createUserDao();
        User user = userDao.findByLogin(login);

        if (user == null || !PasswordHashing.validatePassword(password, user.getPassword())) {
            req.setAttribute("login", login);
            //TODO use internatioinal outputs
            req.setAttribute("loginError", "Wrong login or password!");
            result = new ActionResult("login");
        } else if (!user.isBanned()) {
            ShoppingCart cart = new ShoppingCart();
            req.getSession().setAttribute("user", user);
            req.getSession().setAttribute("cart", cart);
            //TODO make this random
//            Cookie cookie = new Cookie("xid", "udfyuiosdyty6tafuifteyfsef");
//            cookie.setMaxAge(60 * 60 * 24 * 7);
//            resp.addCookie(cookie);
            result = new ActionResult("products", true);
        } else {
            req.setAttribute("login", login);
            //TODO use internatioinal outputs
            req.setAttribute("loginError", "Sorry, you are banned");
            result = new ActionResult("login");
        }
        return result;
    }
}
