package org.helmut.profile.business.bc;

import org.helmut.profile.business.mapping.ProjectMapper;
import org.helmut.profile.business.model.ProjectTO;
import org.helmut.profile.repository.ProjectRepository;
import org.helmut.profile.repository.UserRepository;
import org.helmut.profile.repository.entity.ProjectEntity;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;

@RequestScoped
public class ProjectBC {

    @Inject
    private ProjectRepository projectRepository;

    @Inject
    private UserRepository userRepository;

    @Inject
    private ProjectMapper projectMapper;

    public List<ProjectTO> getByEmail(String email) {
        return projectRepository.findByProperty("userEntity.email", email)
                .stream().map(projectMapper::mapToProjectTO).collect(Collectors.toList());
    }

    public ProjectTO getByName(String name) {
        return projectMapper.mapToProjectTO(projectRepository.findByUniqueProperty("name", name));
    }

    public void createProject(ProjectTO projectTO) {
        ProjectEntity projectEntity = new ProjectEntity();
        projectEntity.setName(projectTO.getName());
        projectEntity.setUserEntity(userRepository.findByUniqueProperty("email", projectTO.getUserTO().getEmail()));
        projectRepository.persist(projectEntity);
    }
}
