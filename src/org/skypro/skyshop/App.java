package org.skypro.skyshop;

import org.skypro.skyshop.basket.ProductBasket;
import org.skypro.skyshop.product.DiscountedProduct;
import org.skypro.skyshop.product.FixPriceProduct;
import org.skypro.skyshop.product.SimpleProduct;

public class App {
    public static void main(String[] args) {
        ProductBasket basket = new ProductBasket();

        // Добавляем разные типы товаров
        basket.addProduct(new SimpleProduct("Ноутбук", 100000));
        basket.addProduct(new DiscountedProduct("Смартфон", 50000, 10));
        basket.addProduct(new SimpleProduct("Наушники", 15000));
        basket.addProduct(new FixPriceProduct("Мышь"));
        basket.addProduct(new DiscountedProduct("Клавиатура", 5000, 5));
        basket.addProduct(new FixPriceProduct("Монитор"));

        System.out.println("Содержимое корзины:");
        basket.printBasket();

        System.out.println("\nПоиск 'Наушники': " + basket.containsProduct("Наушники"));
        System.out.println("Поиск 'Монитор': " + basket.containsProduct("Монитор"));

        basket.clearBasket();
        System.out.println("\nПосле очистки:");
        basket.printBasket();
    }
}