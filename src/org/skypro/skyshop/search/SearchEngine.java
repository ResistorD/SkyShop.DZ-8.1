package org.skypro.skyshop.search;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;
import org.skypro.skyshop.search.SearchableComparator;

public class SearchEngine {
    private final Set<Searchable> items = new HashSet<>();

    // Добавление объекта в поисковый движок (без дубликатов)
    public void add(Searchable item) {
        items.add(item);  // HashSet сам предотвращает дубли
    }

    // Поиск всех подходящих объектов с сортировкой
    public Set<Searchable> search(String searchTerm) {
        Set<Searchable> results = new TreeSet<>(new SearchableComparator());
        for (Searchable item : items) {
            if (item.getSearchTerm().toLowerCase().contains(searchTerm.toLowerCase())) {
                results.add(item);
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

        for (Searchable item : items) {
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
