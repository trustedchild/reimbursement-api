package com.guwor.reimbursementapi.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.guwor.reimbursementapi.services.UserService;
import com.guwor.reimbursementapi.utils.custom_exceptions.AuthenticationException;
import com.guwor.reimbursementapi.utils.custom_exceptions.InvalidRequestException;
import com.guwor.reimbursementapi.dtos.requests.LoginRequest;
import com.guwor.reimbursementapi.dtos.responses.Principal;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AuthServlet extends HttpServlet {

    private ObjectMapper objectMapper;
    private UserService userService;

    public AuthServlet(ObjectMapper objectMapper, UserService userService) {
        this.objectMapper = objectMapper;
        this.userService = userService;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //super.doPost(req, resp);
        try {

            //String[] path = req.getRequestURI().split("/");

            LoginRequest loginRequest = objectMapper.readValue(req.getInputStream(), LoginRequest.class);
            Principal principal = userService.login(loginRequest);

            resp.setStatus(200);
            resp.setContentType("application/json");
            resp.getWriter().write(objectMapper.writeValueAsString(principal));
//
//            if (path[3].equals("login")){
//                resp.getWriter().write(objectMapper.writeValueAsString(loginRequest));
//            }

        }catch (InvalidRequestException e){
            resp.setStatus(404);
        }catch(AuthenticationException e){
            resp.setStatus(401);
        }
    }
}
