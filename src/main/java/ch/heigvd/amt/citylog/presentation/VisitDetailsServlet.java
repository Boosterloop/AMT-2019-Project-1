package ch.heigvd.amt.citylog.presentation;

import ch.heigvd.amt.citylog.integration.VisitsDAO;
import ch.heigvd.amt.citylog.model.User;
import ch.heigvd.amt.citylog.model.Visit;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class VisitDetailsServlet extends HttpServlet {
    @EJB
    private VisitsDAO visits;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        int cityId = Integer.parseInt(req.getParameter("id"));
        User user = (User)req.getSession().getAttribute("user");
        String userId = user.getUsername();
        List<Visit> visitsList = visits.findByUserAndCityId(userId, cityId);
        if(visitsList.size() > 0) {
            req.setAttribute("visits", visitsList);
            req.getRequestDispatcher("/WEB-INF/pages/visitDetails.jsp").forward(req, res);
        } else {
            res.sendRedirect(req.getContextPath() + "/visits");
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        int visitId = Integer.parseInt(req.getParameter("id"));
        try {
            visits.deleteById(visitId);
            res.sendRedirect(req.getContextPath() + "/visits");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
