package org.helmut.profile.business.bc;

import org.helmut.profile.business.interceptor.Loggable;
import org.helmut.profile.business.mapping.PostMapper;
import org.helmut.profile.business.model.PostTO;
import org.helmut.profile.repository.PostRepository;
import org.helmut.profile.repository.UserRepository;
import org.helmut.profile.repository.entity.PostEntity;
import org.helmut.profile.repository.entity.UserEntity;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;

@RequestScoped
public class PostBC {

    @Inject
    private PostRepository postRepository;

    @Inject
    private UserRepository userRepository;

    @Inject
    PostMapper postMapper;

    public List<PostTO> getPostsByEmail(String email) {
        return postRepository.findByProperty("userEntity.email", email)
                .stream()
                .map(postMapper::mapToTO)
                .collect(Collectors.toList());
    }

    @Loggable
    public void newPost(PostTO postTO, String email) {
        UserEntity userEntity = userRepository.getByEmail(email);
        PostEntity postEntity = postMapper.mapToEntity(postTO);
        postEntity.setUserEntity(userEntity);
        postRepository.persist(postEntity);
    }

    public void deletePost(Long id, String email) {
        PostEntity postEntity = postRepository.findById(id);
        if (email.equals(postEntity.getUserEntity().getEmail())) {
            postRepository.delete(postEntity);
        } else {
            throw new IllegalArgumentException("You are not allowed to delete this post!");
        }
    }

    public void updatePost(PostTO postTO, String email) {
        PostEntity postEntity = postRepository.findById(postTO.getId());
        if (email.equals(postEntity.getUserEntity().getEmail())) {
            postEntity.setContent(postTO.getContent());
            postEntity.setTags(postTO.getTags());
            postRepository.update(postEntity);
        } else {
            throw new IllegalArgumentException("You are not allowed to update this post!");
        }
    }
}
