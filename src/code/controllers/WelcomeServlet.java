package code.controllers;

import code.model.pojo.User;
import code.services.UserService;
import code.services.UserServiceImpl;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 */
public class WelcomeServlet extends HttpServlet {

    static {
        PropertyConfigurator.configure(WelcomeServlet.class.getClassLoader()
                .getResource("log4j.properties"));
    }

    private static final Logger LOGGER = Logger.getLogger(WelcomeServlet.class);

    private static UserService userService = new UserServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/welcome.jsp")
                .forward(req, resp);
        System.out.println(req.getSession().getAttribute("userLogin"));
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        User user = userService.auth(login, password);
        if (user != null) {
            if (user.getAccountType() == 1) {
                LOGGER.debug("ADMIN: " + login + " logged ");
                resp.sendRedirect(req.getContextPath() + "/listEntitiesForAdmins");
            } else {
                LOGGER.debug("USER: " + login + " logged");
                resp.sendRedirect(req.getContextPath() + "/listEntitiesForUsers");
            }
            req.getSession().setAttribute("userLogin", login);
            req.getSession().setAttribute("accauntType", user.getAccountType());
        } else {
            req.setAttribute("errorLogin","error");
            req.getRequestDispatcher("/welcome.jsp")
                    .forward(req, resp);
        }
    }
}
