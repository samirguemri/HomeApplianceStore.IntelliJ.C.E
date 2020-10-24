package com.samir.has.api.object.product;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Fridge extends Product {

    private int capacity;
    private boolean freezer;

    public Fridge(){ }

    public Fridge(String type, String name, String description, double price, int availableStock,
                  int capacity, boolean freezer) {
        super(type, name, description, price, availableStock);
        this.capacity = capacity;
        this.freezer = freezer;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public boolean isFreezer() {
        return freezer;
    }

    public void setFreezer(boolean freezer) {
        this.freezer = freezer;
    }

    @Override
    public Fridge getImplementedObject() {
        return this;
    }
}
