package org.skypro.skyshop.basket;

import org.skypro.skyshop.product.Product;

public class ProductBasket {
    private static final int CAPACITY = 5;
    private final Product[] products;
    private int count;

    public ProductBasket() {
        this.products = new Product[CAPACITY];
        this.count = 0;
    }

    public void addProduct(Product product) {
        if (count < CAPACITY) {
            products[count] = product;
            count++;
        } else {
            System.out.println("\nНевозможно добавить продукт - корзина заполнена");
        }
    }

    public int getTotalPrice() {
        int total = 0;
        for (int i = 0; i < count; i++) {
            total += products[i].getPrice();
        }
        return total;
    }

    public void printBasket() {
        if (count == 0) {
            System.out.println("В корзине пусто");
            return;
        }

        for (int i = 0; i < count; i++) {
            System.out.println(products[i].getName() + ": " + products[i].getPrice() + " руб.");
        }
        System.out.println("Итого: " + getTotalPrice() + " руб.");
    }

    public boolean containsProduct(String productName) {
        for (int i = 0; i < count; i++) {
            if (products[i].getName().equals(productName)) {
                return true;
            }
        }
        return false;
    }

    public void clearBasket() {
        for (int i = 0; i < count; i++) {
            products[i] = null;
        }
        count = 0;
    }
}
