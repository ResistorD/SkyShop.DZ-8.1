package org.skypro.skyshop.search;

import java.util.Arrays;

public class SearchEngine {
    private Searchable[] items;
    private int size = 0;

    public SearchEngine(int capacity) {
        this.items = new Searchable[capacity];
    }

    public void add(Searchable item) {
        if (size >= items.length) {
            // Автоматическое расширение массива, если нужно
            items = Arrays.copyOf(items, items.length * 2);
        }
        items[size++] = item;
    }

    public Searchable[] search(String searchTerm) {
        Searchable[] results = new Searchable[5];
        int count = 0;
        for (int i = 0; i < size; i++) {
            if (items[i].getSearchTerm().toLowerCase().contains(searchTerm.toLowerCase())) {
                results[count++] = items[i];
                if (count == 5) break;
            }
        }
        return results;
    }
}