package com.github.mysterix5;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrderRepo {
    Map<String, Order> orders = new HashMap<>();

    public List<Order> list(){

        return orders.values().stream().toList();
    }

    public void add(ProductRepo productRepo, List<String> productIds){
        // TODO abort complete order if one product fails, or print error and add the others?
        if(productIds.isEmpty()) throw new RuntimeException("It is not possible to add an order with zero products!");
        List<Product> productsInOrder = new ArrayList<>();
        for(String p: productIds){
            checkProductInRepo(productRepo, p);
            productsInOrder.add(productRepo.get(p));
        }
        Order order = new Order(productsInOrder);
        orders.put(order.getId(), order);
    }

    private void checkProductInRepo(ProductRepo productRepo, String productId){
        if(productRepo.get(productId)==null)
            throw new RuntimeException("Product with id " + productId + " is not available!");
        // TODO check if product is generally available but not in stock?
    }

    public Order get(String id){
        return orders.get(id);
    }
}
