package com.dogancanokur.petClinic.web;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

@WebFilter(filterName = "/TestServlet")
public class TestFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
//        response.getWriter().write("before...");
        chain.doFilter(request, response);
//        response.getWriter().write("...after");
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
