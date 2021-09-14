package org.helmut.profile.business.mapping;

import org.helmut.profile.common.model.LanguageTO;
import org.helmut.profile.repository.entity.LanguageEntity;
import org.helmut.profile.common.enums.LanguageLevel;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class LanguageMapperTest {

    @InjectMocks
    private LanguageMapper languageMapper;

    @Test
    void mapToTO() {
        LanguageEntity languageEntity = new LanguageEntity();
        languageEntity.setLanguage("English");
        languageEntity.setLevel(LanguageLevel.ADVANCED);

        LanguageTO languageTO = languageMapper.mapToTO(languageEntity);

        assertEquals(languageTO.getLanguage(), languageEntity.getLanguage());
        assertEquals(languageTO.getLevel(), languageEntity.getLevel());
    }

    @Test
    void mapToEntity() {
        LanguageTO languageTO = new LanguageTO();
        languageTO.setLanguage("German");
        languageTO.setLevel(LanguageLevel.NATIVE);

        LanguageEntity languageEntity = languageMapper.mapToEntity(languageTO);

        assertEquals(languageEntity.getLanguage(), languageTO.getLanguage());
        assertEquals(languageEntity.getLevel(), languageTO.getLevel());
    }

    @Test
    void mapUpdates() {
        LanguageTO languageTO = new LanguageTO();
        languageTO.setLevel(LanguageLevel.NATIVE);

        LanguageEntity languageEntity = new LanguageEntity();
        languageEntity.setLanguage("German");
        languageEntity.setLevel(LanguageLevel.ADVANCED);

        languageMapper.mapUpdates(languageEntity, languageTO);

        assertEquals(languageEntity.getLevel(), LanguageLevel.NATIVE);
        assertEquals(languageEntity.getLanguage(), "German");
    }
}