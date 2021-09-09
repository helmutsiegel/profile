package org.helmut.profile.rest.service;

import org.helmut.profile.business.bc.ProjectBC;
import org.helmut.profile.business.model.CreateChapterTO;
import org.helmut.profile.business.model.ProjectTO;
import org.helmut.profile.business.model.SectionTO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import java.util.Collections;
import java.util.List;

import static org.helmut.profile.rest.service.Constants.CURRENT_USER_EMAIL;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProjectServiceTest {

    @InjectMocks
    private ProjectService projectService;

    @Mock
    private ProjectBC projectBC;

    @Mock
    private HttpHeaders httpHeaders;

    @Test
    void getByName() {
        ProjectTO projectFromBC = new ProjectTO();
        doReturn(projectFromBC).when(projectBC).getByName("name");

        ProjectTO projectTO = projectService.getByName("name");

        assertSame(projectTO, projectFromBC);
        verify(projectBC, times(1)).getByName("name");
    }

    @Test
    void getByEmail() {
        String email = "mail@mail.com";
        List<ProjectTO> projectsFromBC = Collections.singletonList(new ProjectTO());
        doReturn(projectsFromBC).when(projectBC).getByEmail(email);

        List<ProjectTO> projectTOS = projectService.getByEmail(email);

        assertSame(projectsFromBC, projectTOS);
        verify(projectBC, times(1)).getByEmail(email);
    }

    @Nested
    @DisplayName("Create project test")
    class CreateProjectTest {

        @Test
        @DisplayName("Successful")
        void createProjectSuccessful() {
            ProjectTO projectTO = new ProjectTO();
            doReturn("email").when(httpHeaders).getHeaderString(CURRENT_USER_EMAIL);

            Response response = projectService.createProject(projectTO);

            assertEquals(projectTO.getUserTO().getEmail(), "email");
            assertEquals(response.getStatus(), Response.Status.OK.getStatusCode());
            verify(projectBC, times(1)).createProject(projectTO);
        }

        @Test
        @DisplayName("Failed")
        void createProjectFailed() {
            ProjectTO projectTO = new ProjectTO();
            Exception exception = new IllegalArgumentException();
            doThrow(exception).when(projectBC).createProject(projectTO);

            Response response = projectService.createProject(projectTO);

            assertEquals(response.getStatus(), Response.Status.BAD_REQUEST.getStatusCode());
            verify(projectBC, times(1)).createProject(projectTO);
            assertSame(exception, response.getEntity());
        }
    }

    @Nested
    @DisplayName("Create project test")
    class CreateChapterTest {

        @Test
        @DisplayName("Successful")
        void createChapterSuccessful() {
            CreateChapterTO createChapterTO = new CreateChapterTO();
            doReturn("email").when(httpHeaders).getHeaderString(CURRENT_USER_EMAIL);

            Response response = projectService.createChapter(createChapterTO);

            assertEquals(response.getStatus(), Response.Status.OK.getStatusCode());
            verify(projectBC, times(1)).createChapter(createChapterTO, "email");
        }

        @Test
        @DisplayName("Failed")
        void createChapterFailed() {
            CreateChapterTO createChapterTO = new CreateChapterTO();
            Exception exception = new IllegalArgumentException();
            String email = "email";
            doReturn(email).when(httpHeaders).getHeaderString(CURRENT_USER_EMAIL);
            doThrow(exception).when(projectBC).createChapter(createChapterTO, email);

            Response response = projectService.createChapter(createChapterTO);

            assertEquals(response.getStatus(), Response.Status.BAD_REQUEST.getStatusCode());
            verify(projectBC, times(1)).createChapter(createChapterTO, email);
            assertSame(exception, response.getEntity());
        }
    }

    @Nested
    @DisplayName("Create project test")
    class UpdateSectionTest {

        @Test
        @DisplayName("Successful")
        void updateSectionSuccessful() {
            SectionTO sectionTO = new SectionTO();
            doReturn("email").when(httpHeaders).getHeaderString(CURRENT_USER_EMAIL);

            Response response = projectService.updateSection(sectionTO);

            assertEquals(response.getStatus(), Response.Status.OK.getStatusCode());
            verify(projectBC, times(1)).updateSection(sectionTO, "email");
        }

        @Test
        @DisplayName("Failed")
        void updateSectionFailed() {
            SectionTO sectionTO = new SectionTO();
            Exception exception = new IllegalArgumentException();
            String email = "email";
            doReturn(email).when(httpHeaders).getHeaderString(CURRENT_USER_EMAIL);
            doThrow(exception).when(projectBC).updateSection(sectionTO, email);

            Response response = projectService.updateSection(sectionTO);

            assertEquals(response.getStatus(), Response.Status.BAD_REQUEST.getStatusCode());
            verify(projectBC, times(1)).updateSection(sectionTO, email);
            assertSame(exception, response.getEntity());
        }
    }
}