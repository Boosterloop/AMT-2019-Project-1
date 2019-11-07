package ch.heigvd.amt.citylog.presentation;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Filter class that checks if the user is logged in before
 * allowing it to access private pages
 *
 * @author Luc Wachter, Alison Savary
 */
@WebFilter
public class AuthenticationFilter implements Filter {
    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        // If the user is connected
        if (((HttpServletRequest) req).getSession().getAttribute("user") != null) {
            // Continue the filter chain
            chain.doFilter(req, res);
        } else {
            // Redirect user to login screen
            req.setAttribute("error", "You must be logged in to perform this task");
            ((HttpServletResponse) res).sendRedirect(((HttpServletRequest) req).getContextPath() + "/login");
        }
    }
}
