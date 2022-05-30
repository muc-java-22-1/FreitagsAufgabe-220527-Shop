package com.github.mysterix5;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class ProductRepo {

    private final Map<String, Product> products = new HashMap<>();

    public Optional<Product> get(String id){
        return Optional.ofNullable(products.get(id));
    }

    public List<Product> list(){
        return products.values().stream().toList();
    }

    public void fill(List<Product> products){
        for(Product p: products){
            this.products.put(p.getId(), p);
        }
    }

    public Optional<Product> getProductByName(String name){
        return products.values().stream()
                .filter(product -> product.getName().equals(name))
                .findFirst();
    }

}
