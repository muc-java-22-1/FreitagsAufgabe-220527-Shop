package com.github.mysterix5;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

import static org.junit.jupiter.api.Assertions.*;

class OrderRepoTest {

    public List<Product> createDummyProducts(){
        List<Product> products = new ArrayList<>();

        Product towel = new DigitalProduct("Towel");
        Product pen = new PhysicalProduct("Pen");

        products.add(towel);
        products.add(pen);

        return products;
    }

    @Test
    void list_valid_orders() {
        ProductRepo productRepo = new ProductRepo();
        var products = createDummyProducts();
        productRepo.fill(products);

        OrderRepo orderRepo = new OrderRepo();
        orderRepo.add(productRepo, Arrays.asList(products.get(0).getId(), products.get(1).getId()));
        orderRepo.add(productRepo, List.of(products.get(0).getId()));

        assertTrue(products.containsAll(orderRepo.list().get(0).getProducts()));
        assertTrue(products.containsAll(orderRepo.list().get(1).getProducts()));
    }

    @Test
    void add_empty_productList() {
        ProductRepo productRepo = new ProductRepo();
        var products = createDummyProducts();
        productRepo.fill(products);

        OrderRepo orderRepo = new OrderRepo();
        List<String> orderProductList = new ArrayList<>();
        try {
            orderRepo.add(productRepo, orderProductList);
            fail();
        }catch(RuntimeException e){
            System.err.println(e.getMessage());
        }
    }
    @Test
    void add_valid_product() {
        ProductRepo productRepo = new ProductRepo();
        var products = createDummyProducts();
        productRepo.fill(products);

        OrderRepo orderRepo = new OrderRepo();
        List<String> orderProductList = new ArrayList<>();
        orderProductList.add(products.get(0).getId());

        orderRepo.add(productRepo, orderProductList);

        List<Product> getProductsFromOrder = new ArrayList<>(orderRepo.list().get(0).getProducts());
        products.remove(1);

        assertTrue(getProductsFromOrder.containsAll(products));
        assertTrue(products.containsAll(getProductsFromOrder));
    }
    @Test
    void add_invalid_product() {
        ProductRepo productRepo = new ProductRepo();
        var products = createDummyProducts();
        productRepo.fill(products);

        OrderRepo orderRepo = new OrderRepo();
        List<String> orderProductList = new ArrayList<>();
        orderProductList.add("notValidId");

        try {
            orderRepo.add(productRepo, orderProductList);
            fail();
        }catch(Exception e){
            System.err.println(e.getMessage());
        }

        assertThatExceptionOfType(RuntimeException.class)
                .isThrownBy(()->orderRepo.add(productRepo, orderProductList));
//                .withMessage("Product with id '" + productId + "' is not available!");
    }

    @Test
    void get() {
        ProductRepo productRepo = new ProductRepo();
        var products = createDummyProducts();
        productRepo.fill(products);

        OrderRepo orderRepo = new OrderRepo();
        orderRepo.add(productRepo, Arrays.asList(products.get(0).getId(), products.get(1).getId()));
        orderRepo.add(productRepo, List.of(products.get(0).getId()));

        String[] orderIds = {orderRepo.list().get(0).getId(), orderRepo.list().get(1).getId()};

        assertTrue(products.containsAll(orderRepo.get(orderIds[0]).getProducts()));
        assertTrue(products.containsAll(orderRepo.get(orderIds[1]).getProducts()));
    }
}