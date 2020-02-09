package com.gmail.petrikov05.app.controller.listener;

import java.util.List;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionListener;

import com.gmail.petrikov05.app.service.TableService;
import com.gmail.petrikov05.app.service.UserService;
import com.gmail.petrikov05.app.service.impl.TableServiceImpl;
import com.gmail.petrikov05.app.service.impl.UserServiceImpl;
import com.gmail.petrikov05.app.service.model.UserDTO;

public class TableListener implements ServletContextListener,
        HttpSessionListener, HttpSessionAttributeListener {

    private TableService tableService = TableServiceImpl.getInstance();
    private UserService userService = UserServiceImpl.getInstance();

    // Public constructor is required by servlet spec
    public TableListener() {
    }

    // -------------------------------------------------------
    // ServletContextListener implementation
    // -------------------------------------------------------
    public void contextInitialized(ServletContextEvent sce) {
      /* This method is called when the servlet context is
         initialized(when the Web application is deployed).
         You can initialize servlet context related data here.
      */

        tableService.createTable();
        List<UserDTO> userDTOList = userService.findAll();
        if (userDTOList.isEmpty()) {
            tableService.addRole();
            tableService.addUser();
        }
    }

    public void contextDestroyed(ServletContextEvent sce) {
      /* This method is invoked when the Servlet Context
         (the Web application) is undeployed or
         Application Server shuts down.
      */
        tableService.dropTable();
        tableService.createTable();
        tableService.addRole();
        tableService.addUser();
    }

}
