package ch.heigvd.amt.citylog.presentation;

import ch.heigvd.amt.citylog.integration.CitiesDAO;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * List of most visited cities servlet
 *
 * @author Luc Wachter, Alison Savary
 */
public class TopCitiesServlet extends HttpServlet {

    @EJB
    private CitiesDAO cities;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        int page = 1;
        int rowsPage = 20;

        if(req.getParameter("page") != null) {
            page = Integer.parseInt(req.getParameter("page"));
        }


        req.setAttribute("cities", cities.findAllByPopularity());
        req.getRequestDispatcher("/WEB-INF/pages/cities.jsp").forward(req, res);
    }
}
