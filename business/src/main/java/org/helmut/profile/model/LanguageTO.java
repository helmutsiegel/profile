package org.helmut.profile.model;

import org.helmut.profile.repository.enums.LanguageLevel;

public class LanguageTO {
    private String language;
    private LanguageLevel level;

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public LanguageLevel getLevel() {
        return level;
    }

    public void setLevel(LanguageLevel level) {
        this.level = level;
    }
}
