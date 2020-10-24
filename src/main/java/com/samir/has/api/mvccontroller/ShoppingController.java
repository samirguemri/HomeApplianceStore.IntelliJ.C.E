package com.samir.has.api.mvccontroller;

import com.samir.has.api.object.ShoppingCart;
import com.samir.has.api.object.LocalUniqueId;
import com.samir.has.api.object.product.Product;
import com.samir.has.api.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller("mvcShoppingController")
@SessionAttributes("productList")
@RequestMapping("/shop")
public class ShoppingController {

    private final ProductService productService;

    @Autowired
    public ShoppingController(@Qualifier("productService") ProductService productService) {
        this.productService = productService;
    }

    @ModelAttribute("productList")
    private void addProductListSessionAttribute(Model model){
        model.addAttribute("productList",productService.selectAllProducts());         ;
    }

    @GetMapping("/shopping")
    public String storeHomePageGET(Model model){
        return "shop/store_home_page";
    }

    @PostMapping("/shopping")
    public String storeHomePagePOST(Model model){
        return "shop/store_home_page";
    }

    @GetMapping("/shopping/validation")
    public String shoppingValidation(@SessionAttribute("shoppingCart") ShoppingCart shoppingCart,
                                     @ModelAttribute("productList") List<Product> productList,
                                     Model model){
        model.addAttribute("shoppingCart", shoppingCart);
        model.addAttribute("productList",productList);
        model.addAttribute("totalCost",calculateTotalCost(shoppingCart,productList));
        return "shop/validate_basket";
    }

    private float calculateTotalCost(ShoppingCart shoppingCart, List<Product> productList){
        float total = 0;
        for (Map.Entry<LocalUniqueId,Integer> entry : shoppingCart.entrySet()){
            double price = productList.stream()
                                    .filter(product -> product.getProductRef().equals(entry.getKey()))
                                    .findFirst().get()
                                    .getPrice();
            total += price * entry.getValue();

        }
        return total;
    }

}
