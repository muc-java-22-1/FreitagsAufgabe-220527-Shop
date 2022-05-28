package com.github.mysterix5;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

class ShopServiceTest {

    public List<Product> createDummyProducts(){
        List<Product> products = new ArrayList<>();

        Product towel = new DigitalProduct("Towel");
        Product pen = new PhysicalProduct("Pen");

        products.add(towel);
        products.add(pen);

        return products;
    }
    @Test
    void getProduct() {
        // create product repo
        ProductRepo productRepo = new ProductRepo();
        var products = createDummyProducts();
        productRepo.fill(products);
        // create order repo
        OrderRepo orderRepo = new OrderRepo();
        // create shop service
        ShopService shopService = new ShopService(productRepo, orderRepo);

        // set expected and actual product
        Product expected = products.get(0);
        Product actual = shopService.getProduct(expected.getId());

        // test if original product matches getProduct(id) from shopService
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void listProducts() {
        // create product repo
        ProductRepo productRepo = new ProductRepo();
        var products = createDummyProducts();
        productRepo.fill(products);
        // create order repo
        OrderRepo orderRepo = new OrderRepo();
        // create shop service
        ShopService shopService = new ShopService(productRepo, orderRepo);

        // set expected and actual product list
        List<Product> actual = shopService.listProducts();

        // test if original products match listProducts() from shopService
        assertThat(actual).containsExactlyInAnyOrderElementsOf(products);
    }

    @Test
    void addOrder() {
        // create product repo
        ProductRepo productRepo = new ProductRepo();
        var products = createDummyProducts();
        productRepo.fill(products);
        // create order repo
        OrderRepo orderRepo = new OrderRepo();
        // create shop service
        ShopService shopService = new ShopService(productRepo, orderRepo);

        // add order
        String orderId = shopService.addOrder(List.of(products.get(0).getId()));

        // test if product lists match
        assertThat(shopService.listOrders().get(0).getProducts()).containsExactly(products.get(0));
    }

    @Test
    void getOrder() {
        // create product repo
        ProductRepo productRepo = new ProductRepo();
        var products = createDummyProducts();
        productRepo.fill(products);
        // create order repo
        OrderRepo orderRepo = new OrderRepo();
        // create shop service
        ShopService shopService = new ShopService(productRepo, orderRepo);

        // add order
        String orderId = shopService.addOrder(List.of(products.get(0).getId()));

        // get order
        Order actual = shopService.getOrder(orderId);

        assertThat(shopService.getOrder(orderId).getProducts()).containsExactly(products.get(0));
    }

    @Test
    void listOrders() {
        // create product repo
        ProductRepo productRepo = new ProductRepo();
        var products = createDummyProducts();
        productRepo.fill(products);
        // create order repo
        OrderRepo orderRepo = new OrderRepo();
        // create shop service
        ShopService shopService = new ShopService(productRepo, orderRepo);

        // add order
        String orderId1 = shopService.addOrder(List.of(products.get(0).getId()));
        String orderId2 = shopService.addOrder(List.of(products.get(0).getId(), products.get(1).getId()));

        assertThat(shopService.listOrders())
                .extracting(Order::getProducts)
                .contains(products, List.of(products.get(0)));
    }

}