package org.helmut.profile.business.mapping;

import org.helmut.profile.business.model.ChapterTO;
import org.helmut.profile.business.model.ProjectTO;
import org.helmut.profile.business.model.SectionTO;
import org.helmut.profile.repository.entity.ChapterEntity;
import org.helmut.profile.repository.entity.ProjectEntity;
import org.helmut.profile.repository.entity.SectionEntity;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import java.util.stream.Collectors;

@RequestScoped
public class ProjectMapper {

    @Inject
    private UserMapper userMapper;

    public ProjectTO mapToProjectTO(ProjectEntity entity) {
        ProjectTO projectTO = new ProjectTO();
        projectTO.setName(entity.getName());
        projectTO.setDescription(entity.getDescription());
        projectTO.setChapters(entity.getChapters().stream().map(this::mapChapterToTO).collect(Collectors.toList()));
        projectTO.setUserTO(userMapper.mapToTO(entity.getUserEntity()));
        return projectTO;
    }

    private ChapterTO mapChapterToTO(ChapterEntity entity) {
        ChapterTO chapterTO = new ChapterTO();
        chapterTO.setTitle(entity.getTitle());
        chapterTO.setSections(entity.getSections().stream().map(this::mapSectionToTO).collect(Collectors.toList()));
        return chapterTO;
    }

    private SectionTO mapSectionToTO(SectionEntity entity) {
        SectionTO sectionTO = new SectionTO();
        sectionTO.setTitle(entity.getTitle());
        sectionTO.setDescription(entity.getDescription());
        return sectionTO;
    }

}
