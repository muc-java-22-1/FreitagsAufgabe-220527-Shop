package com.github.mysterix5;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductRepo {

    private final Map<String, Product> products = new HashMap<>();

    public ProductRepo(){
    }

    public Product get(String id){
        return products.get(id);
    }

    public List<Product> list(){
        return products.values().stream().toList();
    }

    public void fill(List<Product> products){
        for(Product p: products){
            this.products.put(p.getId(), p);
        }
    }

    public static List<Product> createDummyProducts(){
        List<Product> products = new ArrayList<>();

        Product towel = new DigitalProduct("Towel");
        Product pen = new PhysicalProduct("Pen");

        products.add(towel);
        products.add(pen);

        return products;
    }
}
