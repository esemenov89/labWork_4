package code.controllers;

import code.model.pojo.StorageUnit;
import code.model.pojo.User;
import code.services.StorageUnitService;
import code.services.StorageUnitServiceImpl;
import code.services.UserService;
import code.services.UserServiceImpl;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashSet;

/** ololo
 * Created by admin on 22.04.2017.
 */
public class ListForAdminsServlet  extends HttpServlet {

    private static StorageUnitService storageUnitService = new StorageUnitServiceImpl();
    private static UserService userService = new UserServiceImpl();
    HashSet<StorageUnit> storageUnits = null;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        storageUnits=storageUnitService.getAllStorageUnits();
        req.setAttribute("books", storageUnits);
        req.setAttribute("users", userService.getAllUsers());

        getServletContext().getRequestDispatcher("/foradmins.jsp")
                .forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println(req.getParameter("readIsn"));
        System.out.println(req.getParameter("delIsn"));
        if (storageUnits!=null && req.getParameter("readIsn")!=null) {
            StorageUnit storageUnit=storageUnits.stream().filter(x->x.getIsn().equals(req.getParameter("readIsn"))).findFirst().get();
            req.setAttribute("title", storageUnit.getTitle());
            req.setAttribute("text", storageUnit.getText());
            getServletContext().getRequestDispatcher("/readStorageUnit.jsp")
                    .forward(req, resp);
        }
        if (storageUnits!=null && req.getParameter("delIsn")!=null) {
            StorageUnit storageUnit=storageUnits.stream().filter(x->x.getIsn().equals(req.getParameter("delIsn"))).findFirst().get();
            storageUnitService.delStorageUnitByISN(storageUnit.getIsn());
            resp.sendRedirect(req.getContextPath() + "/listEntitiesForAdmins");
        }
        if(req.getParameter("readIsn")==null && req.getParameter("delIsn")==null){
            resp.sendRedirect(req.getContextPath() + "/listEntitiesForAdmins");
        }
    }
}