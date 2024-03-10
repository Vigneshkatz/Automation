package org.smytten.user;

public class Address {
    public final String firstName;
    public final String lastName;
    public final String email;
    public final String phone;
    public final String landmark;
    public final String houseNumber;
    public final String streetName;
    public final String pinCode;
    public final String city;
    public final String state;

    public Address(String firstName, String lastName, String email, String phone, String landmark, String houseNumber, String streetName, String pinCode, String city, String state) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
        this.landmark = landmark;
        this.houseNumber = houseNumber;
        this.streetName = streetName;
        this.pinCode = pinCode;
        this.city = city;
        this.state = state;
    }

}