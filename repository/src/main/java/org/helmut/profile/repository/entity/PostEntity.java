package org.helmut.profile.repository.entity;

import javax.persistence.*;
import java.time.ZonedDateTime;

@Entity
@Table(name = "T_POST")
public class PostEntity extends BaseEntity {

    @Column
    private String title;

    @Column
    private String content;

    @Column(name = "date_created")
    private ZonedDateTime dateCreated;

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

    public ZonedDateTime getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(ZonedDateTime dateCreated) {
        this.dateCreated = dateCreated;
    }
}
