package com.samir.has.api.object.delivery;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlValue;

@XmlRootElement(name = "deliveryMode")
public class StorePickup implements Delivery {

    @XmlElement(name = "mode")
    private final String modeString = "StorePickup";

    public String getModeString() {
        return modeString;
    }

    @XmlElement
    @Override
    public double getPrice() {
        return 0;
    }

    @Override
    public StorePickup getImplementedObject() {
        return this;
    }
}
