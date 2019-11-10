package ch.heigvd.amt.citylog.presentation;

import ch.heigvd.amt.citylog.integration.CitiesDAO;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AddVisitServlet extends HttpServlet {

    @EJB
    private CitiesDAO cities;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        req.setAttribute("cities", cities.findAll());
        req.getRequestDispatcher("/WEB-INF/pages/addVisit.jsp").forward(req, res);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        int cityId = Integer.parseInt(req.getParameter("city"));
        String startDate = req.getParameter("startDate");
        String endDate = req.getParameter("endDate");
        //TODO User id ???


    }
}
