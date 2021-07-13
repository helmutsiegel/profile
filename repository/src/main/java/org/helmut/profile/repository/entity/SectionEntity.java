package org.helmut.profile.repository.entity;

import javax.persistence.*;

@Entity
@Table(name = "T_SECTION")
public class SectionEntity extends BaseEntity {

    @Column
    private String title;

    @Column
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    private ChapterEntity chapter;

    public String getTitle() {
        return title;
    }

    public void setTitle(String name) {
        this.title = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ChapterEntity getChapter() {
        return chapter;
    }

    public void setChapter(ChapterEntity chapter) {
        this.chapter = chapter;
    }
}
