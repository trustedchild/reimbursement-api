package com.guwor.reimbursementapi.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.guwor.reimbursementapi.daos.UserDAO;
import com.guwor.reimbursementapi.services.UserService;
import com.guwor.reimbursementapi.servlets.AuthServlet;
import com.guwor.reimbursementapi.servlets.UserServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class ContextLoaderListener implements ServletContextListener{
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        //ServletContextListener.super.contextInitialized(sce);
        ObjectMapper objectMapper = new ObjectMapper();

        UserServlet userServlet = new UserServlet(objectMapper, new UserService(new UserDAO()));
        AuthServlet authServlet = new AuthServlet(objectMapper, new UserService(new UserDAO()));

        ServletContext servletContext = sce.getServletContext();
        servletContext.addServlet("UserServlet", userServlet).addMapping("/users/*");
        servletContext.addServlet("AuthServlet", authServlet).addMapping("/auth");


    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        ServletContextListener.super.contextDestroyed(sce);
    }
}
