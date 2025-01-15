package com.example.shopo.entity;

public class Suplay {
    private String suplayerID;
    private String suplayerName;
    private String suplayerAddress;
    private String suplayerPhone;
    private String suplayerContact;

    public Suplay(String suplayerID, String suplayerName, String suplayerAddress, String suplayerPhone, String suplayerContact) {
        this.suplayerID = suplayerID;
        this.suplayerName = suplayerName;
        this.suplayerAddress = suplayerAddress;
        this.suplayerPhone = suplayerPhone;
        this.suplayerContact = suplayerContact;
    }

    public String getSuplayerID() {
        return suplayerID;
    }

    public void setSuplayerID(String suplayerID) {
        this.suplayerID = suplayerID;
    }

    public String getSuplayerName() {
        return suplayerName;
    }

    public void setSuplayerName(String suplayerName) {
        this.suplayerName = suplayerName;
    }

    public String getSuplayerAddress() {
        return suplayerAddress;
    }

    public void setSuplayerAddress(String suplayerAddress) {
        this.suplayerAddress = suplayerAddress;
    }

    public String getSuplayerPhone() {
        return suplayerPhone;
    }

    public void setSuplayerPhone(String suplayerPhone) {
        this.suplayerPhone = suplayerPhone;
    }

    public String getSuplayerContact() {
        return suplayerContact;
    }

    public void setSuplayerContact(String suplayerContact) {
        this.suplayerContact = suplayerContact;
    }
}
