package com.guwor.reimbursementapi.models;

public class UserRole {

    private String role_id;
    private String role;

    public UserRole() {
    }

    public UserRole(String role_id, String role) {
        this.role_id = role_id;
        this.role = role;
    }

    public String getRole_id() {
        return role_id;
    }

    public void setRole_id(String role_id) {
        this.role_id = role_id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "UserRole{" +
                "role_id='" + role_id + '\'' +
                ", role='" + role + '\'' +
                '}';
    }


}
