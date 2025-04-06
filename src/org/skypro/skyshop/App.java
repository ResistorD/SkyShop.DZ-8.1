package org.skypro.skyshop;

import org.skypro.skyshop.product.Product;
import org.skypro.skyshop.basket.ProductBasket;

public class App {
    public static void main(String[] args) {
        Product product1 = new Product("Ноутбук", 100000);
        Product product2 = new Product("Смартфон", 50000);
        Product product3 = new Product("Наушники", 15000);
        Product product4 = new Product("Мышь", 3000);
        Product product5 = new Product("Клавиатура", 5000);
        Product product6 = new Product("Монитор", 40000);

        ProductBasket basket = new ProductBasket();

        basket.addProduct(product1);
        basket.addProduct(product2);
        basket.addProduct(product3);
        basket.addProduct(product4);
        basket.addProduct(product5);

        basket.addProduct(product6);

        System.out.println("\nСодержимое корзины:");
        basket.printBasket();

        System.out.println("\nОбщая стоимость: " + basket.getTotalPrice() + " руб.");

        System.out.println("\nПоиск 'Наушники': " + basket.containsProduct("Наушники"));

        System.out.println("Поиск 'Монитор': " + basket.containsProduct("Монитор"));

        basket.clearBasket();

        System.out.println("\nПосле очистки:");
        basket.printBasket();

        System.out.println("\nОбщая стоимость пустой корзины: " + basket.getTotalPrice() + " руб.");

        System.out.println("\nПоиск 'Ноутбук' в пустой корзине: " + basket.containsProduct("Ноутбук"));
    }
}