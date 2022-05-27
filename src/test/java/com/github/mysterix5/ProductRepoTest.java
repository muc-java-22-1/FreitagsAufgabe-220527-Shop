package com.github.mysterix5;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ProductRepoTest {

    public List<Product> createDummyProducts(){
        List<Product> products = new ArrayList<>();

        Product towel = new DigitalProduct("Towel");
        Product pen = new PhysicalProduct("Pen");

        products.add(towel);
        products.add(pen);

        return products;
    }
    @Test
    void list() {
        ProductRepo pr = new ProductRepo();
        var products = createDummyProducts();
        pr.fill(products);

        assertTrue(products.containsAll(pr.list()));
        assertTrue(pr.list().containsAll(products));
    }

    @Test
    void get() {
        ProductRepo pr = new ProductRepo();
        var products = createDummyProducts();
        pr.fill(products);

        assertEquals(products.get(0), pr.get(products.get(0).getId()));
    }
}