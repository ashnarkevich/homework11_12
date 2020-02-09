package com.gmail.petrikov05.app.controller.filter;

import java.io.IOException;
import java.lang.invoke.MethodHandles;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.gmail.petrikov05.app.service.RoleService;
import com.gmail.petrikov05.app.service.UserService;
import com.gmail.petrikov05.app.service.impl.RoleServiceImpl;
import com.gmail.petrikov05.app.service.impl.UserServiceImpl;
import com.gmail.petrikov05.app.service.model.UserLoginDTO;
import com.gmail.petrikov05.app.service.model.UserRoleDTO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LoginFilter implements Filter {

    private static final Logger logger = LogManager.getLogger(MethodHandles.lookup().lookupClass());
    private UserService userService = UserServiceImpl.getInstance();
    private RoleService roleService = RoleServiceImpl.getInstance();

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        UserLoginDTO userLoginDTO = getUserLoginDTO(req);

        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;

        boolean isVerification = userService.verification(userLoginDTO);

        if (isVerification) {
            String userName = req.getParameter("username");
            UserRoleDTO userRoleDTO = roleService.getUserRole(userName);
            HttpSession session = request.getSession();
            session.setAttribute("userRole", userRoleDTO);

            chain.doFilter(req, resp);
        } else {
            response.sendRedirect(request.getContextPath() + "/login");

        }
    }

    private UserLoginDTO getUserLoginDTO(ServletRequest req) {
        UserLoginDTO userLoginDTO = new UserLoginDTO();
        userLoginDTO.setUserName(req.getParameter("username"));
        userLoginDTO.setPassword(req.getParameter("password"));
        return userLoginDTO;
    }

}
