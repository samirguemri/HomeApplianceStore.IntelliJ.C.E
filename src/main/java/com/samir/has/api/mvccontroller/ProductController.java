package com.samir.has.api.mvccontroller;

import com.samir.has.api.object.LocalUniqueId;
import com.samir.has.api.object.product.Product;
import com.samir.has.api.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Field;

@Controller("mvcProductController")
@RequestMapping("/product")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(@Qualifier("productService") ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/display/{productRef}")
    public String displayProduct(@PathVariable LocalUniqueId productRef, Model model){
        Product product  = productService.getProduct(productRef);
        Field[] abstractObjectFields = Product.class.getDeclaredFields();
        Field[] implementedObjectFields = product.getImplementedObject().getClass().getDeclaredFields();
        //Field[] attributes = Stream.concat(Arrays.stream(abstractObjectFields),Arrays.stream(implementedObjectFields)).toArray(Field[]::new);
        model.addAttribute("product",product.getImplementedObject());
        model.addAttribute("fields",implementedObjectFields);
        return "product/product_home_page";
    }

    @GetMapping("/display/{productRef}")
    public String  displayProduct(final Model model, @PathVariable LocalUniqueId productRef){
        return displayProduct(productRef, model);
    }

}
