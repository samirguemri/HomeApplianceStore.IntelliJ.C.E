package com.samir.has.api.object.product;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Phone extends Product {

    private double screenSize;

    public Phone(){ }

    public Phone(String type, String name, String description, double price,
                 int availableStock, double screenSize) {
        super(type, name, description, price, availableStock);
        this.screenSize = screenSize;
    }

    public double getScreenSize() {
        return screenSize;
    }

    public void setScreenSize(double screenSize) {
        this.screenSize = screenSize;
    }

    @Override
    public Phone getImplementedObject() {
        return this;
    }
}
