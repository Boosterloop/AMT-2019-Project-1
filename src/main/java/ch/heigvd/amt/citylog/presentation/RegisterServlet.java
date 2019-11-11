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
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String passwordCheck = req.getParameter("passwordCheck");

        String error = null;

        // Check that every field was filled
        if (!username.equals("") && !password.equals("") && !passwordCheck.equals("")) {
            // Check that both passwords are identical
            if (password.equals(passwordCheck)) {
                // Check that this username doesn't already exist
                User check = users.findById(username);
                if (check == null) {
                    // Insert user
                    users.create(new User(username, password));
                    req.getRequestDispatcher("/WEB-INF/pages/login.jsp").forward(req, resp);
                    return;
                } else {
                    error = "Username already used";
                }
            } else {
                error = "Passwords not identical";
            }
        } else {
            error = "Please fill in every field";
        }

        req.setAttribute("error", error);
        req.getRequestDispatcher("/WEB-INF/pages/register.jsp").forward(req, resp);
    }
}
