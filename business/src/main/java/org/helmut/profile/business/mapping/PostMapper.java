package org.helmut.profile.business.mapping;

import org.helmut.profile.common.model.PostTO;
import org.helmut.profile.repository.entity.PostEntity;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;

@RequestScoped
public class PostMapper {

    @Inject
    private OffsetDateTime currentDateTime;

    public PostTO mapToTO(PostEntity entity) {
        PostTO postTO = new PostTO();
        postTO.setId(entity.getId());
        postTO.setTitle(entity.getTitle());
        postTO.setContent(entity.getContent());
        postTO.setDateCreated(entity.getDateCreated().format(DateTimeFormatter.ISO_INSTANT));
        postTO.setTags(entity.getTags());
        return postTO;
    }

    public PostEntity mapToEntity(PostTO postTO) {
        PostEntity postEntity = new PostEntity();
        postEntity.setTitle(postTO.getTitle());
        postEntity.setContent(postTO.getContent());
        postEntity.setTags(postTO.getTags());
        postEntity.setDateCreated(currentDateTime);
        return postEntity;
    }
}
