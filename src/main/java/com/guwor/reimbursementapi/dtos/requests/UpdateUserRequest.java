package com.guwor.reimbursementapi.dtos.requests;

public class UpdateUserRequest {

    private String username;

    private String email;

    private String password;

    private String given_name;

    private String surname;

    private String is_active;

    private String role_id;

    public UpdateUserRequest() {
    }

    public UpdateUserRequest(String is_active) {
        this.is_active = is_active;
    }

    public UpdateUserRequest(String username, String email, String password, String given_name, String surname) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.given_name = given_name;
        this.surname = surname;
    }

    public UpdateUserRequest(String username, String email, String password, String given_name, String surname, String is_active, String role_id) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.given_name = given_name;
        this.surname = surname;
        this.is_active = is_active;
        this.role_id = role_id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getGiven_name() {
        return given_name;
    }

    public void setGiven_name(String given_name) {
        this.given_name = given_name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getIs_active() {
        return is_active;
    }

    public void setIs_active(String is_active) {
        this.is_active = is_active;
    }

    public String getRole_id() {
        return role_id;
    }

    public void setRole_id(String role_id) {
        this.role_id = role_id;
    }

    @Override
    public String toString() {
        return "UpdateUserRequest{" +
                "username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", given_name='" + given_name + '\'' +
                ", surname='" + surname + '\'' +
                ", is_active='" + is_active + '\'' +
                ", role_id='" + role_id + '\'' +
                '}';
    }

}
