package org.helmut.profile.repository.entity;

import javax.persistence.*;
import java.time.OffsetDateTime;

@Entity
@Table(name = "T_POST")
public class PostEntity extends BaseEntity {

    @Column
    private String title;

    @Column(length = 5000)
    private String content;

    @Column(name = "date_created")
    private OffsetDateTime dateCreated;

    @Column
    private String tags;

    @ManyToOne
    @JoinColumn
    private UserEntity userEntity;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public UserEntity getUserEntity() {
        return userEntity;
    }

    public void setUserEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public OffsetDateTime getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(OffsetDateTime dateCreated) {
        this.dateCreated = dateCreated;
    }
}
