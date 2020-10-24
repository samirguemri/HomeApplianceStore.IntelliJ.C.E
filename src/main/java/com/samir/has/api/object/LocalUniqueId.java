package com.samir.has.api.object;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.xml.bind.annotation.XmlValue;
import java.security.SecureRandom;
import java.util.Objects;
import java.util.UUID;

public class LocalUniqueId {

    private static final String numbers = "0123456789";
    private static final String upperCase = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String lowerCase = "abcdefghijklmnopqrstuvwxyz";

    private static SecureRandom random = new SecureRandom();

    private static String randomString(String str, int length){
        StringBuilder strBuilder = new StringBuilder( length );
        for( int i = 0; i < length; i++ )
            strBuilder.append( str.charAt( random.nextInt(str.length()) ) );
        return strBuilder.toString();
    }

    private final String defaultUid = UUID.randomUUID().toString();
    private final String customerUid = randomString(numbers,4);
    private final String invoiceUid = randomString(numbers,6);
    private final String productUid = randomString(numbers,8);

    private final static int __DEFAULT__ = 0;
    private final static int __CUSTOMER_ = 1;
    private final static int __INVOICE__ = 2;
    private final static int __PRODUCT__ = 3;

    @XmlValue
    @JsonProperty("uid")
    private String uid = null;

    public LocalUniqueId(String uid){
        this.uid = uid;
    }

    private LocalUniqueId(final int x){
        extractUId(x);
        String suffix;
        switch (x){
            case __CUSTOMER_:  suffix = "Cust_";
                break;
            case __INVOICE__:  suffix = "INV/";
                break;
            case __PRODUCT__:  suffix = "P_";
                break;
            default: suffix = "";
        }
        uid = suffix.concat(uid);
    }

    private String  extractUId(final int x){
        switch (x){
            case __CUSTOMER_:  uid = customerUid;
                break;
            case __INVOICE__:  uid = invoiceUid;
                break;
            case __PRODUCT__:  uid = productUid;
                break;
            default: uid = defaultUid;
        }
        String[] divided = uid.split("-");
        return uid = divided[0];
    }

    public static LocalUniqueId randomUniqueId() {
        return new LocalUniqueId(LocalUniqueId.__DEFAULT__);
    }

    public static LocalUniqueId randomCustomerUniqueId() {
        return new LocalUniqueId(LocalUniqueId.__CUSTOMER_);
    }

    public static LocalUniqueId randomInvoiceUniqueId() {
        return new LocalUniqueId(LocalUniqueId.__INVOICE__);
    }

    public static LocalUniqueId randomProductUniqueId() {
        return new LocalUniqueId(LocalUniqueId.__PRODUCT__);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uid);
    }

    @Override
    public boolean equals(Object obj){
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (!(obj instanceof LocalUniqueId))
            return false;
        return this.uid.equals( ( (LocalUniqueId) obj).uid);
    }

    @Override
    public String toString(){
        return uid;
    }
    
}
