package org.helmut.profile.repository.entity;

import javax.persistence.*;

@Entity
@Table(name = "T_POST")
public class PostEntity extends BaseEntity {

    @Column
    private String title;

    @Column
    private String content;

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
}
