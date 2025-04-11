package org.skypro.skyshop.product;

public class SimpleProduct extends Product {
    private final double price;

    public SimpleProduct(String name, double price) {
        super(name);
        this.price = price;
    }

    @Override
    public double getPrice() {
        return price;
    }

    @Override
    public boolean isSpecial() {
        return false;
    }

    @Override
    public String toString() {
        return String.format("%s: %.2f руб.", getName(), price);
    }
}
