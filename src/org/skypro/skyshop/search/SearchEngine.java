package org.skypro.skyshop.search;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class SearchEngine {
    private final Map<String, Searchable> items = new HashMap<>();

    // Добавление объекта в поисковый движок
    public void add(Searchable item) {
        items.put(item.getName(), item);
    }

    // Поиск с возвратом отсортированной Map
    public Map<String, Searchable> search(String searchTerm) {
        Map<String, Searchable> results = new TreeMap<>();
        for (Map.Entry<String, Searchable> entry : items.entrySet()) {
            if (entry.getKey().toLowerCase().contains(searchTerm.toLowerCase())) {
                results.put(entry.getKey(), entry.getValue());
            }
        }
        return results;
    }

    // Поиск наиболее подходящего объекта
    public Searchable searchBestMatch(String search) throws BestResultNotFound {
        if (search == null || search.isBlank()) {
            throw new BestResultNotFound("Поисковый запрос не может быть пустым или null");
        }

        Searchable bestMatch = null;
        int maxCount = 0;

        for (Searchable item : items.values()) {
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

        if (bestMatch == null) {
            throw new BestResultNotFound("Не найдено ни одного подходящего результата для запроса: " + search);
        }

        return bestMatch;
    }
}

