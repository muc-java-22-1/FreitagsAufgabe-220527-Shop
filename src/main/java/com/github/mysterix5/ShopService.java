package com.github.mysterix5;

import java.util.ArrayList;
import java.util.List;

public class ShopService {
    private final ProductRepo productRepo;
    private final OrderRepo orderRepo;

    public ShopService(ProductRepo productRepo, OrderRepo orderRepo){
        this.productRepo = productRepo;
        this.orderRepo = orderRepo;
    }

    public Product getProduct(String productId){

        return productRepo.get(productId);
    }

    public List<Product> listProducts(){
        return productRepo.list();
    }

    public String addOrder(List<String> productIds){
        return orderRepo.add(productRepo, productIds);
    }

    public Order getOrder(String orderId){
        return orderRepo.get(orderId);
    }

    public List<Order> listOrders(){

        return orderRepo.list();
    }
}
