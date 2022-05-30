package com.github.mysterix5;

import java.util.List;

public class ShopService {
    private final ProductRepo productRepo;
    private final OrderRepo orderRepo;

    public ShopService(ProductRepo productRepo, OrderRepo orderRepo){
        this.productRepo = productRepo;
        this.orderRepo = orderRepo;
    }

    public Product getProduct(String productId){

        return productRepo.get(productId).orElseThrow(()->new RuntimeException("Product with id '" + productId + "' is not available!"));
    }

    public List<Product> listProducts(){
        return productRepo.list();
    }

    public String addOrder(List<String> productIds){
        try{
            return orderRepo.add(productRepo, productIds);
        }catch(RuntimeException e){
            throw new RuntimeException("Adding order failed", e);
        }
    }

    public Order getOrder(String orderId){
        return orderRepo.get(orderId).orElseThrow(()->new RuntimeException("Order with id '" + orderId + "' is not available!"));
    }

    public List<Order> listOrders(){

        return orderRepo.list();
    }
}
