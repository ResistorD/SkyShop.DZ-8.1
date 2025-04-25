package org.skypro.skyshop.product;

import org.skypro.skyshop.search.Searchable;

public abstract class Product implements Searchable {
    protected String name;

    public Product(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Название продукта не может быть пустым или состоять только из пробелов");
        }
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getContentType() {
        return "PRODUCT";
    }

    @Override
    public String getSearchTerm() {
        return name;
    }

    public abstract double getPrice();

    public abstract boolean isSpecial();
}
