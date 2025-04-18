package org.skypro.skyshop;

import org.skypro.skyshop.basket.ProductBasket;
import org.skypro.skyshop.product.DiscountedProduct;
import org.skypro.skyshop.product.FixPriceProduct;
import org.skypro.skyshop.product.SimpleProduct;
import org.skypro.skyshop.search.Article;
import org.skypro.skyshop.search.SearchEngine;
import org.skypro.skyshop.search.Searchable;

import java.util.Arrays;

public class App {
    public static void main(String[] args) {
        ProductBasket basket = new ProductBasket();

        // Товары
        SimpleProduct laptop = new SimpleProduct("Ноутбук", 100000);
        DiscountedProduct smartphone = new DiscountedProduct("Смартфон", 50000, 10);
        SimpleProduct headphones = new SimpleProduct("Наушники", 15000);
        FixPriceProduct mouse = new FixPriceProduct("Мышь");
        DiscountedProduct keyboard = new DiscountedProduct("Клавиатура", 5000, 5);
        FixPriceProduct monitor = new FixPriceProduct("Монитор");

        basket.addProduct(laptop);
        basket.addProduct(smartphone);
        basket.addProduct(headphones);
        basket.addProduct(mouse);
        basket.addProduct(keyboard);
        basket.addProduct(monitor);

        System.out.println("Содержимое корзины:");
        basket.printBasket();

        System.out.println("\nПоиск 'Наушники': " + basket.containsProduct("Наушники"));
        System.out.println("Поиск 'Монитор': " + basket.containsProduct("Монитор"));

        basket.clearBasket();
        System.out.println("\nПосле очистки:");
        basket.printBasket();

        // -----------------------------------------------
        // Поисковый движок
        SearchEngine engine = new SearchEngine(20); // запас по размеру

        // Добавляем продукты
        engine.add(laptop);
        engine.add(smartphone);
        engine.add(headphones);
        engine.add(mouse);
        engine.add(keyboard);
        engine.add(monitor);

        // Добавляем статьи
        engine.add(new Article("Обзор ноутбуков", "Как выбрать ноутбук для работы и игр"));
        engine.add(new Article("Смартфоны 2025", "Лучшие модели смартфонов этого года"));
        engine.add(new Article("Звук и стиль", "Почему наушники стали модным аксессуаром"));
        engine.add(new Article("Мыши для геймеров", "Как выбрать мышь с точным сенсором"));
        engine.add(new Article("Клавиатуры и комфорт", "Что важно знать при выборе клавиатуры"));
        engine.add(new Article("Мониторы 4K", "Зачем нужен 4K-монитор в 2025 году"));

        // Примеры поиска
        System.out.println("\n=== Результаты поиска по запросу 'мышь' ===");
        printResults(engine.search("мышь"));

        System.out.println("\n=== Результаты поиска по запросу 'лучшие' ===");
        printResults(engine.search("лучшие"));

        System.out.println("\n=== Результаты поиска по запросу 'ноутбук' ===");
        printResults(engine.search("ноутбук"));

        System.out.println("\n=== Результаты поиска по запросу 'не существует' ===");
        printResults(engine.search("не существует"));
    }

    private static void printResults(Searchable[] results) {
        Arrays.stream(results)
                .filter(r -> r != null)
                .forEach(r -> System.out.println(r.getStringRepresentation()));
    }
}