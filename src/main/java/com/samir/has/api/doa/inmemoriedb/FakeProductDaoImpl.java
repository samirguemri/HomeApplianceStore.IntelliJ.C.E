package com.samir.has.api.doa.inmemoriedb;

import com.samir.has.api.doa.IDatabaseConnection;
import com.samir.has.api.doa.IProductDao;
import com.samir.has.api.object.product.*;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Repository("fakeProductDao")
public class FakeProductDaoImpl implements IProductDao, IDatabaseConnection {

    private final List<Product> productTable;

    public FakeProductDaoImpl() {
        this.productTable = new ArrayList<>();
        this.connect();
    }

    @Override
    public void connect() {
        productTable.add(new Television("televiseur","Samsung","Description Televiseur Samsung",599.99,10,40,"FullHD"));
        productTable.add(new Television("televiseur","Philips","Description Televiseur Philips",489.99,8,520,"FullHD"));
        productTable.add(new Fridge("refregirateur","Beko","Description Refregerateur Beko",299.99,8,277,true));
        productTable.add(new Fridge("refregirateur","Samsung","Description Refregerateur Samsung",449,5,350,true));
        productTable.add(new CoffeeMachine("machine_cafe","Nespresso","Description Machine a cafe Nespresso",99,20,"cafe"));
        productTable.add(new CoffeeMachine("machine_cafe","Delonghi","Description Machine a cafe Delonghi",199.49,10,"cafe"));
        productTable.add(new Printer("imprimante","Hp","Description Imprimante Hp",49,30,"hp302"));
        productTable.add(new Printer("imprimante","Epson","Description Imprimante Epson",89.99,20,"e113b"));
        productTable.add(new Oven("four","Hotpoint","Description Four Hotpoint",79.49,23,15,75));
        productTable.add(new Microwave("micro_onde ","Samsung","Description Micro Onde Samsung",99,15,12,60));
        productTable.add(new Microwave("micro_onde ","Beko","Description Micro Onde Beko",69,5,18,75));
        productTable.add(new Phone("telephone","Iphone","Description telephone Iphone",999,10,4.7));
        productTable.add(new Phone("telephone","Samsung","Description telephone Samsung",599.99,10,4));
        productTable.add(new Phone("telephone","OnePlus","Description telephone OnePlus",449.99,10,5.2));
        productTable.add(new Phone("telephone","Huawai","Description telephone Huawai",399,10,5.9));
    }

    @Override
    public void disconnect() {    }

    @Override
    public void insertNewProduct(Product produit) {
        String type = produit.getProductType();
        switch (type){
            case "four":{
                Oven oven = (Oven) produit;
                productTable.add(oven);
            }
            case "micro_onde":{
                Microwave microwave = (Microwave) produit;
                productTable.add(microwave);
            }
            case "imprimante":{
                Microwave microwave = (Microwave) produit;
                productTable.add(microwave);
            }
            case "machine_cafe":{
                CoffeeMachine coffeeMachine = (CoffeeMachine) produit;
                productTable.add(coffeeMachine);
            }
            case "refrigerateur":{
                Fridge fridge = (Fridge) produit;
                productTable.add(fridge);
            }
            case "televiseur":{
                Television television = (Television) produit;
                productTable.add(television);
            }
            case "telephone":{
                Phone phone = (Phone) produit;
                productTable.add(phone);
            }
        }
    }

    @Override
    public Product selectProduct(com.samir.has.api.object.LocalUniqueId reference) {
        Iterator<Product> iterator = productTable.iterator();
        while (iterator.hasNext()){
            Product product = iterator.next();
            if (product.getProductRef().equals(reference))
                return product;
        }
        return null;
    }

    @Override
    public List<Product> selectAllProducts() {
        return productTable;
    }

    @Override
    public List<Product> selectProductsByBrand(String marque) {
        List<Product> brandProducts = new ArrayList<>();
        for (Product product : productTable) {
            if (product.getProductType().equals(marque))
                brandProducts.add(product);
        }
        return brandProducts;
    }

    @Override
    public boolean updateProduct(com.samir.has.api.object.LocalUniqueId reference, Product newProduct) {
        if(removeProduct(reference))
            return productTable.add(newProduct);
        return false;
    }

    @Override
    public boolean removeProduct(com.samir.has.api.object.LocalUniqueId reference) {
        Iterator<Product> iterator = productTable.iterator();
        while (iterator.hasNext()){
            Product product = iterator.next();
            if (product.getProductRef().equals(reference))
                return productTable.remove(product);
        }
        return false;
    }
}
