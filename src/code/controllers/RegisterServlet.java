package code.controllers;

import code.model.pojo.StorageUnit;
import code.model.pojo.User;
import code.services.UserService;
import code.services.UserServiceImpl;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by admin on 22.04.2017.
 */
public class RegisterServlet extends HttpServlet {

    private static UserService userService = new UserServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/register.jsp")
                .forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        String mail = req.getParameter("email");

        System.out.println(login);
        System.out.println(password);
        System.out.println(mail);

        User user =userService.validateUser(login,password,mail);
        boolean error=false;

        req.setAttribute("registerLogin", "");
        req.setAttribute("registerPassword", "");
        req.setAttribute("registerMail", "");

        if(user.getLogin().equals("@Error1")){
            req.setAttribute("registerLogin", "Логин может состоять из латинских символов и цифр, а так же размер не должен быть меньше 2-х символ и не должен превышать 16 символов");
            error=true;
        }
        if(user.getLogin().equals("@Error2")){
            req.setAttribute("registerLogin", "Пользователь с таким логином уже существует");
            error=true;
        }
        if(user.getPassword().equals("@Error1")){
            req.setAttribute("registerPassword", "Пароль может состоять из латинских символов и цифр, а так же размер не должен быть меньше 8-ми символов и не должен превышать 16 символов");
            error=true;
        }
        if(user.getMail().equals("@Error1")){
            req.setAttribute("registerMail", "Mail может состоять из латинских символов, цифр и знаков: \'@\',\'-\',\'.\', а так же размер не должен быть меньше 8-ми символ и не должен превышать 16 символов");
            error=true;
        }
        if(user.getMail().equals("@Error2")){
            req.setAttribute("registerMail", "Пользователь с таким emal уже существует");
            error=true;
        }
        if(error){
            req.getRequestDispatcher("/register.jsp")
                    .forward(req, resp);
        }
        else {
            userService.addUser(user);
            req.setAttribute("mess","Вы успешно зарегистрировались, для входа введите логин и пароль");
            resp.sendRedirect("/");
        }

        System.out.println(user);
    }
}