package ch.heigvd.amt.citylog.presentation;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class VisitDetailsServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        int cityId = Integer.parseInt(req.getParameter("id"));
        int userId;
        //req.setAttribute("visits", visits.findAll(userId, cityId));
        req.getRequestDispatcher("/WEB-INF/pages/visits.jsp").forward(req, res);
    }
}
