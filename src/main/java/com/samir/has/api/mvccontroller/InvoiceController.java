package com.samir.has.api.mvccontroller;

import com.samir.has.api.object.ShoppingCart;
import com.samir.has.api.object.Invoice;
import com.samir.has.api.object.LocalUniqueId;
import com.samir.has.api.object.delivery.*;
import com.samir.has.api.object.person.Customer;
import com.samir.has.api.service.InvoiceService;
import org.apache.fop.apps.FOPException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.xml.transform.TransformerException;
import java.io.IOException;
import java.text.DecimalFormat;

@Controller("mvcBillController")
@RequestMapping("/invoice")
public class InvoiceController {

    private final InvoiceService invoiceService;

    @Autowired
    public InvoiceController(@Qualifier("invoiceService") InvoiceService invoiceService) {
        this.invoiceService = invoiceService;
    }

    @GetMapping("/shoppingCart/invoice")
    public String createInvoice(@ModelAttribute("delivery") String delivery,
                                @ModelAttribute("param") String param,
                                @ModelAttribute("totalCost") float totalCost,
                                @SessionAttribute("connectedCustomer") Customer customer,
                                @ModelAttribute("shoppingCart") ShoppingCart shoppingCart,
                                Model model){
        Delivery deliveryMode = getDeliveryMode(delivery,param);
        if (customer == null)
            customer = new Customer();
        Invoice invoice = shoppingCartToInvoice(customer,deliveryMode, shoppingCart,totalCost);
        invoiceService.addInvoice(invoice);
        model.addAttribute("invoice", invoice);
        return "invoice/invoice";
    }

    private Delivery getDeliveryMode(String delivery, String param){
        Delivery deliveryMode;
        switch (delivery){
            case "home": deliveryMode = new HomeDelivery();break;
            case "express": deliveryMode = new ExpressHomeDelivery(param);break;
            case "relay": deliveryMode = new RelayPointDelivery(Integer.parseInt(param));break;
            default: deliveryMode = new StorePickup();break;
        }
        return deliveryMode;
    }

    private Invoice shoppingCartToInvoice(Customer customer, Delivery deliveryMode, ShoppingCart shoppingCart, float totalCost){
        Invoice invoice = new Invoice(customer.getCustomerId(),deliveryMode);
        invoice.setProductList(shoppingCart);
        invoice.setPrice( (double)totalCost + deliveryMode.getPrice());
        return invoice;
    }

    @PostMapping("/send")
    public String mailingInvoice(@RequestParam("email") String email,
                                 @RequestParam("invoiceNumber") LocalUniqueId invoiceNumber,
                                 Model model){
        try {
            invoiceService.generatePdf(invoiceNumber);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (FOPException e) {
            e.printStackTrace();
        } catch (TransformerException e) {
            e.printStackTrace();
        }

        model.addAttribute("email",email);
        return "invoice/send";
    }

}
