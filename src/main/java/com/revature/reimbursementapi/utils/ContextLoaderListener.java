package com.revature.reimbursementapi.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.reimbursementapi.daos.UserDAO;
import com.revature.reimbursementapi.services.UserService;
import com.revature.reimbursementapi.servlets.UserServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class ContextLoaderListener implements ServletContextListener{
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        //ServletContextListener.super.contextInitialized(sce);
        ObjectMapper objectMapper = new ObjectMapper();

        UserServlet userServlet = new UserServlet(objectMapper, new UserService(new UserDAO()));

        ServletContext servletContext = sce.getServletContext();
        servletContext.addServlet("UserServlet", userServlet).addMapping("/users/*");


    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        ServletContextListener.super.contextDestroyed(sce);
    }
}
