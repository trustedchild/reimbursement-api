package com.guwor.reimbursementapi.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.guwor.reimbursementapi.dtos.requests.UpdateUserRequest;
import com.guwor.reimbursementapi.dtos.responses.Principal;
import com.guwor.reimbursementapi.models.User;
import com.guwor.reimbursementapi.services.TokenService;
import com.guwor.reimbursementapi.services.UserService;
import com.guwor.reimbursementapi.utils.custom_exceptions.InvalidRequestException;
import com.guwor.reimbursementapi.utils.custom_exceptions.ResourceConflictException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class UpdateUserServlet extends HttpServlet {
    private ObjectMapper objectMapper;
    private UserService userService;
    private TokenService tokenService;

    public UpdateUserServlet(ObjectMapper objectMapper, UserService userService, TokenService tokenService) {
        this.objectMapper = objectMapper;
        this.userService = userService;
        this.tokenService = tokenService;
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String token = req.getHeader("Authorization");
        Principal principal = tokenService.extractRequestDetails(token);

        try {
            UpdateUserRequest updatedUserRequest = objectMapper.readValue(req.getInputStream(), UpdateUserRequest.class);

            resp.getWriter().write(objectMapper.writeValueAsString(updatedUserRequest)); //debugger

            String[] path = req.getRequestURI().split("/");

            if (path[3].equals("update")) {
                if (principal.getRole_id().equals("ADMIN")){
                    User updatedUser = userService.updateByAdmin(updatedUserRequest);
                    resp.setStatus(200);
                    resp.setContentType("application/json");
                    resp.getWriter().write(objectMapper.writeValueAsString(updatedUser.getUser_id())); //debugger
                }else if (principal.getRole_id().equals("FINANCEMANAGER")){
                    User updatedUser = userService.activateUser(updatedUserRequest);
                    resp.setStatus(200);
                    resp.setContentType("application/json");
                    resp.getWriter().write(objectMapper.writeValueAsString(updatedUser.getUser_id())); //debugger
                } else if (principal.getRole_id().equals("EMPLOYEE")) {
                    User updatedUser = userService.updateByCurrentUser(updatedUserRequest);
                    resp.setStatus(200);
                    resp.setContentType("application/json");
                    resp.getWriter().write(objectMapper.writeValueAsString(updatedUser.getUser_id())); //debugger
                }else {
                    System.out.println("User not found.");
                }

            }else {
                System.out.println(path[3] + " URL not found.");
            }
        } catch (NullPointerException e) {
            resp.setStatus(401);
            //resp.getWriter().write(objectMapper.writeValueAsString(e.getMessage()));
        } catch (Exception e) {
            resp.setStatus(404);
            //resp.getWriter().write(objectMapper.writeValueAsString(e.getMessage()));
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String token = req.getHeader("Authorization");
        Principal principal = tokenService.extractRequestDetails(token);

        try {
            if (principal.getRole_id().equals("ADMIN")){
                String username = req.getParameter("username");

                resp.setContentType("application/json");
                if (username != null){
                    resp.getWriter().write(objectMapper.writeValueAsString(userService.getUserByUsername(username)));
                } else {
                    List<User> userList = userService.getAllUsers();
                    resp.getWriter().write(objectMapper.writeValueAsString(userList));
                }
            }else {
                resp.setStatus((403));
            }
        }catch (NullPointerException e){
            resp.setStatus(401);
        }catch (InvalidRequestException e){
            resp.setStatus(404);
        }
    }

}
