package org.helmut.profile.mapping;

import org.helmut.profile.model.ExperienceTO;
import org.helmut.profile.repository.entity.ExperienceEntity;

import javax.enterprise.context.RequestScoped;

@RequestScoped
public class ExperienceMapper {

    public ExperienceTO mapExperienceTO(ExperienceEntity entity) {
        ExperienceTO experienceTO = new ExperienceTO();
        experienceTO.setCompany(entity.getCompany());
        experienceTO.setStartDate(entity.getStartDate());
        experienceTO.setEndDate(entity.getEndDate());
        return experienceTO;
    }
}