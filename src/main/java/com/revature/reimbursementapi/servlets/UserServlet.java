package com.revature.reimbursementapi.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.reimbursementapi.dtos.requests.NewUserRequest;
import com.revature.reimbursementapi.services.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UserServlet extends HttpServlet {
    private ObjectMapper objectMapper;
    private UserService userService;

    public UserServlet(ObjectMapper objectMapper, UserService userService) {
        this.objectMapper = objectMapper;
        this.userService = userService;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //super.doPost(req, resp);
        NewUserRequest newUserRequest = objectMapper.readValue(req.getInputStream(), NewUserRequest.class);
        resp.getWriter().write(objectMapper.writeValueAsString(newUserRequest));
    }


}
