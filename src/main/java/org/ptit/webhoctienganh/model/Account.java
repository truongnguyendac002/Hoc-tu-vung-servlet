package org.ptit.webhoctienganh.model;

public class Account {
    private String accountID;
    private String username;
    private String password;
    private String email;
    private String role;
    private int status;

    public Account(String accountID, String username, String password, String email, String role, int status) {
        this.accountID = accountID;
        this.username = username;
        this.password = password;
        this.email = email;
        this.role = role;
        this.status = status;
    }

    public String getAccountID() {
        return accountID;
    }

    public void setAccountID(String accountID) {
        this.accountID = accountID;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
