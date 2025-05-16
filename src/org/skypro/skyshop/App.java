package org.skypro.skyshop;

import org.skypro.skyshop.basket.ProductBasket;
import org.skypro.skyshop.product.DiscountedProduct;
import org.skypro.skyshop.product.FixPriceProduct;
import org.skypro.skyshop.product.Product;
import org.skypro.skyshop.product.SimpleProduct;
import org.skypro.skyshop.search.Article;
import org.skypro.skyshop.search.BestResultNotFound;
import org.skypro.skyshop.search.SearchEngine;
import org.skypro.skyshop.search.Searchable;
import java.util.Set;
import java.util.List;


public class App {
    public static void main(String[] args) {
        ProductBasket basket = new ProductBasket();

        // Добавляем продукты в корзину
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

        System.out.println("\nСодержимое корзины:");
        basket.printBasket();

        // Удаление продуктов
        System.out.println("\n=== Удаление 'Клавиатура' из корзины ===");
        List<Product> removed = basket.removeProductByName("Клавиатура");
        if (removed.isEmpty()) {
            System.out.println("Список пуст");
        } else {
            System.out.println("Удалены следующие продукты:");
            removed.forEach(System.out::println);
        }

        System.out.println("\nСодержимое корзины после удаления:");
        basket.printBasket();

        System.out.println("\n=== Удаление 'Пылесос' (несуществующего) ===");
        List<Product> notFound = basket.removeProductByName("Пылесос");

        if (notFound == null || notFound.isEmpty()) {  // Проверяем на null и пустоту
            System.out.println("Список пуст");
        } else {
            System.out.println("Удалены:");
            notFound.forEach(System.out::println);
        }


        System.out.println("\nКорзина после второй попытки удаления:");
        basket.printBasket();

        // -----------------------------------------------
        // Поисковый движок
        SearchEngine engine = new SearchEngine();

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

        // Поиск по запросам
        System.out.println("\n=== Результаты поиска по запросу 'мышь' ===");
        printResults(engine.search("мышь"));

        System.out.println("\n=== Результаты поиска по запросу 'ноутбук' ===");
        printResults(engine.search("ноутбук"));

        System.out.println("\n=== Поиск наиболее подходящего ===");
        try {
            Searchable best = engine.searchBestMatch("мышь");
            System.out.println("Лучшее совпадение: " + best.getStringRepresentation());
        } catch (BestResultNotFound e) {
            System.out.println("Ошибка: " + e.getMessage());
        }

        System.out.println("\n=== Поиск наиболее подходящего — с ошибкой ===");
        try {
            Searchable best = engine.searchBestMatch("qwerty");
            System.out.println("Лучшее совпадение: " + best.getStringRepresentation());
        } catch (BestResultNotFound e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
    }

    // Вывод результатов поиска
    private static void printResults(Set<Searchable> results) {
        if (results.isEmpty()) {
            System.out.println("Ничего не найдено.");
        } else {
            results.forEach(r -> System.out.println(r.getStringRepresentation()));
        }
    }

}
