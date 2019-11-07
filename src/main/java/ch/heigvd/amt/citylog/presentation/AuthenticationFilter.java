package ch.heigvd.amt.citylog.presentation;

import javax.servlet.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Filter class that checks if the user is logged in before
 * allowing it to access private pages
 *
 * @author Luc Wachter, Alison Savary
 */
public class AuthenticationFilter implements Filter {
    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpReq = (HttpServletRequest) req;
        HttpServletResponse httpRes = (HttpServletResponse) res;

        // If the user is connected
        if (httpReq.getSession().getAttribute("user") != null) {
            // Continue the filter chain
            chain.doFilter(req, res);
        } else {
            // Redirect user to login screen
            httpReq.setAttribute("error", "You must be logged in to perform this task");
            httpRes.sendRedirect(httpReq.getContextPath() + "/login");
        }
    }
}
