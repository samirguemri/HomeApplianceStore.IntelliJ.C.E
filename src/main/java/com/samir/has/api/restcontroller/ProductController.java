package com.samir.has.api.restcontroller;

import com.samir.has.api.service.ProductService;
import com.samir.has.api.object.product.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

/*@RestController("restProductController")
@RequestMapping("/sms/product")*/
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(@Qualifier("productService") ProductService productService) {
        this.productService = productService;
    }

    @PostMapping(
            name = "addPoduct",
            path = "/add",
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public void addProduct(@RequestBody Product produit){
        this.productService.addProduct(produit);
    }

    @GetMapping(
            name = "get",
            path = "/{ref}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Product> getProduct(@PathVariable("ref") com.samir.has.api.object.LocalUniqueId ref){
        try {
            Product product = this.productService.getProduct(ref);
            return new ResponseEntity<>(product, HttpStatus.OK);
        } catch (NoSuchElementException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(
            name = "getBandProducts",
            path = "/{brand}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public List<Product> getProductsByBrand(@PathVariable("brand") String marque){
        return this.productService.selectProductsByBrand(marque);
    }

    @GetMapping(
            name = "getAllProducts",
            path = "/all",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public List<Product> getAllProducts(){
        return this.productService.selectAllProducts();
    }

    @PutMapping(
            name = "updateProduct",
            path = "/update/{ref}",
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public boolean updateProduct(@PathVariable("ref") com.samir.has.api.object.LocalUniqueId ref, @RequestBody Product produit){
        return this.productService.updateProduct(ref,produit);
    }

    @DeleteMapping(
            name = "deleteProduct",
            path = "/delete/{ref}"
    )
    public boolean removeProduct(@PathVariable("ref") com.samir.has.api.object.LocalUniqueId ref){
        return this.productService.removeProduct(ref);
    }

}
