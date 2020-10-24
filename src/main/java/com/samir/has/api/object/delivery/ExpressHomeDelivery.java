package com.samir.has.api.object.delivery;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlValue;

@XmlRootElement(name = "deliveryMode")
public class ExpressHomeDelivery implements Delivery {

    @XmlElement(name = "mode")
    private final String modeString = "ExpressHomeDelivery";
    private final static String CITY_REFERENCE = "NICE";
    String city;

    public ExpressHomeDelivery(){}

    public ExpressHomeDelivery(String city) {
        this.city = city;
    }

    public String getModeString() {
        return modeString+ getPrice();
    }

    @XmlElement
    @Override
    public double getPrice() {
        if(city.equals(CITY_REFERENCE))
            return 6.99;
        return 9.99;
    }

    @Override
    public ExpressHomeDelivery getImplementedObject() {
        return this;
    }

}
