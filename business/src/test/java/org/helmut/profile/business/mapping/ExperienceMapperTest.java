package org.helmut.profile.business.mapping;

import org.helmut.profile.business.model.ExperienceTO;
import org.helmut.profile.repository.entity.ExperienceEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class ExperienceMapperTest {

    @InjectMocks
    private ExperienceMapper experienceMapper;

    @Test
    void mapToTO() {
        ExperienceEntity experienceEntity = new ExperienceEntity();
        experienceEntity.setId(1L);
        experienceEntity.setCompany("Company");
        experienceEntity.setStartDate(LocalDate.now());
        experienceEntity.setEndDate(LocalDate.MIN);
        experienceEntity.setPosition("Position");

        ExperienceTO experienceTO = experienceMapper.mapToTO(experienceEntity);

        assertEquals(experienceTO.getId(), experienceEntity.getId());
        assertEquals(experienceTO.getCompany(), experienceEntity.getCompany());
        assertEquals(experienceTO.getStartDate(), experienceEntity.getStartDate());
        assertEquals(experienceTO.getEndDate(), experienceEntity.getEndDate());
        assertEquals(experienceTO.getPosition(), experienceEntity.getPosition());
    }

    @Test
    void mapToEntity() {
        ExperienceTO experienceTO = new ExperienceTO();
        experienceTO.setCompany("Company");
        experienceTO.setStartDate(LocalDate.MAX);
        experienceTO.setEndDate(LocalDate.now());
        experienceTO.setPosition("Position");

        ExperienceEntity experienceEntity = experienceMapper.mapToEntity(experienceTO);

        assertEquals(experienceEntity.getCompany(), experienceTO.getCompany());
        assertEquals(experienceEntity.getStartDate(), experienceTO.getStartDate());
        assertEquals(experienceEntity.getEndDate(), experienceTO.getEndDate());
        assertEquals(experienceEntity.getPosition(), experienceTO.getPosition());
    }

    @Test
    void mapUpdates() {
        ExperienceTO experienceTO = new ExperienceTO();
        experienceTO.setCompany("Old company");
        experienceTO.setStartDate(LocalDate.MAX);
        experienceTO.setEndDate(LocalDate.now());
        experienceTO.setPosition("Old position");

        ExperienceEntity experienceEntity = new ExperienceEntity();
        experienceEntity.setId(1L);
        experienceEntity.setCompany("Company");
        experienceEntity.setStartDate(LocalDate.now());
        experienceEntity.setEndDate(LocalDate.MIN);
        experienceEntity.setPosition("Position");

        experienceMapper.mapUpdates(experienceEntity, experienceTO);

        assertEquals(experienceEntity.getCompany(), experienceTO.getCompany());
        assertEquals(experienceEntity.getStartDate(), experienceTO.getStartDate());
        assertEquals(experienceEntity.getEndDate(), experienceTO.getEndDate());
        assertEquals(experienceEntity.getPosition(), experienceTO.getPosition());
    }
}