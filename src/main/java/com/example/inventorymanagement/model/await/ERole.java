package com.example.inventorymanagement.model.await;

public enum ERole {
    ROLE_ADMIN("ADMIN"), ROLE_STAFF("STAFF");

    public final String name;

    ERole(String name) {
        this.name = name;
    }
}
