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

    public Searchable searchBestMatch(String search) throws BestResultNotFound {
        if (search == null || search.isBlank()) {
            throw new BestResultNotFound("Поисковый запрос не может быть пустым или null");
        }

        Searchable bestMatch = null;
        int maxCount = 0;

        for (Searchable item : items) {
            if (item != null) {
                String term = item.getSearchTerm().toLowerCase();
                String searchLower = search.toLowerCase();
                int count = 0;
                int index = 0;

                while ((index = term.indexOf(searchLower, index)) != -1) {
                    count++;
                    index += searchLower.length();
                }

                if (count > maxCount) {
                    maxCount = count;
                    bestMatch = item;
                }
            }
        }

        if (bestMatch == null) {
            throw new BestResultNotFound("Не найдено ни одного подходящего результата для запроса: " + search);
        }

        return bestMatch;
    }

}