package org.helmut.profile.repository.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "T_CHAPTER")
public class ChapterEntity extends BaseEntity {

    @Column
    private String title;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "chapter_id")
    private List<SectionEntity> sections;

    public String getTitle() {
        return title;
    }

    public void setTitle(String name) {
        this.title = name;
    }

    public List<SectionEntity> getSections() {
        return sections;
    }

    public void setSections(List<SectionEntity> sections) {
        this.sections = sections;
    }
}
