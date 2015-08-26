package com.epam.ot.action;

import com.epam.ot.dao.DaoFactory;
import com.epam.ot.dao.UserDao;
import com.epam.ot.entity.ShoppingCart;
import com.epam.ot.entity.User;

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
        ShoppingCart cart = new ShoppingCart();

        //TODO use a cookies
//        Cookie cookie = new Cookie("xid", "dkufyiaugyu87ht8h479strgh7846");
        if (user != null && (password.equals(user.getPassword()))) {
            req.getSession().setAttribute("user", user);
            req.getSession().setAttribute("cart", cart);
            result = new ActionResult("products", true);
        } else {
            req.setAttribute("login", login);
            req.setAttribute("loginError", "Wrong login or password!");
            result = new ActionResult("login");
        }
        return result;
    }
}
