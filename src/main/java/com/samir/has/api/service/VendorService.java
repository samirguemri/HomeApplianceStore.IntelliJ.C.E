package com.samir.has.api.service;

import com.samir.has.api.doa.IVendorDao;
import com.samir.has.api.object.person.Vendor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class VendorService {

    private final IVendorDao vendorDao;

    @Autowired
    public VendorService(IVendorDao vendorDao) {
        this.vendorDao = vendorDao;
    }

    public void addVendor(Vendor vendor){
        vendorDao.save(vendor);
    }

    public Vendor selectVendorById(Long vendorId){
        try {
            return vendorDao.findById(vendorId).get();
        } catch (NoSuchElementException e) {
            return null;
        }
    }

    public Vendor selectVendorByLogin(String login, String password){
        for (Vendor vendor : vendorDao.findAll()) {
            if (vendor.getLogin().equals(login) && vendor.getPassword().equals(password))
                return vendor;
        }
        return null;
    }

    public boolean isVendor(String login, String password){
        if (selectVendorByLogin(login,password) != null)
            return true;
        return false;
    }

    public boolean updateVendor(Long vendorId, Vendor vendor) {
        if (vendorDao.save(vendor) != null)
            return true;
        return false;
    }

    public boolean deleteVendorById( Long vendorId){
        vendorDao.deleteById(vendorId);
        return true;
    }

}
