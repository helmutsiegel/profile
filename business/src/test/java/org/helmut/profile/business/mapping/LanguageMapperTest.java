package org.helmut.profile.business.mapping;

import org.helmut.profile.common.model.LanguageTO;
import org.helmut.profile.repository.entity.LanguageEntity;
import org.helmut.profile.common.enums.LanguageLevel;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.postgresql.core.Tuple;

import javax.persistence.TupleElement;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class LanguageMapperTest {

    @InjectMocks
    private LanguageMapper languageMapper;

    @Test
    void mapToTO() {
//        LanguageEntity languageEntity = new LanguageEntity();
//        languageEntity.setLanguage("English");
//        languageEntity.setLevel(LanguageLevel.ADVANCED);
//
//        LanguageTO languageTO = languageMapper.mapToTO(languageEntity);
//
//        assertEquals(languageTO.getLanguage(), languageEntity.getLanguage());
//        assertEquals(languageTO.getLevel(), languageEntity.getLevel());


        String host = "/";

        System.out.println(host.split("/")[0]);
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

class Parameter {
    private String key;
    private String value;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}