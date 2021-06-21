package org.helmut.profile.repository.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "T_PROJECT")
public class ProjectEntity extends BaseEntity {

    @Column
    private String name;

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
}
