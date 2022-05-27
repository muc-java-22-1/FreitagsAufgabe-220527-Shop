package com.github.mysterix5;

import java.util.UUID;

public class Product {
    private final String id;
    private String name;

    public Product(String name){
        id = UUID.randomUUID().toString();
        this.name = name;
    }
    public Product(String id, String name){
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

}
