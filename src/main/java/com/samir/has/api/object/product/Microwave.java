package com.samir.has.api.object.product;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Microwave extends Product {

    private int capacity;
    private int power;

    public Microwave(){ }

    public Microwave(String type, String name, String description, double price, int availableStock,
                     int capacity, int power) {
        super(type, name, description, price, availableStock);
        this.capacity = capacity;
        this.power = power;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }

    @Override
    public Microwave getImplementedObject() {
        return this;
    }
}
