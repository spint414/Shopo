package com.example.shopo.dto;

public class CashierDTO {
    private String castUser;
    private String castPassword;
    private String castRole;


    public CashierDTO(String castUser, String castPassword, String castRole) {
        this.castUser = castUser;
        this.castPassword = castPassword;
        this.castRole = castRole;
    }

    public String getCastUser() {
        return castUser;
    }

    public void setCastUser(String castUser) {
        this.castUser = castUser;
    }

    public String getCastPassword() { return castPassword; }

    public void setCastPassword(String castPassword) {
        this.castPassword = castPassword;
    }

    public String getCastRole() {
        return castRole;
    }

    public void setCastRole(String castRole) {
        this.castRole = castRole;
    }

    @Override
    public String toString() {
        return "CashierDTO{" +
                "castUser='" + castUser + '\'' +
                ", castPassword='" + castPassword + '\'' +
                ", castRole='" + castRole + '\'' +
                '}';
    }
}


