package com.revature.reimbursementapi.dtos.requests;

public class NewUserRequest {
    private String username;
    private String password;
    private String password_confirmation;


    public NewUserRequest() {
    }

    public NewUserRequest(String username, String password, String password_confirmation) {
        this.username = username;
        this.password = password;
        this.password_confirmation = password_confirmation;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword_confirmation() {
        return password_confirmation;
    }

    public void setPassword_confirmation(String password_confirmation) {
        this.password_confirmation = password_confirmation;
    }
    @Override
    public String toString() {
        return "NewUserRequest{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", password_confirmation='" + password_confirmation + '\'' +
                '}';
    }

}
