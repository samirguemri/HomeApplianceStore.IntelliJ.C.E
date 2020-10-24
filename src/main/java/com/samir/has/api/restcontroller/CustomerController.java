package com.samir.has.api.restcontroller;


import com.samir.has.api.service.CustomerService;
import com.samir.has.api.object.person.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/*@RestController("restCustomerController")
@RequestMapping("/has/client")*/
public class CustomerController {

    private final CustomerService customerService;

    @Autowired
    public CustomerController(@Qualifier("customerService") CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping(
            name = "newCustomer",
            path = "nouveau",
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public void addCustomer(@RequestBody Customer customer){
        this.customerService.addCustomer(customer);
    }

    @GetMapping(
            name = "getCustomer",
            path = "/{ref}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public Customer getCustomer(@PathVariable com.samir.has.api.object.LocalUniqueId ref){
        return this.customerService.selectCustomerById(ref);
    }

    @GetMapping(
            name = "getAllCustomers",
            path = "/all",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public List<Customer> getAllCustomers(){
        return this.customerService.selectAllCustomers();
    }

    @PutMapping(
            name = "updateCustomer",
            path = "/update/{ref}",
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public void updateCustomer(@PathVariable com.samir.has.api.object.LocalUniqueId ref, @RequestBody Customer customer){
        this.customerService.updateCustomer(ref,customer);
    }

    @DeleteMapping(
            name = "deleteCustomer",
            path = "/delete/{ref}"
    )
    public void deleteCustomer(@PathVariable com.samir.has.api.object.LocalUniqueId ref){
        this.customerService.removeCustomer(ref);
    }

}
