package org.skypro.skyshop.basket;

import org.skypro.skyshop.product.Product;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductBasket {
    private final Map<String, List<Product>> products = new HashMap<>();

    // Добавление продукта в корзину
    public void addProduct(Product product) {
        products.computeIfAbsent(product.getName(), k -> new ArrayList<>()).add(product);
    }

    // Очистка корзины
    public void clearBasket() {
        products.clear();
    }

    // Получение общей стоимости всех товаров
    public double getTotalPrice() {
        return products.values().stream()
                .flatMap(List::stream)
                .mapToDouble(Product::getPrice)
                .sum();
    }

    // Проверка наличия продукта по имени
    public boolean containsProduct(String productName) {
        return products.containsKey(productName);
    }

    // Печать содержимого корзины
    public void printBasket() {
        if (products.isEmpty()) {
            System.out.println("Корзина пуста");
            return;
        }

        products.forEach((name, productList) -> productList.forEach(product ->
                System.out.println(product.getStringRepresentation())));

        System.out.printf("Итого: %.2f руб.%n", getTotalPrice());

        long specialCount = products.values().stream()
                .flatMap(List::stream)
                .filter(Product::isSpecial)
                .count();
        System.out.printf("Специальных товаров: %d%n", specialCount);
    }

    // Удаление всех продуктов с заданным именем
    public List<Product> removeProductByName(String name) {
        return products.remove(name);
    }
}