package org.helmut.profile.business.bc;

import org.helmut.profile.business.mapping.ProjectMapper;
import org.helmut.profile.business.model.CreateChapterTO;
import org.helmut.profile.business.model.ProjectTO;
import org.helmut.profile.business.model.SectionTO;
import org.helmut.profile.repository.ProjectRepository;
import org.helmut.profile.repository.SectionRepository;
import org.helmut.profile.repository.UserRepository;
import org.helmut.profile.repository.entity.ProjectEntity;
import org.helmut.profile.repository.entity.SectionEntity;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.event.Event;
import javax.enterprise.event.Observes;
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
    private SectionRepository sectionRepository;

    @Inject
    private ProjectMapper projectMapper;

    //We can also use qualifiers for events to
    @Inject
    private Event<ProjectTO> projectTOEvent;

    public List<ProjectTO> getByEmail(String email) {
        return projectRepository.findByProperty("userEntity.email", email)
                .stream().map(projectMapper::mapToProjectTO).collect(Collectors.toList());
    }

    public ProjectTO getByName(String name) {
        return projectMapper.mapToProjectTO(projectRepository.findByUniqueProperty("name", name));
    }

    public void createProject(ProjectTO projectTO) {
        projectTOEvent.fire(projectTO);
        ProjectEntity projectEntity = new ProjectEntity();
        projectEntity.setName(projectTO.getName());
        projectEntity.setDescription(projectTO.getDescription());
        projectEntity.setUserEntity(userRepository.findByUniqueProperty("email", projectTO.getUserTO().getEmail()));
        projectRepository.persist(projectEntity);
    }

    public void createChapter(CreateChapterTO createChapterTO, String email) {
        ProjectEntity projectEntity = projectRepository.findByUniqueProperty("name", createChapterTO.getProjectName());
        if (!projectEntity.getUserEntity().getEmail().equals(email)) {
            throw new IllegalArgumentException("You don't have the permission to create chapter for this project");
        }
        projectEntity.addChapter(projectMapper.mapCreateChapterTO(createChapterTO));
        this.projectRepository.update(projectEntity);
    }

    private void observerProjectTO(@Observes ProjectTO projectTO) {
        System.out.println("Event caught: " + projectTO);
    }

    public void updateSection(SectionTO sectionTO, String currentUserEmail) {
        SectionEntity sectionEntity = sectionRepository.findById(sectionTO.getId());
        if (!currentUserEmail.equals(sectionEntity.getChapter().getProject().getUserEntity().getEmail())) {
            throw new IllegalArgumentException("You don't have the permission to create chapter for this project");
        }
        sectionEntity.setDescription(sectionTO.getDescription());
        sectionEntity.setTitle(sectionTO.getTitle());
        sectionRepository.update(sectionEntity);
    }
}
