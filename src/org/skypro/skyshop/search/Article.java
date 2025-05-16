package org.skypro.skyshop.search;

import java.util.Objects;

public class Article implements Searchable {
    private final String name;
    private final String content;

    public Article(String name, String content) {
        this.name = name;
        this.content = content;
    }

    public String getName() {
        return name;
    }

    public String getContent() {
        return content;
    }

    @Override
    public String getSearchTerm() {
        return name;
    }

    @Override
    public String getStringRepresentation() {
        return name + " — ARTICLE";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Article article = (Article) obj;
        return Objects.equals(name, article.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String getContentType() {
        return "ARTICLE";
    }
}
