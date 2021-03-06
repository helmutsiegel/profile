package org.helmut.profile.repository.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "T_PROJECT")
public class ProjectEntity extends BaseEntity {

    @Column(unique = true)
    private String name;

    @Column
    private String description;

    @ManyToOne
    private UserEntity userEntity;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "project_id")
    private List<ChapterEntity> chapters;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public UserEntity getUserEntity() {
        return userEntity;
    }

    public void setUserEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
    }

    public List<ChapterEntity> getChapters() {
        return chapters;
    }

    public void setChapters(List<ChapterEntity> chapters) {
        this.chapters = chapters;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void addChapter(ChapterEntity chapterEntity) {
        if (Objects.isNull(this.chapters)) {
            this.chapters = new ArrayList<>();
        }
        this.chapters.add(chapterEntity);
    }
}
