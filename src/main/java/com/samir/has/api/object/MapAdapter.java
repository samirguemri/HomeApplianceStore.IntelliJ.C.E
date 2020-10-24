package com.samir.has.api.object;

import com.samir.has.api.object.product.Product;
import com.samir.has.api.service.ProductService;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapAdapter extends XmlAdapter< MapAdapter.AdaptedMap, Map<LocalUniqueId, Integer> >{

    final ProductService productService;

    public MapAdapter(ProductService productService) {
        this.productService = productService;
    }

    public static class AdaptedMap {
        @XmlElement(name="item")
        public List<AdaptedEntry> entries = new ArrayList();
    }

    public static class AdaptedEntry {
        @XmlElement(name="product") public Product product;
        @XmlElement public Integer quantity;
    }

    @Override
    public AdaptedMap marshal(Map<LocalUniqueId, Integer> productMap) throws Exception {
        AdaptedMap adaptedMap = new AdaptedMap();
        for (Map.Entry<LocalUniqueId, Integer> entry : productMap.entrySet()) {
            AdaptedEntry adaptedEntry = new AdaptedEntry();
            adaptedEntry.product = productService.getProduct(entry.getKey());
            adaptedEntry.quantity = entry.getValue();
            adaptedMap.entries.add(adaptedEntry);
        }
        return adaptedMap;
    }

    @Override
    public Map<LocalUniqueId, Integer> unmarshal(AdaptedMap adaptedMap) throws Exception {
        Map<LocalUniqueId, Integer> map = new HashMap<>();
        for ( AdaptedEntry entry : adaptedMap.entries) {
            map.put(entry.product.getProductRef(),entry.quantity);
        }
        return map;
    }

}
