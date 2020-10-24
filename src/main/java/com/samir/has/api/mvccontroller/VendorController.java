package com.samir.has.api.mvccontroller;


import com.samir.has.api.object.LocalUniqueId;
import com.samir.has.api.object.person.Customer;
import com.samir.has.api.service.CustomerService;
import com.samir.has.api.service.VendorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@Controller("mvcVendorController")
@RequestMapping("/vendor")
public class VendorController {

    private final VendorService vendorService;
    private final CustomerService customerService;
    private Customer theCustomer = null;

    @Autowired
    public VendorController(VendorService vendorService, @Qualifier("customerService") CustomerService customerService) {
        this.vendorService = vendorService;
        this.customerService = customerService;
    }

    public String getClassName(){
        return this.getClass().getSimpleName();
    }

    @GetMapping()
    public String signingVendor(){
        return "vendor/login";
    }

    @PostMapping("/signing")
    public String vendorSigningForm(String login, String pwd){
        if (vendorService.isVendor(login,pwd))
            return "vendor/vendor_home_page";
        else return "vendor/login";
    }

    @GetMapping("/newCustomer")
    public String newCustomerForm(Model model){
        model.addAttribute("newCustomer", new Customer());
        return "vendor/new_customer";
    }

    @PostMapping("/newCustomer/save")
    public String saveNewCustomer(@ModelAttribute Customer customer){
        customerService.addCustomer(customer);
        return "vendor/vendor_home_page";
    }

    @GetMapping("/customers/display/{customerId}")
    public String displayCustomerById(@PathVariable String customerId, Model model){
        List<Customer> list = new ArrayList<>();
        list.add(customerService.selectCustomerById(new LocalUniqueId(customerId)));
        model.addAttribute("customerList",list);
        return "vendor/display_customers";
    }

    @GetMapping("/customers/display/{zip}/{name}")
    public String displayCustomerByName(@PathVariable String name,@PathVariable String zip, Model model){
        List<Customer> list = new ArrayList<>();
        list.add(customerService.selectCustomerByName(name,zip));
        model.addAttribute("customerList",list);
        return "vendor/display_customers";
    }

    @GetMapping("/customers/display/all")
    public String displayAllCustomers(Model model){
        model.addAttribute("customerList", customerService.selectAllCustomers());
        return "vendor/display_customers";
    }

    @GetMapping("/customers/update/{zip}/{name}")
    public String  updateCustomerForm(@PathVariable String name, @PathVariable String zip, Model model){
        Customer customer = customerService.selectCustomerByName(name,zip);
        model.addAttribute("customer",customer);
        return "vendor/update_customer";
    }

    @PostMapping("/customers/update/{customerId}")
    public String  updateCustomerById(@PathVariable LocalUniqueId customerId, @ModelAttribute Customer customer){
        customerService.updateCustomer(customerId,customer);
        return "vendor/vendor_home_page";
    }

    @PostMapping("/customers/update/{zip}/{name}")
    public String updateCustomerByName(@PathVariable String name, @PathVariable String zip, @ModelAttribute Customer customer){
        customerService.updateCustomer(customerService.selectCustomerByName(name,zip).getCustomerId(),customer);
        return "vendor/vendor_home_page";
    }

    @PostMapping("/customers/remove/{customerId}")
    public String  deleteCustomer(@PathVariable LocalUniqueId customerId){
        customerService.removeCustomer(customerId);
        return "vendor/vendor_home_page";
    }

    @PostMapping("/customers/remove/{zip}/{name}")
    public String  deleteCustomer(@PathVariable String name, @PathVariable String zip){
        customerService.removeCustomer(customerService.selectCustomerByName(name,zip).getCustomerId());
        return "vendor/vendor_home_page";
    }

    @GetMapping("/customers/selected")
    public String selectedCustomer(@ModelAttribute Customer customer){
        this.theCustomer = customer;
        return "shop/store_home_page";
    }

}
