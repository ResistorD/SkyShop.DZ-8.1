package org.skypro.skyshop.product;

public class DiscountedProduct extends Product {
    private final double basePrice;
    private final int discountPercent;

    public DiscountedProduct(String name, double basePrice, int discountPercent) {
        super(name);
        this.basePrice = basePrice;
        this.discountPercent = discountPercent;
    }

    @Override
    public double getPrice() {
        return basePrice * (100 - discountPercent) / 100;
    }

    @Override
    public boolean isSpecial() {
        return true;
    }

    @Override
    public String toString() {
        return String.format("%s: %.2f руб. (скидка %d%%)",
                getName(), getPrice(), discountPercent);
    }
}
