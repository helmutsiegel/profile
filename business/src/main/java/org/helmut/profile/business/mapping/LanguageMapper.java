package org.helmut.profile.business.mapping;

import org.helmut.profile.business.model.LanguageTO;
import org.helmut.profile.repository.entity.LanguageEntity;

import javax.enterprise.context.RequestScoped;

@RequestScoped
public class LanguageMapper {
    public LanguageTO mapToTO(LanguageEntity entity) {
        LanguageTO languageTO = new LanguageTO();
        languageTO.setLanguage(entity.getLanguage());
        languageTO.setLevel(entity.getLevel());
        return languageTO;
    }
}