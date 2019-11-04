package ch.heigvd.amt.citylog.presentation;

import ch.heigvd.amt.citylog.integration.CountriesDAO;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Luc Wachter, Alison Savary
 */
public class ExampleServlet extends HttpServlet {
    @EJB
    private CountriesDAO countries;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        req.setAttribute("countries", countries.findAll());
        req.getRequestDispatcher("/WEB-INF/pages/countries.jsp").forward(req, res);
    }
}
