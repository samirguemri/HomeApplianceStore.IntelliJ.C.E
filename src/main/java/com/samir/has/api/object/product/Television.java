package com.samir.has.api.object.product;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Television extends Product {

    private int screenSize;
    private String screen;

    public Television(){ }

    public Television(String type, String name, String description, double price, int availableStock, int screenSize, String screen) {
        super(type, name, description, price, availableStock);
        this.screenSize = screenSize;
        this.screen = screen;
    }

    public String getScreen() {
        return screen;
    }

    public void setScreen(String screen) {
        this.screen = screen;
    }

    public int getScreenSize() {
        return screenSize;
    }

    public void setScreenSize(int screenSize) {
        this.screenSize = screenSize;
    }

    @Override
    public Television getImplementedObject() {
        return this;
    }
}
