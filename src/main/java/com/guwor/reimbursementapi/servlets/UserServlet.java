package com.guwor.reimbursementapi.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.guwor.reimbursementapi.utils.custom_exceptions.InvalidRequestException;
import com.guwor.reimbursementapi.utils.custom_exceptions.ResourceConflictException;
import com.guwor.reimbursementapi.dtos.requests.NewUserRequest;
import com.guwor.reimbursementapi.models.User;
import com.guwor.reimbursementapi.services.UserService;

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

        try {
            NewUserRequest newUserRequest = objectMapper.readValue(req.getInputStream(), NewUserRequest.class);
            //resp.getWriter().write(objectMapper.writeValueAsString(newUserRequest));
            //System.out.println("\n"+req.getRequestURI());

            String[] path = req.getRequestURI().split("/");

            if (path[3].equals("signup")) {
                //System.out.println("HELLO");
                User createdUser = userService.register(newUserRequest);

                resp.setStatus(200);
                resp.setContentType("application/json");
                //resp.getWriter().write(objectMapper.writeValueAsString(createdUser.getUser_id()));

            }else {
                System.out.println("No");
            }
        } catch (InvalidRequestException e) {
            resp.setStatus(404);
            //resp.getWriter().write(objectMapper.writeValueAsString(e.getMessage()));
        } catch (ResourceConflictException e) {
            resp.setStatus(409);
        } catch (Exception e) {
            resp.setStatus(404);
            //resp.getWriter().write(objectMapper.writeValueAsString(e.getMessage()));
        }
    }


}
