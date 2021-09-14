package org.helmut.profile.business.mapping;

import org.helmut.profile.common.model.ExperienceTO;
import org.helmut.profile.repository.entity.ExperienceEntity;

import javax.enterprise.context.RequestScoped;

@RequestScoped
public class ExperienceMapper {

    public ExperienceTO mapToTO(ExperienceEntity entity) {
        ExperienceTO experienceTO = new ExperienceTO();
        experienceTO.setId(entity.getId());
        experienceTO.setCompany(entity.getCompany());
        experienceTO.setStartDate(entity.getStartDate());
        experienceTO.setEndDate(entity.getEndDate());
        experienceTO.setPosition(entity.getPosition());
        return experienceTO;
    }

    public ExperienceEntity mapToEntity(ExperienceTO experienceTO) {
        ExperienceEntity experienceEntity = new ExperienceEntity();
        experienceEntity.setCompany(experienceTO.getCompany());
        experienceEntity.setPosition(experienceTO.getPosition());
        experienceEntity.setStartDate(experienceTO.getStartDate());
        experienceEntity.setEndDate(experienceTO.getEndDate());
        return experienceEntity;
    }

    public void mapUpdates(ExperienceEntity experienceEntity, ExperienceTO experienceTO) {
        experienceEntity.setCompany(experienceTO.getCompany());
        experienceEntity.setStartDate(experienceTO.getStartDate());
        experienceEntity.setEndDate(experienceTO.getEndDate());
        experienceEntity.setPosition(experienceTO.getPosition());
    }
}
