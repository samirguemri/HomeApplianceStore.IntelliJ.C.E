package com.samir.has.api.object.delivery;

public interface Delivery<T extends Delivery > {
    double getPrice();
    String getModeString();
    T getImplementedObject();
}
