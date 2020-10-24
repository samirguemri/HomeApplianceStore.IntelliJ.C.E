package com.samir.has.api.object.person;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.samir.has.api.object.Invoice;
import com.samir.has.api.object.LocalUniqueId;

import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.util.List;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Customer {

    @XmlAttribute
    @JsonProperty("customerId")
    private LocalUniqueId customerId;

    @XmlElement
    @JsonProperty("name")
    private String name;

    @XmlElement
    @JsonProperty("address")
    private String address;

    @XmlElement
    @JsonProperty("zip")
    private String  zip;

    @XmlElement
    @JsonProperty("phoneNumber")
    private String phoneNumber;

    @JsonProperty("billList")
    private List<Invoice> invoiceList;

    @JsonProperty("login")
    private String login;

    @JsonProperty("password")
    private String password;

    public Customer(String name, String address, String zip, String phoneNumber, String login, String password) {
        this.customerId = LocalUniqueId.randomCustomerUniqueId();
        this.name = name;
        this.address = address;
        this.zip = zip;
        this.phoneNumber = phoneNumber;
        this.login = login;
        this.password = password;
    }

    public Customer() {
        this.customerId = LocalUniqueId.randomCustomerUniqueId();
        this.name = "Unknown";
        this.address = "Unknown";
        this.zip = "Unknown";
        this.phoneNumber = "Unknown";
        this.login = "Unknown";
        this.password = "Unknown";
    }

    @Override
    public String toString() {
        return "Customer{" +
                "customerId=" + customerId +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", zip='" + zip + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", billList=" + invoiceList +
                '}';
    }

    public LocalUniqueId getCustomerId() {
        return customerId;
    }

    public void setCustomerId(LocalUniqueId customerId) {
        this.customerId = customerId;
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

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public List<Invoice> getBillList() {
        return invoiceList;
    }

    public void setBillList(List<Invoice> invoiceList) {
        this.invoiceList = invoiceList;
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
