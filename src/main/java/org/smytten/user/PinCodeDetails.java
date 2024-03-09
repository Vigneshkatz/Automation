package org.smytten.user;

public class PinCodeDetails {
   public String pinCode;
    public String city;
   public String state;

    public PinCodeDetails(String pinCode, String city, String state) {
        this.pinCode = pinCode;
        this.city = city;
        this.state = state;
    }
}