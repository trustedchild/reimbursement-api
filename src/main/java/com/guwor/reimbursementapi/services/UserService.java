package com.guwor.reimbursementapi.services;

import com.guwor.reimbursementapi.daos.UserDAO;
import com.guwor.reimbursementapi.dtos.requests.UpdateUserRequest;
import com.guwor.reimbursementapi.models.User;
import com.guwor.reimbursementapi.utils.custom_exceptions.AuthenticationException;
import com.guwor.reimbursementapi.utils.custom_exceptions.InvalidRequestException;
import com.guwor.reimbursementapi.utils.custom_exceptions.ResourceConflictException;
import com.guwor.reimbursementapi.dtos.requests.LoginRequest;
import com.guwor.reimbursementapi.dtos.requests.NewUserRequest;
import com.guwor.reimbursementapi.dtos.responses.Principal;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

public class UserService {

    private final UserDAO userDAO;

    public UserService(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public User register(NewUserRequest newUserRequest){
        User user = null;

        if (isValidUsername(newUserRequest.getUsername())) {
            if (!isDuplicateUsername(newUserRequest.getUsername())) {
                if (isValidPassword(newUserRequest.getPassword())) {
                    if (isSamePassword(newUserRequest.getPassword(), newUserRequest.getPassword_confirmation())) {
                        user = new User(UUID.randomUUID().toString(), newUserRequest.getUsername(), newUserRequest.getEmail(), newUserRequest.getPassword(), newUserRequest.getGiven_name(), newUserRequest.getSurname(), newUserRequest.getIs_active(), newUserRequest.getRole_id());
                        userDAO.save(user);
                    }
                }
            }
        }
        return user;
    }

    public Principal login(LoginRequest request) {
        User user = userDAO.getUserByUsernameAndPassword(request.getUsername(), request.getPassword());
        if (user == null) throw new AuthenticationException("\nWrong username or password.");
        return new Principal(user.getUser_id(), user.getUsername(), user.getRole_id());
    }

    public User updateByAdmin(UpdateUserRequest updateUserRequest) {
        User user = null;
        if (updateUserRequest == null) throw new AuthenticationException("\nUnknown user.");
        user = new User(updateUserRequest.getSurname(), updateUserRequest.getEmail(), updateUserRequest.getPassword(), updateUserRequest.getGiven_name(), updateUserRequest.getSurname(), updateUserRequest.getIs_active(), updateUserRequest.getRole_id());
        userDAO.update(user);
        return null;
    }

    public User updateByCurrentUser(UpdateUserRequest updateUserRequest) {
        User user = null;
        if (updateUserRequest == null) throw new AuthenticationException("\nUnknown user.");
        user = new User(updateUserRequest.getUsername(), updateUserRequest.getEmail(), updateUserRequest.getPassword(), updateUserRequest.getGiven_name(), updateUserRequest.getSurname());
        userDAO.update(user);
        return user;
    }

    public User activateUser(UpdateUserRequest updateUserRequest) {
        User user = null;

        if (updateUserRequest == null) throw new AuthenticationException("\nUnknown user.");

        user = new User(updateUserRequest.getIs_active());
        userDAO.update(user);

        return user;
    }

    public User assignUserRole(UpdateUserRequest updateUserRequest) {
        User user = null;

        if (updateUserRequest == null) throw new AuthenticationException("\nUnknown user.");

        user = new User(updateUserRequest.getRole_id());
        userDAO.update(user);

        return null;
    }


    public User getUserById(String id) {
        return userDAO.getById(id);
    }

    public User getUserByUsername(String username) {
        if (userDAO.getUserByUsername(username) == null) throw new InvalidRequestException("\nNo user found!");
        return userDAO.getUserByUsername(username);
    }

    public List<User> getAllUsers() {

        return userDAO.getAll();
    }

    public boolean isValidUsername(String username) {
        if (!username.matches("^(?=[a-zA-Z0-9._]{8,20}$)(?!.*[_.]{2})[^_.].*[^_.]$")) throw new InvalidRequestException("\nInvalid username! username is 8-20 characters long. no _ or . at the beginning. no __ or _. or ._ or .. inside");
        return true;
    }

    public boolean isValidPassword(String password) {
        if (!password.matches("^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$")) throw new InvalidRequestException("\nInvalid password! Minimum eight characters, at least one letter and one number");
        return true;
    }

    public boolean isDuplicateUsername(String username) {
        if (userDAO.getUsername(username) != null) throw new ResourceConflictException("\nSorry, " + username + " already been taken :(");
        return false;
    }

    public boolean isSamePassword(String password, String password2) {
        if (!password.equals(password2)) throw new InvalidRequestException("\nPassword mismatch!");
        return true;
    }
}
