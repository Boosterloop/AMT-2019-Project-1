package ch.heigvd.amt.citylog.presentation;

import business.AuthenticationService;
import ch.heigvd.amt.citylog.integration.UsersDAO;
import ch.heigvd.amt.citylog.model.User;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Login servlet
 *
 * @author Luc Wachter, Alison Savary
 */
public class LogInServlet extends HttpServlet {
    @EJB
    private UsersDAO users;

    @EJB
    AuthenticationService auth;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/pages/login.jsp").forward(req, res);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        String error = null;

        // Check that username exists
        User user = users.findById(username);

        if (user != null) {
            // Check that password matches user's hash
            if (auth.checkPassword(password, user.getPasswordHash())) {
                // Set session for current user
                req.getSession().setAttribute("user", user);
                res.sendRedirect(req.getContextPath() + "/");
                return;
            } else {
                error = "Username or password incorrect";
            }
        } else {
            error = "This username doesn't exist";
        }

        req.setAttribute("error", error);
        req.getRequestDispatcher("/WEB-INF/pages/login.jsp").forward(req, res);
    }
}
