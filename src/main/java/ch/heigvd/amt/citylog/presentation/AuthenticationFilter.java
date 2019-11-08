package ch.heigvd.amt.citylog.presentation;

import javax.servlet.*;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Filter class that checks if the user is logged in before
 * allowing it to access private pages
 *
 * @author Luc Wachter, Alison Savary
 */
public class AuthenticationFilter extends HttpFilter {
    @Override
    public void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {
        // If the user is connected
        if (req.getSession().getAttribute("user") != null) {
            // Continue the filter chain
            chain.doFilter(req, res);
        } else {
            // Redirect user to login screen
            req.setAttribute("error", "You must be logged in to perform this task");
            res.sendRedirect(req.getContextPath() + "/login");
        }
    }
}
