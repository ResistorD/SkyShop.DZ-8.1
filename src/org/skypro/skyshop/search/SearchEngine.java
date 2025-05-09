package org.skypro.skyshop.search;

import java.util.ArrayList;
import java.util.List;

public class SearchEngine {
    private final List<Searchable> items = new ArrayList<>();

    public void add(Searchable item) {
        items.add(item);
    }

    public List<Searchable> search(String searchTerm) {
        List<Searchable> results = new ArrayList<>();
        for (Searchable item : items) {
            if (item.getSearchTerm().toLowerCase().contains(searchTerm.toLowerCase())) {
                results.add(item);
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
