package com.example.spring_web_app_using_xml.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class TopSecretFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        // Check if the specific header is present
        String headerValue = httpRequest.getHeader("X-Top-Secret-Header");
        if (headerValue == null) {
            httpResponse.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            httpResponse.getWriter().write("Missing required header: X-Top-Secret-Header");
            return;
        }
        if (!headerValue.equals("Chrissy")) {
            httpResponse.setStatus(HttpServletResponse.SC_FORBIDDEN);
            httpResponse.getWriter().write("Incorrect value for header: X-Top-Secret-Header");
            return;
        }

        // Continue the filter chain if the header is present
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
    }
}
