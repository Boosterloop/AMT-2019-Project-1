package ch.heigvd.amt.citylog.presentation;

import ch.heigvd.amt.citylog.integration.UsersDAO;
import ch.heigvd.amt.citylog.model.User;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Registration servlet
 *
 * @author Luc Wachter, Alison Savary
 */
public class RegisterServlet extends HttpServlet {
    @EJB
    private UsersDAO users;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/pages/register.jsp").forward(req, res);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        users.create(new User((String)req.getAttribute("username"), (String)req.getAttribute("password")));
        req.getRequestDispatcher("/WEB-INF/pages/login.jsp").forward(req, resp);
    }
}
