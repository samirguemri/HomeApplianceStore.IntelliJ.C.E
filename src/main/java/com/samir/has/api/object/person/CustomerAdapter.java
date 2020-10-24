package com.samir.has.api.object.person;

import com.samir.has.api.object.LocalUniqueId;
import com.samir.has.api.service.CustomerService;
import org.springframework.stereotype.Service;

import javax.xml.bind.annotation.adapters.XmlAdapter;

@Service
public class CustomerAdapter extends XmlAdapter<Customer, LocalUniqueId> {

    final private CustomerService customerService;

    public CustomerAdapter(CustomerService customerService) {
        this.customerService = customerService;
    }

    @Override
    public Customer marshal(LocalUniqueId customerId) throws Exception {
        Customer customer = customerService.selectCustomerById(customerId);
        return customer != null ? customer : new Customer();
    }

    @Override
    public LocalUniqueId unmarshal(Customer customer) throws Exception {
        return customer.getCustomerId();
    }
}
