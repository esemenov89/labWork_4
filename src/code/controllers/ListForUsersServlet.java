package code.controllers;

import code.model.pojo.StorageUnit;
import code.services.StorageUnitService;
import code.services.StorageUnitServiceImpl;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * Created by admin on 22.04.2017.
 */
public class ListForUsersServlet  extends HttpServlet {

    private static StorageUnitService storageUnitService = new StorageUnitServiceImpl();
    HashSet<StorageUnit> storageUnits = null;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        storageUnits=storageUnitService.getAllStorageUnits();
        req.setAttribute("books", storageUnits);
        getServletContext().getRequestDispatcher("/forusers.jsp")
                .forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (storageUnits!=null) {
            StorageUnit storageUnit=storageUnits.stream().filter(x->x.getIsn().equals(req.getParameter("isn"))).findFirst().get();
            req.setAttribute("title", storageUnit.getTitle());
            req.setAttribute("text", storageUnit.getText());
            getServletContext().getRequestDispatcher("/readStorageUnit.jsp")
                    .forward(req, resp);
        }
    }
}
