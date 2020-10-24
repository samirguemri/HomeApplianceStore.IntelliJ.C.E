package com.samir.has.api.mvccontroller;


import com.samir.has.api.object.ShoppingCart;
import com.samir.has.api.object.LocalUniqueId;
import com.samir.has.api.object.delivery.Cities;
import com.samir.has.api.object.person.Customer;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;



@Controller("mvcBasketController")
@SessionAttributes("shoppingCart")
@RequestMapping("/shoppingCart")
public class shoppingCartController {

    @ModelAttribute("shoppingCart")
    private void addShoppingCartToSessionAttributes(Model model){
        model.addAttribute("shoppingCart",new ShoppingCart());
    }

    @GetMapping("")
    public String basketView(){
        return "shoppingCart/shoppingCart_home_page";
    }

    @PostMapping("/add/{productRef}")
    public String  addProduct(@PathVariable LocalUniqueId productRef,
                              @ModelAttribute("shoppingCart") ShoppingCart shoppingCart,
                              @RequestParam("quantity") int itemQuantity){
        int quantity = 0;
        if (shoppingCart.containsKey(productRef))
            quantity = shoppingCart.get(productRef);
        shoppingCart.put(productRef,quantity+itemQuantity);
        return basketView();
    }

    @PostMapping("/add/{productRef}/{quantity}")
    public String  addProduct(@PathVariable LocalUniqueId productRef,
                              @PathVariable("quantity") int itemQuantity,
                              @ModelAttribute("shoppingCart") ShoppingCart shoppingCart){
        int quantity = 0;
        if (shoppingCart.containsKey(productRef))
            quantity = shoppingCart.get(productRef);
        shoppingCart.put(productRef,quantity+itemQuantity);
        return basketView();
    }

    @PostMapping("/update/{productRef}")
    public String  updateProduct(@PathVariable LocalUniqueId productRef,
                                 @RequestParam("quantity") int quantity,
                                 @ModelAttribute("shoppingCart") ShoppingCart shoppingCart){
        if (quantity > 0)
            shoppingCart.put(productRef,quantity);
        if (quantity == 0)
            shoppingCart.remove(productRef);
        return basketView();
    }

    @GetMapping("/remove/{productRef}")
    public String  removeProduct(@PathVariable("productRef") LocalUniqueId productRef,
                                 @ModelAttribute("shoppingCart") ShoppingCart shoppingCart){
        int quantity = shoppingCart.get(productRef) -1;
        if (quantity > 0)
            shoppingCart.replace(productRef, quantity);
        else
            shoppingCart.remove(productRef);
        return basketView();
    }

    @GetMapping("/buy")
    public String validationPage(@ModelAttribute("shoppingCart") ShoppingCart shoppingCart,
                                 RedirectAttributes redirectAttributes){
        if (shoppingCart.size() != 0){
            redirectAttributes.addFlashAttribute("shoppingCart", shoppingCart);
            return "redirect:/shop/shopping/validation";
        }
        else
            return "redirect:/shop/shopping";
    }

    @PostMapping("/buy/{productRef}")
    public String validationPage(@PathVariable LocalUniqueId productRef,
                                 @RequestParam("quantity") int itemQuantity,
                                 @ModelAttribute("shoppingCart") ShoppingCart shoppingCart,
                                 RedirectAttributes redirectAttributes){
        shoppingCart.put(productRef,itemQuantity);
        redirectAttributes.addFlashAttribute("shoppingCart", shoppingCart);
        return "redirect:/shop/shopping/validation";
    }

    @PostMapping("/payment")
    public String paymentPage(@RequestParam("totalCost") float totalCost, Model model){
        model.addAttribute("totalCost",totalCost);
        model.addAttribute("cities", Cities.values());
        return "shop/payment";
    }

    @PostMapping("/payment/invoice")
    public String invoicePage(@RequestParam("delivery") String delivery,
                              @RequestParam("param") String  param,
                              @RequestParam("totalCost") float totalCost,
                              @SessionAttribute("connectedCustomer") Customer connectedCustomer,
                              @ModelAttribute("shoppingCart") ShoppingCart shoppingCart,
                              final RedirectAttributes redirectAttributes,
                              final Model model){

        if (model.getAttribute("connectedCustomer") == null)
            connectedCustomer = new Customer();
        redirectAttributes.addFlashAttribute("connectedCustomer",connectedCustomer);
        redirectAttributes.addFlashAttribute("delivery",delivery);
        redirectAttributes.addFlashAttribute("param",param);
        redirectAttributes.addFlashAttribute("shoppingCart", shoppingCart);
        redirectAttributes.addFlashAttribute("totalCost",totalCost);

        return "redirect:/invoice/shoppingCart/invoice";
    }
}
