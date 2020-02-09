package com.gmail.petrikov05.app.controller;

import java.io.IOException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gmail.petrikov05.app.service.RoleService;
import com.gmail.petrikov05.app.service.impl.RoleServiceImpl;
import com.gmail.petrikov05.app.service.model.RoleDTO;

import static com.gmail.petrikov05.app.controller.constant.PagesConstant.PAGES_LOCATION;

public class RoleServlet extends HttpServlet {

    private RoleService roleService = RoleServiceImpl.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<RoleDTO> roleDTOList = roleService.findAll();
        req.setAttribute("roles", roleDTOList);
        ServletContext servletContext = req.getServletContext();
        RequestDispatcher requestDispatcher = servletContext.getRequestDispatcher(PAGES_LOCATION + "/roles.jsp");
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

}
