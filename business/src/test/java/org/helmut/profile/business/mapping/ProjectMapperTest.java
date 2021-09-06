package org.helmut.profile.business.mapping;

import org.checkerframework.checker.units.qual.C;
import org.helmut.profile.business.model.*;
import org.helmut.profile.repository.entity.ChapterEntity;
import org.helmut.profile.repository.entity.ProjectEntity;
import org.helmut.profile.repository.entity.SectionEntity;
import org.helmut.profile.repository.entity.UserEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.doReturn;

@ExtendWith(MockitoExtension.class)
class ProjectMapperTest {

    @InjectMocks
    private ProjectMapper projectMapper;

    @Mock
    private UserMapper userMapper;

    @Test
    void mapToProjectTO() {
        ProjectEntity projectEntity = createProjectEntity();
        UserEntity userEntity = new UserEntity();
        projectEntity.setUserEntity(userEntity);
        UserTO userTO = new UserTO();
        doReturn(userTO).when(userMapper).mapToTO(userEntity);

        ProjectTO projectTO = projectMapper.mapToProjectTO(projectEntity);

        assertEquals(projectEntity.getName(), projectTO.getName());
        assertEquals(projectEntity.getDescription(), projectTO.getDescription());
        ChapterEntity chapterEntity = projectEntity.getChapters().get(0);
        ChapterTO chapterTO = projectTO.getChapters().get(0);
        assertEquals(chapterEntity.getTitle(), chapterTO.getTitle());
        SectionEntity sectionEntity = chapterEntity.getSections().get(0);
        SectionTO sectionTO = chapterTO.getSections().get(0);
        assertEquals(sectionEntity.getTitle(), sectionTO.getTitle());
        assertEquals(sectionEntity.getDescription(), sectionTO.getDescription());
        assertEquals(sectionEntity.getId(), sectionTO.getId());
        assertSame(userTO, projectTO.getUserTO());
    }

    @Test
    void mapCreateChapterTO() {
        CreateChapterTO createChapterTO = new CreateChapterTO();
        createChapterTO.setTitle("Title");
        ChapterEntity chapterEntity = projectMapper.mapCreateChapterTO(createChapterTO);

        assertEquals(chapterEntity.getTitle(), createChapterTO.getTitle());
        SectionEntity sectionEntity = chapterEntity.getSections().get(0);
        assertEquals(sectionEntity.getTitle(), "Untitled");
        assertEquals(sectionEntity.getDescription(), "No description");
        assertSame(sectionEntity.getChapter(), chapterEntity);
    }

    private ProjectEntity createProjectEntity() {
        ProjectEntity entity = new ProjectEntity();
        entity.setName("Name");
        entity.setDescription("Description");
        entity.setChapters(createChapters());
        return entity;
    }

    private List<ChapterEntity> createChapters() {
        ChapterEntity chapterEntity = new ChapterEntity();
        chapterEntity.setTitle("Chapter title");
        chapterEntity.setSections(createSections());
        return Collections.singletonList(chapterEntity);
    }

    private List<SectionEntity> createSections() {
        SectionEntity sectionEntity = new SectionEntity();
        sectionEntity.setId(1L);
        sectionEntity.setTitle("Section title");
        sectionEntity.setDescription("Section description");
        return Collections.singletonList(sectionEntity);
    }
}