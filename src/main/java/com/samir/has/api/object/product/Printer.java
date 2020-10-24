package com.samir.has.api.object.product;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Printer extends Product {

    private String ink;

    public Printer(){ }

    public Printer(String type, String name, String description, double price,
                   int availableStock, String ink) {
        super(type, name, description, price, availableStock);
        this.ink = ink;
    }

    public String getInk() {
        return ink;
    }

    public void setInk(String ink) {
        this.ink = ink;
    }

    @Override
    public Printer getImplementedObject() {
        return this;
    }
}
