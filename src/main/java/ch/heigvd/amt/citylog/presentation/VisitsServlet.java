package ch.heigvd.amt.citylog.presentation;

import ch.heigvd.amt.citylog.integration.CountriesDAO;
import ch.heigvd.amt.citylog.integration.VisitsDAO;
import ch.heigvd.amt.citylog.model.User;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * List of visits servlet
 *
 * @author Luc Wachter, Alison Savary
 */
public class VisitsServlet extends HttpServlet {

    @EJB
    private VisitsDAO visits;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        User user = (User)req.getSession().getAttribute("user");
        String userId = user.getUsername();
        req.setAttribute("visits", visits.findByUserId(userId));
        System.out.println(visits.findByUserId(userId).toString());
        req.getRequestDispatcher("/WEB-INF/pages/visits.jsp").forward(req, res);
    }
}
