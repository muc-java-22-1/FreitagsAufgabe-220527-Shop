package com.github.mysterix5;

import java.util.UUID;

public abstract class BaseProduct {
    private final String id;
    private final String name;

    public BaseProduct(String name){
        id = UUID.randomUUID().toString();
        this.name = name;
    }
    public BaseProduct(String id, String name){
        // TODO custom ids must be unique-checked
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
