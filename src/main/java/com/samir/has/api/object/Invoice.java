package com.samir.has.api.object;

import com.samir.has.api.object.delivery.Delivery;
import com.samir.has.api.object.person.CustomerAdapter;

import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.text.DateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

@XmlRootElement(name = "invoice")
@XmlAccessorType(XmlAccessType.FIELD)
public class Invoice {

    @XmlAttribute
    private LocalUniqueId invoiceNumber;

    private String  issueDate;

    @XmlElement(name = "customer")
    @XmlJavaTypeAdapter(CustomerAdapter.class)
    private LocalUniqueId customerId;

    @XmlElement(name = "items")
    @XmlJavaTypeAdapter(MapAdapter.class)
    private Map<LocalUniqueId, Integer> productList;

    @XmlAnyElement private Delivery deliveryMode;

    private double price;
    private boolean paid = false;

    public Invoice(){}

    public Invoice(LocalUniqueId customerId, Delivery deliveryMode) {
        this.invoiceNumber = LocalUniqueId.randomInvoiceUniqueId();
        this.issueDate = DateFormat.getDateInstance(DateFormat.DEFAULT, new Locale("fr", "FR")).format(new Date());
        //this.issueDate = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
        this.customerId = customerId;
        this.productList = new HashMap<LocalUniqueId, Integer>();
        this.deliveryMode = deliveryMode;
        this.price = 0;
        this.paid = false;
    }

    public LocalUniqueId getInvoiceNumber() {
        return invoiceNumber;
    }

    public void setInvoiceNumber(LocalUniqueId invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }

    public String getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(String issueDate) {
        this.issueDate = issueDate;
    }

    public LocalUniqueId getCustomerId() {
        return customerId;
    }

    public void setCustomerId(LocalUniqueId customer) {
        this.customerId = customer;
    }

    public Map<LocalUniqueId, Integer> getProductList() {
        return productList;
    }

    public void setProductList(Map<LocalUniqueId, Integer> productList) {
        this.productList = productList;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public boolean isPaid() {
        return paid;
    }

    public void setPaid(boolean paid) {
        this.paid = paid;
    }

    public Delivery getDeliveryMode() {
        return deliveryMode;
    }

    public void setDeliveryMode(Delivery deliveryMode) {
        this.deliveryMode = deliveryMode;
    }

}
