package com.samir.has.api.service;

import com.samir.has.api.doa.ICustomerDao;
import com.samir.has.api.object.LocalUniqueId;
import com.samir.has.api.object.person.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

    private final ICustomerDao customerDao;

    @Autowired
    public CustomerService(@Qualifier("fakeCustomerDao") ICustomerDao customerDao) {
        this.customerDao = customerDao;
    }

    public void addCustomer(Customer customer){
        this.customerDao.insertNewCustomer(customer);
    }

    public Customer selectCustomerById(LocalUniqueId customerId){
        return customerDao.selectCustomer(customerId);
    }

    public Customer selectCustomerByName(String name, String zip){
        for (Customer customer : customerDao.selectALlCustomers()) {
            if (customer.getName().equals(name) && customer.getZip().equals(zip))
                return customer;
        }
        return null;
    }

    public Customer selectCustomerByLogin(String login, String pwd) {
        for (Customer customer : customerDao.selectALlCustomers()) {
            if (customer.getLogin().equals(login) && customer.getPassword().equals(pwd))
                return customer;
        }
        return null;
    }

    public List<Customer> selectAllCustomers(){
        return this.customerDao.selectALlCustomers();
    }

    public boolean updateCustomer(LocalUniqueId customerId, Customer customer){
        return customerDao.updateCustomer(customerId,customer);
    }

    public boolean removeCustomer(LocalUniqueId customerId){
        return customerDao.removeCustomer(customerId);
    }

    public boolean isCustomer(String login, String pwd) {
        if (selectCustomerByLogin(login,pwd) != null)
            return true;
        return false;
    }

}
