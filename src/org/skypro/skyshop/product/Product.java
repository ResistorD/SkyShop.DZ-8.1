package org.skypro.skyshop.product;

import org.skypro.skyshop.search.Searchable;

public abstract class Product implements Searchable {
    private final String name;

    public Product(String name) {
        this.name = name;
    }

    @Override
    public String getSearchTerm() {
        return name; // Поиск по названию товара
    }

    @Override
    public String getContentType() {
        return "PRODUCT"; // Тип контента
    }

    @Override
    public String getName() {
        return name;
    }

    public abstract double getPrice();
    public abstract boolean isSpecial();
}
