package com.samir.has.api.object.product;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.samir.has.api.object.LocalUniqueId;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


@XmlAccessorType(XmlAccessType.FIELD)
public abstract class Product<T extends Product> {

    @XmlElement @JsonProperty("productRef") LocalUniqueId productRef;
    @XmlElement @JsonProperty("productType") String productType;
    @XmlElement @JsonProperty("name") String name;
    @XmlElement @JsonProperty("description") String description;
    @XmlElement @JsonProperty("price") double price;
    @JsonProperty("availableStock") int availableStock;

    public Product() { }

    public Product(String productType, String name, String description, double price, int availableStock) {
        this.productRef = LocalUniqueId.randomProductUniqueId();
        this.productType = productType;
        this.name = name;
        this.description = description;
        this.price = price;
        this.availableStock = availableStock;
    }

    public abstract T getImplementedObject();

    public LocalUniqueId getProductRef() {
        return productRef;
    }

    public void setProductRef(LocalUniqueId productRef) {
        this.productRef = productRef;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getAvailableStock() {
        return availableStock;
    }

    public void setAvailableStock(int availableStock) {
        this.availableStock = availableStock;
    }
}
