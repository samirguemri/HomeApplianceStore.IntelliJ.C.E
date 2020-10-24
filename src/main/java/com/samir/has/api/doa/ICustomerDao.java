package com.samir.has.api.doa;

import com.samir.has.api.object.LocalUniqueId;
import com.samir.has.api.object.person.Customer;

import java.util.List;

public interface ICustomerDao {

    void insertNewCustomer(Customer customer);
    Customer selectCustomer(LocalUniqueId customerId);
    List<Customer> selectALlCustomers();
    boolean updateCustomer(LocalUniqueId customerId, Customer customer);
    boolean removeCustomer(LocalUniqueId customerId);
}
