package org.helmut.profile.repository.entity;

import org.helmut.profile.repository.enums.LanguageLevel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "T_LANGUAGE")
public class LanguageEntity extends BaseEntity {

    @Column
    private String language;

    @Column
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
