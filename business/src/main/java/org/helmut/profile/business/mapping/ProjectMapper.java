package org.helmut.profile.business.mapping;

import org.helmut.profile.business.model.ProjectTO;
import org.helmut.profile.repository.entity.ProjectEntity;

import javax.enterprise.context.RequestScoped;

@RequestScoped
public class ProjectMapper {

    public ProjectTO mapToProjectTO(ProjectEntity entity){
        ProjectTO projectTO = new ProjectTO();
        projectTO.setName(entity.getName());
        return projectTO;
    }
}
