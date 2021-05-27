package org.helmut.profile.mapping;

import org.helmut.profile.model.ProjectTO;
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
