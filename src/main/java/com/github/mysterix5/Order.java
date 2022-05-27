package com.github.mysterix5;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Order {
    private final String id;
    private final List<Product> products;

    public List<Product> getProducts() {
        return products;
    }

    public String getId() {
        return id;
    }

    public Order(List<Product> productList){
        id = UUID.randomUUID().toString();
        products = new ArrayList<>(productList);
    }
    public Order(String id, List<Product> productList){
        // TODO custom ids must be unique-checked
        this.id = id;
        products = new ArrayList<>(productList);
    }
}
