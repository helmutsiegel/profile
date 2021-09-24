package org.helmut.profile.business.bc;

import org.helmut.profile.business.mapping.ProjectMapper;
import org.helmut.profile.common.model.CreateChapterTO;
import org.helmut.profile.common.model.ProjectTO;
import org.helmut.profile.common.model.SectionTO;
import org.helmut.profile.common.model.UserTO;
import org.helmut.profile.repository.ProjectRepository;
import org.helmut.profile.repository.SectionRepository;
import org.helmut.profile.repository.UserRepository;
import org.helmut.profile.repository.entity.ChapterEntity;
import org.helmut.profile.repository.entity.ProjectEntity;
import org.helmut.profile.repository.entity.SectionEntity;
import org.helmut.profile.repository.entity.UserEntity;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.enterprise.event.Event;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProjectBCTest {

    @InjectMocks
    private ProjectBC projectBC;

    @Mock
    private ProjectRepository projectRepository;

    @Mock
    private ProjectMapper projectMapper;

    @Mock
    private Event<ProjectTO> projectTOEvent;

    @Mock
    private UserRepository userRepository;

    @Mock
    private SectionRepository sectionRepository;

    @Test
    void getByEmail() {
        String email = "mail@mail.com";
        ProjectEntity projectEntity = new ProjectEntity();
        doReturn(Collections.singletonList(projectEntity)).when(projectRepository).findByProperty("userEntity.email", email);
        ProjectTO projectTO = new ProjectTO();
        doReturn(projectTO).when(projectMapper).mapToProjectTO(projectEntity);

        List<ProjectTO> projectsByEmail = projectBC.getByEmail(email);

        assertEquals(projectsByEmail.size(), 1);
        assertSame(projectsByEmail.get(0), projectTO);
        verify(projectRepository, times(1)).findByProperty("userEntity.email", email);
        verify(projectMapper, times(1)).mapToProjectTO(projectEntity);
    }

    @Test
    void getByName() {
        String projectName = "projectName";
        ProjectEntity projectEntity = new ProjectEntity();
        doReturn(projectEntity).when(projectRepository).findByUniqueProperty("name", projectName);
        ProjectTO mappedProjectTO = new ProjectTO();
        doReturn(mappedProjectTO).when(projectMapper).mapToProjectTO(projectEntity);

        ProjectTO projectTO = projectBC.getByName(projectName);

        assertSame(projectTO, mappedProjectTO);
        verify(projectRepository, times(1)).findByUniqueProperty("name", projectName);
        verify(projectMapper, times(1)).mapToProjectTO(projectEntity);
    }

    @Test
    void createProject() {
        ProjectTO projectTO = new ProjectTO();
        UserTO userTO = new UserTO.Builder().build();
        userTO.setEmail("email@mail.com");
        projectTO.setUserTO(userTO);

        projectBC.createProject(projectTO);

        verify(projectTOEvent, times(1)).fire(projectTO);
        verify(userRepository, times(1)).getByEmail(projectTO.getUserTO().getEmail());
        verify(projectRepository, times(1)).persist(any(ProjectEntity.class));
    }

    @Nested
    @DisplayName("Create chapter test")
    class CreateChapterTest {

        @Test
        @DisplayName("Create successful")
        void createChapterSuccessful() {
            CreateChapterTO createChapterTO = new CreateChapterTO();
            createChapterTO.setProjectName("ProjectName");
            ProjectEntity projectEntity = new ProjectEntity();
            UserEntity userEntity = new UserEntity();
            userEntity.setEmail("mail@mail.com");
            projectEntity.setUserEntity(userEntity);
            doReturn(projectEntity).when(projectRepository).findByUniqueProperty("name", createChapterTO.getProjectName());
            ChapterEntity chapterEntity = new ChapterEntity();
            doReturn(chapterEntity).when(projectMapper).mapCreateChapterTO(createChapterTO);

            projectBC.createChapter(createChapterTO, "mail@mail.com");

            verify(projectRepository, times(1)).findByUniqueProperty("name", createChapterTO.getProjectName());
            verify(projectMapper, times(1)).mapCreateChapterTO(createChapterTO);
            verify(projectRepository, times(1)).update(projectEntity);
            assertSame(chapterEntity.getProject(), projectEntity);
            assertSame(projectEntity.getChapters().get(0), chapterEntity);
        }

        @Test
        @DisplayName("Create successful")
        void createChapterFailed() {
            CreateChapterTO createChapterTO = new CreateChapterTO();
            createChapterTO.setProjectName("ProjectName");
            ProjectEntity projectEntity = new ProjectEntity();
            UserEntity userEntity = new UserEntity();
            userEntity.setEmail("different_email@mail.com");
            projectEntity.setUserEntity(userEntity);

            doReturn(projectEntity).when(projectRepository).findByUniqueProperty("name", createChapterTO.getProjectName());

            assertThrows(IllegalArgumentException.class, () -> projectBC.createChapter(createChapterTO, "mail@mail.com"));
        }
    }

    @Nested
    @DisplayName("Update section")
    class UpdateSectionClass {
        @Test
        @DisplayName("Update successful")
        void updateSectionSuccessful() {
            SectionEntity sectionEntity = new SectionEntity();
            ChapterEntity chapterEntity = new ChapterEntity();
            ProjectEntity projectEntity = new ProjectEntity();
            UserEntity userEntity = new UserEntity();
            userEntity.setEmail("email@mail.com");
            projectEntity.setUserEntity(userEntity);
            chapterEntity.setProject(projectEntity);
            sectionEntity.setChapter(chapterEntity);
            doReturn(sectionEntity).when(sectionRepository).findById(1L);

            SectionTO sectionTO = new SectionTO();
            sectionTO.setId(1L);
            sectionTO.setTitle("Title");
            sectionTO.setDescription("Description");

            projectBC.updateSection(sectionTO, "email@mail.com");

            verify(sectionRepository, times(1)).findById(1L);
            verify(sectionRepository, times(1)).update(sectionEntity);
            assertEquals(sectionEntity.getTitle(), sectionTO.getTitle());
            assertEquals(sectionEntity.getDescription(), sectionTO.getDescription());
        }

        @Test
        @DisplayName("Update failed")
        void updateSectionFailed() {
            SectionEntity sectionEntity = new SectionEntity();
            ChapterEntity chapterEntity = new ChapterEntity();
            ProjectEntity projectEntity = new ProjectEntity();
            UserEntity userEntity = new UserEntity();
            userEntity.setEmail("different_email@mail.com");
            projectEntity.setUserEntity(userEntity);
            chapterEntity.setProject(projectEntity);
            sectionEntity.setChapter(chapterEntity);
            doReturn(sectionEntity).when(sectionRepository).findById(1L);

            SectionTO sectionTO = new SectionTO();
            sectionTO.setId(1L);

            assertThrows(IllegalArgumentException.class, () -> projectBC.updateSection(sectionTO, "email@mail.com"));
        }
    }
}