package com.github.mysterix5;

import java.util.List;
import java.util.NoSuchElementException;

public class ShopService {
    private final ProductRepo productRepo;
    private final OrderRepo orderRepo;

    public ShopService(ProductRepo productRepo, OrderRepo orderRepo){
        this.productRepo = productRepo;
        this.orderRepo = orderRepo;
    }

    public List<Product> getAllProductsContainingName(String name){
        return productRepo.getAllProductsContainingName(name);
    }
    public Product getFirstProductByExactName(String name){
        return productRepo.getFirstProductByExactName(name).orElseThrow(()->new NoSuchElementException("Product with name '" + name + "' is not available!"));
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
