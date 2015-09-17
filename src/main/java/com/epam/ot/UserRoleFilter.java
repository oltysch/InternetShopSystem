package com.epam.ot;

import com.epam.ot.entity.Role;
import com.epam.ot.entity.User;
import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebFilter(filterName = "UserRoleFilter")
public class UserRoleFilter implements Filter {
    public static final Logger logger = Logger.getLogger(UserRoleFilter.class);
    protected List<String> userPages = new ArrayList<>(); //pages available for all
    protected List<String> onlyAdminPages = new ArrayList<>(); //pages only for admin

    public void init(FilterConfig config) throws ServletException {
        userPages.add("GET/");
        userPages.add("GET/login");
        userPages.add("POST/login");
        userPages.add("GET/logout");
        userPages.add("GET/register");
        userPages.add("POST/register");
        userPages.add("GET/success_register");
        userPages.add("GET/products");
        userPages.add("POST/add_to_cart");
        userPages.add("POST/remove_from_cart");
        userPages.add("GET/shopcart");
        userPages.add("GET/shopcart/change_product_count");
        userPages.add("GET/paid_shopcart");
        userPages.add("GET/clear_shopcart");
        userPages.add("GET/view_product");

        onlyAdminPages.add("GET/product_editor");

        onlyAdminPages.add("GET/users");
        onlyAdminPages.add("GET/guns");
        onlyAdminPages.add("GET/bullets");

        onlyAdminPages.add("GET/edit_users");
        onlyAdminPages.add("GET/edit_guns");
        onlyAdminPages.add("GET/edit_bullets");

        onlyAdminPages.add("GET/create_user");
        onlyAdminPages.add("GET/change_user");
        onlyAdminPages.add("GET/make_user");
        onlyAdminPages.add("GET/make_admin");
        onlyAdminPages.add("GET/ban_user");
        onlyAdminPages.add("GET/unban_user");
        onlyAdminPages.add("GET/delete_user");

        onlyAdminPages.add("GET/create_gun");
        onlyAdminPages.add("GET/change_gun");
        onlyAdminPages.add("GET/change_gun_db");
        onlyAdminPages.add("GET/delete_gun");

        onlyAdminPages.add("GET/create_bullet");
        onlyAdminPages.add("GET/change_bullet");
        onlyAdminPages.add("GET/change_bullet_db");
        onlyAdminPages.add("GET/delete_bullet");
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        //if request is not Http - throw exception
        if (!(request instanceof HttpServletRequest)) {
            logger.info("UserRoleFilter just supports HTTP requests");
            throw new ServletException("UserRoleFilter just supports HTTP requests");
        }

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        String path = httpRequest.getMethod() + httpRequest.getPathInfo();

        for (String adminUrl : onlyAdminPages)
            //check
            if (path.equals(adminUrl)) {
                User user = (User) httpRequest.getSession().getAttribute("user");

                // if user is not admin - go to main page
                if (user != null && user.getRole() != Role.ADMIN) {
                    httpResponse.sendRedirect(httpRequest.getContextPath() + "/gunshop/");
                    logger.info("User without admin role");
                    return;
                }
                chain.doFilter(request, response);
                return;
            }

        chain.doFilter(request, response);
    }


    public void destroy() {

    }

}
