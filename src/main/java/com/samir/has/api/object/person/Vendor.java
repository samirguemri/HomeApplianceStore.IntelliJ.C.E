package com.samir.has.api.object.person;


import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 *
 */

@Entity
public class Vendor {

    @Id @GeneratedValue
    @JsonProperty("vendorId") private Long vendorId;
    @JsonProperty("name") private String name;
    @JsonProperty("address") private String address;
    @JsonProperty("zip") private int zip;
    @JsonProperty("phoneNumber") private int phoneNumber;
    @JsonProperty("login") private String login;
    @JsonProperty("password") private String password;

    public Vendor(String name, String address, int zip, int phoneNumber, String login, String password) {
        this.name = name;
        this.address = address;
        this.zip = zip;
        this.phoneNumber = phoneNumber;
        this.login = login;
        this.password = password;
    }

    public Vendor() {  }

    @Override
    public String toString() {
        return "Vendor{" +
                "vendorId=" + vendorId +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", zip=" + zip +
                ", phoneNumber=" + phoneNumber +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    public Long getVendorId() {
        return vendorId;
    }

    public void setVendorId(Long vendorId) {
        this.vendorId = vendorId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getZip() {
        return zip;
    }

    public void setZip(int zip) {
        this.zip = zip;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
