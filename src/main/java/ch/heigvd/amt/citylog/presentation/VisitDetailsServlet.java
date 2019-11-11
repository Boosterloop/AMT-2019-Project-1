package ch.heigvd.amt.citylog.presentation;

import ch.heigvd.amt.citylog.integration.VisitsDAO;
import ch.heigvd.amt.citylog.model.User;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class VisitDetailsServlet extends HttpServlet {

    @EJB
    private VisitsDAO visits;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        int cityId = Integer.parseInt(req.getParameter("id"));
        User user = (User)req.getSession().getAttribute("user");
        String userId = user.getUsername();
        req.setAttribute("visits", visits.findByUserAndCityId(userId, cityId));
        req.getRequestDispatcher("/WEB-INF/pages/visitDetails.jsp").forward(req, res);
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
