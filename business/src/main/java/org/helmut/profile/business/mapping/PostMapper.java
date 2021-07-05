package org.helmut.profile.business.mapping;

import org.helmut.profile.business.model.PostTO;
import org.helmut.profile.repository.entity.PostEntity;

import javax.enterprise.context.RequestScoped;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@RequestScoped
public class PostMapper {

    public PostTO mapToTO(PostEntity entity) {
        PostTO postTO = new PostTO();
        postTO.setId(entity.getId());
        postTO.setTitle(entity.getTitle());
        postTO.setContent(entity.getContent());
        postTO.setDateCreated(entity.getDateCreated().toLocalDateTime());
        return postTO;
    }

    public PostEntity mapToEntity(PostTO postTO) {
        PostEntity postEntity = new PostEntity();
        postEntity.setTitle(postTO.getTitle());
        postEntity.setContent(postTO.getContent());
        postEntity.setDateCreated(Timestamp.valueOf(LocalDateTime.now()));
        return postEntity;
    }
}
