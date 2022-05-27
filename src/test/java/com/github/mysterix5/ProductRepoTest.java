package com.github.mysterix5;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProductRepoTest {

    @Test
    void list() {
        ProductRepo pr = new ProductRepo();
        var products = ProductRepo.createDummyProducts();
        pr.fill(products);

        assertTrue(products.containsAll(pr.list()));
        assertTrue(pr.list().containsAll(products));
    }

    @Test
    void get() {
        ProductRepo pr = new ProductRepo();
        var products = ProductRepo.createDummyProducts();
        pr.fill(products);

        assertEquals(products.get(0), pr.get(products.get(0).getId()));
    }
}