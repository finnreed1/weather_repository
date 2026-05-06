package com.project.weatherApp.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class AuthFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request,
                         ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        String path = req.getRequestURI();

        if (path.startsWith("/images/") ||
                path.startsWith("/style.css") ||
                path.equals("/login") ||
                path.equals("/registration") ||
                path.equals("/error")) {
            chain.doFilter(request, response);
            return;
        }

        if (path.equals("/")) {
            res.sendRedirect("/home");
            return;
        }

        chain.doFilter(request, response);
    }
}
