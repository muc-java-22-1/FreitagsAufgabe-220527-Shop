package com.github.mysterix5;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

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
        ProductRepo productRepo = new ProductRepo();
        var products = createDummyProducts();
        productRepo.fill(products);

        assertThat(productRepo.list()).containsExactlyInAnyOrderElementsOf(products);
    }

    @Test
    void get() {
        ProductRepo productRepo = new ProductRepo();
        var products = createDummyProducts();
        productRepo.fill(products);

        assertThat(productRepo.get(products.get(0).getId())).isEqualTo(products.get(0));
    }
}