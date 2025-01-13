package com.example.shopo.entity;

public class Cashier {
    private String castUser;
    private String castPassword;
    private String castRole;

    public Cashier(String castUser, String castPassword, String castRole) {
        this.castUser = castUser;
        this.castPassword = castPassword;
        this.castRole = castRole;
    }

    public String getCastUser() {
        return castUser;
    }

    public void setCastUser(String castlogin) {
        this.castUser = castlogin;
    }

    public String getCastPassword() {
        return castPassword;
    }

    public void setCastPassword(String castPassword) {
        this.castPassword = castPassword;
    }

    public String getCastRole() {
        return castRole;
    }

    public void setCastRole(String castRole) {
        this.castRole = castRole;
    }
}

