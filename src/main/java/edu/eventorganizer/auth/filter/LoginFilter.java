package edu.eventorganizer.auth.filter;

import com.google.inject.Singleton;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Singleton
public class LoginFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        HttpSession session = req.getSession(false);
        String loginUri = req.getContextPath() + "/";

        boolean isLoggedIn = session != null && session.getAttribute("username") != null;
        boolean loginRequest = req.getRequestURI().equals(loginUri);

        String username = request.getParameter("username");

        if(isLoggedIn) {
            chain.doFilter(req, res);
        } else {
            System.out.println("something wrong");
            res.sendRedirect(loginUri);
        }
    }

    @Override
    public void destroy() {
    }
}
