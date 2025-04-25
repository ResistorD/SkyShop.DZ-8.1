package org.skypro.skyshop.product;

public class SimpleProduct extends Product {
    private final double price;

    public SimpleProduct(String name, long price) {
        super(name);
        if (price <= 0) {
            throw new IllegalArgumentException("Цена должна быть строго больше 0.");
        }
        this.price = price;
    }

    public long getPrice() {
        return price;
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
