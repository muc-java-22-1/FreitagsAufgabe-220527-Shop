package com.github.mysterix5;

import java.util.*;

public class OrderRepo {
    Map<String, Order> orders = new HashMap<>();

    public List<Order> list(){

        return orders.values().stream().toList();
    }

    public String add(ProductRepo productRepo, List<String> productIds){
        // TODO abort complete order if one product fails, or print error and add the others?
        if(productIds.isEmpty()) {
            throw new RuntimeException("It is not possible to add an order with zero products!");
        }
        List<Product> productsInOrder = productIds.stream()
                .map(p -> productRepo.get(p).orElseThrow(
                        ()->new NoSuchElementException("Product with id '" + p + "' is not available!")))
                .toList();
        Order order = new Order(productsInOrder);
        orders.put(order.getId(), order);

        return order.getId();
    }

    public Optional<Order> get(String id){
        return Optional.ofNullable(orders.get(id));
    }
}
