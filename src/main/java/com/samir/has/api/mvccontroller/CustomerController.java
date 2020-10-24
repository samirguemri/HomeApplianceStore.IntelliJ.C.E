package com.samir.has.api.mvccontroller;


import com.samir.has.api.object.person.Customer;
import com.samir.has.api.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Controller("mvcCustomerController")
@SessionAttributes("connectedCustomer")
@RequestMapping("/customer")
public class CustomerController {

    private Customer connectedCustomer = null;
    private final CustomerService customerService;

    @Autowired
    public CustomerController(@Qualifier("customerService") CustomerService customerService) {
        this.customerService = customerService;
    }

    @ModelAttribute("connectedCustomer")
    private void addConnectedCustomerSessionAttribute(Model model){
        model.addAttribute("connectedCustomer",connectedCustomer);
    }

    public String getClassName(){
        return this.getClass().getSimpleName();
    }

    @GetMapping("/login")
    public String loginCustomer(){
        return "customer/login";
    }

    @PostMapping("/login")
    public String customerLoginForm(Model model, String login, String pwd){
        if (customerService.isCustomer(login,pwd)){
            connectedCustomer = customerService.selectCustomerByLogin(login,pwd);
            model.addAttribute("connectedCustomer",connectedCustomer);
            return "customer/customer_home_page";
        }
        else return "customer/login";
    }

    @GetMapping("/signup")
    public String customerRegistrationForm(Model model){
        model.addAttribute("newCustomer", new Customer());
        return "customer/new_customer";
    }

    @PostMapping("/new/save")
    public String saveNewCustomer(@ModelAttribute Customer customer){
        customerService.addCustomer(customer);
        return "index";
    }

    @GetMapping("/me")
    public String editCustomerProfile(Model model){
        model.addAttribute("connectedCustomer",connectedCustomer);
        return "customer/update_customer";
    }

    @PostMapping("/update")
    public String updateCustomer(@ModelAttribute Customer customer){
        customerService.updateCustomer(customer.getCustomerId(),customer);
        return "customer/customer_home_page";
    }

}
