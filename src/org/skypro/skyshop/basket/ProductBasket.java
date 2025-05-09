package org.skypro.skyshop.basket;

import org.skypro.skyshop.product.Product;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ProductBasket {
    private final List<Product> products = new ArrayList<>();

    public void addProduct(Product product) {
        products.add(product);
    }

    public void clearBasket() {
        products.clear();
    }

    public double getTotalPrice() {
        return products.stream()
                .mapToDouble(Product::getPrice)
                .sum();
    }

    public boolean containsProduct(String productName) {
        return products.stream()
                .anyMatch(p -> p.getName().equals(productName));
    }

    public void printBasket() {
        products.forEach(System.out::println);

        System.out.printf("Итого: %.2f руб.%n", getTotalPrice());

        long specialCount = products.stream()
                .filter(Product::isSpecial)
                .count();
        System.out.printf("Специальных товаров: %d%n", specialCount);
    }

    public List<Product> removeProductByName(String name) {
        List<Product> removed = new ArrayList<>();
        Iterator<Product> iterator = products.iterator();

        while (iterator.hasNext()) {
            Product product = iterator.next();
            if (product.getName().equals(name)) {
                removed.add(product);
                iterator.remove();
            }
        }

        return removed;
    }
}
