package com.github.mysterix5;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ShopServiceTest {

    @Test
    void getProduct() {
        // create product repo
        ProductRepo productRepo = new ProductRepo();
        var products = ProductRepo.createDummyProducts();
        productRepo.fill(products);
        // create order repo
        OrderRepo orderRepo = new OrderRepo();
        // create shop service
        ShopService shopService = new ShopService(productRepo, orderRepo);

        // set expected and actual product
        Product expected = products.get(0);
        Product actual = shopService.getProduct(expected.getId());

        // test if original products match listProducts() from shopService
        assertEquals(expected, actual);
    }

    @Test
    void listProducts() {
        // create product repo
        ProductRepo productRepo = new ProductRepo();
        var products = ProductRepo.createDummyProducts();
        productRepo.fill(products);
        // create order repo
        OrderRepo orderRepo = new OrderRepo();
        // create shop service
        ShopService shopService = new ShopService(productRepo, orderRepo);

        // set expected and actual product list
        List<Product> expected = products;
        List<Product> actual = shopService.listProducts();

        // test if original products match listProducts() from shopService
        assertTrue(expected.containsAll(actual));
        assertTrue(actual.containsAll(expected));
    }

    @Test
    void addOrder() {
        // create product repo
        ProductRepo productRepo = new ProductRepo();
        var products = ProductRepo.createDummyProducts();
        productRepo.fill(products);
        // create order repo
        OrderRepo orderRepo = new OrderRepo();
        // create shop service
        ShopService shopService = new ShopService(productRepo, orderRepo);

        // add order
        shopService.addOrder(List.of(products.get(0).getId()));

        // get products from added order
        List<Product> actual = shopService.listOrders().get(0).getProducts();
        // remove product from original product list to match order products
        products.remove(1);

        // test if product lists match
        assertTrue(actual.containsAll(products));
        assertTrue(products.containsAll(actual));
    }

    @Test
    void getOrder() {
        // create product repo
        ProductRepo productRepo = new ProductRepo();
        var products = ProductRepo.createDummyProducts();
        productRepo.fill(products);
        // create order repo
        OrderRepo orderRepo = new OrderRepo();
        // create shop service
        ShopService shopService = new ShopService(productRepo, orderRepo);

        // add order
        shopService.addOrder(List.of(products.get(0).getId()));

        // get order
        String orderId = shopService.listOrders().get(0).getId();
        Order actual = shopService.getOrder(orderId);

        var productsFromGetOrder = actual.getProducts();

        // remove product from original product list to match order products
        products.remove(1);

        // test if product lists match
        assertTrue(productsFromGetOrder.containsAll(products));
        assertTrue(products.containsAll(productsFromGetOrder));
    }

    @Test
    void listOrders() {
        // create product repo
        ProductRepo productRepo = new ProductRepo();
        var products = ProductRepo.createDummyProducts();
        productRepo.fill(products);
        // create order repo
        OrderRepo orderRepo = new OrderRepo();
        // create shop service
        ShopService shopService = new ShopService(productRepo, orderRepo);

        // add order
        shopService.addOrder(List.of(products.get(0).getId()));

        var actual = shopService.listOrders();
        var productsFromGetOrder = actual.get(0).getProducts();

        products.remove(1);

        assertTrue(productsFromGetOrder.containsAll(products));
        assertTrue(products.containsAll(productsFromGetOrder));
    }
}