package org.helmut.profile.business.mapping;

import org.helmut.profile.common.model.LanguageTO;
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

    public LanguageEntity mapToEntity(LanguageTO languageTO) {
        LanguageEntity languageEntity = new LanguageEntity();
        languageEntity.setLanguage(languageTO.getLanguage());
        languageEntity.setLevel(languageTO.getLevel());
        return languageEntity;
    }

    public void mapUpdates(LanguageEntity languageEntity, LanguageTO languageTO) {
        languageEntity.setLevel(languageTO.getLevel());
    }
}
