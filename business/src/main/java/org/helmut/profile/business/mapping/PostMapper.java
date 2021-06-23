package org.helmut.profile.business.mapping;

import org.helmut.profile.business.model.PostTO;
import org.helmut.profile.repository.entity.PostEntity;

import javax.enterprise.context.RequestScoped;

@RequestScoped
public class PostMapper {

    public PostTO mapToTO(PostEntity entity) {
        PostTO postTO = new PostTO();
        postTO.setTitle(entity.getTitle());
        postTO.setContent(entity.getContent());
        return postTO;
    }

    public PostEntity mapToEntity(PostTO postTO){
        PostEntity postEntity = new PostEntity();
        postEntity.setTitle(postTO.getTitle());
        postEntity.setContent(postTO.getContent());
        return postEntity;
    }
}
