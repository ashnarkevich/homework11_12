package com.gmail.petrikov05.app.controller.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.gmail.petrikov05.app.service.RoleService;
import com.gmail.petrikov05.app.service.impl.RoleServiceImpl;
import com.gmail.petrikov05.app.service.model.UserRoleDTO;

public class RoleFilter implements Filter {

    private RoleService roleService = RoleServiceImpl.getInstance();

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {

        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
        HttpSession session = request.getSession();
        ServletContext servletContext = request.getServletContext();

        if (session.getAttribute("userRole") != null) {
            UserRoleDTO userRoleDTO = (UserRoleDTO) session.getAttribute("userRole");
            String link;
            switch (userRoleDTO.getRole()) {
                case "ADMIN": {
                    link = "/roles";
                    break;
                }
                case "USER": {
                    link = "/users";
                    break;
                }
                default: {
                    throw new UnsupportedOperationException();
                }
            }
            RequestDispatcher requestDispatcher = servletContext.getRequestDispatcher(link);
            requestDispatcher.forward(request, resp);
        } else {
            response.sendRedirect(request.getContextPath() + "/login");
        }

    }

}
