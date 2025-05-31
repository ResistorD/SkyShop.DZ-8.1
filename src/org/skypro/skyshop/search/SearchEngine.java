package org.skypro.skyshop.search;

import java.util.Set;
import java.util.HashSet;
import java.util.TreeSet;
import java.util.Comparator;
import java.util.stream.Collectors;

public class SearchEngine {
    private final Set<Searchable> items = new HashSet<>();

    public void add(Searchable item) {
        items.add(item);
    }

    public Set<Searchable> search(String searchTerm) {
        return items.stream()
                .filter(item -> item.getSearchTerm().toLowerCase().contains(searchTerm.toLowerCase()))
                .collect(Collectors.toCollection(() -> new TreeSet<>(new SearchableComparator())));
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
