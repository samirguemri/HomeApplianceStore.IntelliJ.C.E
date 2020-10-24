package com.samir.has.api.object.delivery;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlValue;

@XmlRootElement(name = "deliveryMode")
public class HomeDelivery implements Delivery {

    @XmlElement(name = "mode")
    private final String modeString = "HomeDelivery";

    @Override
    public String getModeString() {
        return modeString;
    }

    @XmlElement
    @Override
    public double getPrice() {
        return 4.99;
    }

    @Override
    public HomeDelivery getImplementedObject() {
        return this;
    }

}
