package ch.heigvd.amt.citylog.presentation;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Logout servlet
 *
 * @author Luc Wachter, Alison Savary
 */
public class LogoutServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException {
        // Log out the user
        req.getSession().invalidate();
        res.sendRedirect(req.getContextPath() + "/");
    }
}
