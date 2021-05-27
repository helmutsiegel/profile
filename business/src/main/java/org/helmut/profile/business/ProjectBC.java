package org.helmut.profile.business;

import org.helmut.profile.mapping.ProjectMapper;
import org.helmut.profile.model.ProjectTO;
import org.helmut.profile.repository.ProjectRepository;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;

@RequestScoped
public class ProjectBC {

    @Inject
    private ProjectRepository projectRepository;

    @Inject
    private ProjectMapper projectMapper;

    public List<ProjectTO> getByUsername(String username) {
        return projectRepository.findByProperty("userEntity.username", username)
                .stream().map(projectMapper::mapToProjectTO).collect(Collectors.toList());
    }
}
