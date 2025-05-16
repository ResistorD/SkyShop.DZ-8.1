package org.skypro.skyshop.product;

public class FixPriceProduct extends Product {
    private static final double FIXED_PRICE = 99.99;

    public FixPriceProduct(String name) {
        super(name);
    }

    @Override
    public double getPrice() {
        return FIXED_PRICE;
    }

    @Override
    public boolean isSpecial() {
        return true;
    }

    @Override
    public String toString() {
        return String.format("%s: Фиксированная цена %.2f руб.",
                getName(), FIXED_PRICE);
    }

    @Override
    public String getContentType() {
        return "PRODUCT";
    }
}
