package com.samir.has.api.doa;

import com.samir.has.api.object.product.Product;

import java.util.List;

public interface IProductDao {

    void insertNewProduct(Product produit);
    Product selectProduct(com.samir.has.api.object.LocalUniqueId reference);
    List<Product> selectAllProducts();
    List<Product> selectProductsByBrand(String marque);
    boolean updateProduct(com.samir.has.api.object.LocalUniqueId reference, Product newProduct);
    boolean removeProduct(com.samir.has.api.object.LocalUniqueId reference);
}
