package com.samir.has.api.service;

import com.samir.has.api.doa.IProductDao;
import com.samir.has.api.object.LocalUniqueId;
import com.samir.has.api.object.product.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("productService")
public class ProductService {

    private final IProductDao productDao;

    @Autowired
    public ProductService(@Qualifier("fakeProductDao") IProductDao productDao) {
        this.productDao = productDao;
    }

    public void addProduct(Product product){
        this.productDao.insertNewProduct(product);
    }

    public Product getProduct(LocalUniqueId productRef){
        return this.productDao.selectProduct(productRef);
    }

    public List<Product> selectProductsByBrand(String marque){
        return this.productDao.selectProductsByBrand(marque);
    }


    public List<Product> selectAllProducts(){
        return this.productDao.selectAllProducts();
    }

    public boolean updateProduct(LocalUniqueId productRef, Product product){
        return this.productDao.updateProduct(productRef,product);
    }

    public boolean removeProduct(LocalUniqueId productRef){
        return this.productDao.removeProduct(productRef);
    }
    public void addStock(LocalUniqueId productRef, int newStock){
        Product product = this.productDao.selectProduct(productRef);
        product.setAvailableStock(newStock);
        this.productDao.updateProduct(productRef,product);
    }

}
