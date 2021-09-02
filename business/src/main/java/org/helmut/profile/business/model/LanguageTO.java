package org.helmut.profile.business.model;

import org.helmut.profile.repository.enums.LanguageLevel;

public class LanguageTO {
    private String language;
    private LanguageLevel level;

    public LanguageTO() {
    }

    public LanguageTO(String language, LanguageLevel level) {
        this.language = language;
        this.level = level;
    }

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
