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
        String order0Id = orderRepo.add(productRepo, Arrays.asList(products.get(0).getId(), products.get(1).getId()));
        String order1Id = orderRepo.add(productRepo, List.of(products.get(0).getId()));

        assertThat(orderRepo.get(order0Id).getProducts()).extracting(Product::getName).contains("Towel", "Pen");
        assertThat(orderRepo.get(order1Id).getProducts()).extracting(Product::getName).contains("Towel");
    }

    @Test
    void add_empty_productList() {
        ProductRepo productRepo = new ProductRepo();
        var products = createDummyProducts();
        productRepo.fill(products);

        OrderRepo orderRepo = new OrderRepo();
        List<String> orderProductList = new ArrayList<>();

        assertThatExceptionOfType(RuntimeException.class)
                .isThrownBy(() -> orderRepo.add(productRepo, orderProductList))
                .withMessage("It is not possible to add an order with zero products!");
    }
    @Test
    void add_valid_product() {
        ProductRepo productRepo = new ProductRepo();
        var products = createDummyProducts();
        productRepo.fill(products);

        OrderRepo orderRepo = new OrderRepo();
        List<String> orderProductList = new ArrayList<>();
        orderProductList.add(products.get(0).getId());

        String orderId = orderRepo.add(productRepo, orderProductList);

        assertThat(orderRepo.get(orderId).getProducts()).containsExactly(products.get(0));
    }
    @Test
    void add_invalid_product() {
        ProductRepo productRepo = new ProductRepo();
        var products = createDummyProducts();
        productRepo.fill(products);

        OrderRepo orderRepo = new OrderRepo();
        List<String> orderProductList = new ArrayList<>();
        orderProductList.add("notValidId");

        assertThatExceptionOfType(RuntimeException.class)
                .isThrownBy(()->orderRepo.add(productRepo, orderProductList))
                .withMessage("Product with id 'notValidId' is not available!");
    }

    @Test
    void get() {
        ProductRepo productRepo = new ProductRepo();
        var products = createDummyProducts();
        productRepo.fill(products);

        OrderRepo orderRepo = new OrderRepo();
        String orderId1 = orderRepo.add(productRepo, Arrays.asList(products.get(0).getId(), products.get(1).getId()));
        String orderId2 = orderRepo.add(productRepo, List.of(products.get(0).getId()));

        assertThat(orderRepo.get(orderId1).getProducts()).extracting(Product::getName).contains("Towel", "Pen");
        assertThat(orderRepo.get(orderId2).getProducts()).extracting(Product::getName).contains("Towel");
    }
}