package com.samir.has.api.doa.inmemoriedb;

import com.samir.has.api.doa.ICustomerDao;
import com.samir.has.api.doa.IDatabaseConnection;
import com.samir.has.api.object.LocalUniqueId;
import com.samir.has.api.object.person.Customer;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Repository("fakeCustomerDao")
public class FakeCustomerDaoImpl implements ICustomerDao, IDatabaseConnection {

    private final List<Customer> customerTable;

    public FakeCustomerDaoImpl() {
        customerTable = new ArrayList<>();
        connect();
    }

    @Override
    public void connect() {
        customerTable.add(new Customer("Samir","Corniche Sainte Rosalie Nice","06000","1234567890","samir","pwd"));
        customerTable.add(new Customer("Najah","Av Le Luautey Nice","06100","1234567890","najah","pwd"));
        customerTable.add(new Customer("Haysam","Bd Gambetta Paris","92000","1234567890","haysam","pwd"));
        customerTable.add(new Customer("Mohamed","Quartier Liberation Nice","06100","1234567890","mohamed","pwd"));
        customerTable.add(new Customer("Tarek","Echirolle Grenoble","38000","1234567890","tarek","pwd"));
        customerTable.add(new Customer("Issam","La Garde Freinet Frejus","83180","1234567890","issam","pwd"));
    }

    @Override
    public void disconnect() {

    }

    @Override
    public void insertNewCustomer(Customer customer) {
        customerTable.add(customer);
    }

    @Override
    public Customer selectCustomer(LocalUniqueId customerId) {
        Iterator<Customer> iterator = customerTable.iterator();
        while (iterator.hasNext()){
            Customer customer = iterator.next();
            if (customer.getCustomerId().equals(customerId))
                return customer;
        }
        return null;
    }

    @Override
    public List<Customer> selectALlCustomers() {
        return customerTable;
    }

    @Override
    public boolean updateCustomer(LocalUniqueId customerId, Customer customer) {
        if (removeCustomer(customerId))
            return customerTable.add(customer);
        return false;
    }

    @Override
    public boolean removeCustomer(LocalUniqueId customerId) {
        for (Customer customer : customerTable) {
            boolean equals = customer.getCustomerId().equals(customerId);
            if (equals)
                return customerTable.remove(customer);
        }
        return false;
    }

}
