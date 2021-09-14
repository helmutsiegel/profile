package org.helmut.profile.business.bc;

import org.helmut.profile.business.mapping.PostMapper;
import org.helmut.profile.common.model.PostTO;
import org.helmut.profile.repository.PostRepository;
import org.helmut.profile.repository.UserRepository;
import org.helmut.profile.repository.entity.PostEntity;
import org.helmut.profile.repository.entity.UserEntity;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PostBCTest {

    @InjectMocks
    private PostBC postBC;

    @Mock
    private PostRepository postRepository;

    @Mock
    private PostMapper postMapper;

    @Mock
    private UserRepository userRepository;

    @Test
    void getPostsByEmail() {
        String email = "mail@mail.com";
        PostEntity postEntity = new PostEntity();
        doReturn(Collections.singletonList(postEntity)).when(postRepository).findByProperty("userEntity.email", email);
        PostTO mappedPostTO = new PostTO();
        doReturn(mappedPostTO).when(postMapper).mapToTO(postEntity);

        List<PostTO> postsByEmail = postBC.getPostsByEmail(email);

        verify(postRepository, times(1)).findByProperty("userEntity.email", email);
        verify(postMapper, times(1)).mapToTO(postEntity);
        assertSame(postsByEmail.get(0), mappedPostTO);
        assertEquals(postsByEmail.size(), 1);
    }

    @Test
    void newPost() {
        String email = "mail@mail.com";
        PostTO postTO = new PostTO();
        PostEntity postEntity = new PostEntity();
        doReturn(postEntity).when(postMapper).mapToEntity(postTO);
        UserEntity userEntity = new UserEntity();
        doReturn(userEntity).when(userRepository).getByEmail(email);

        postBC.newPost(postTO, email);

        assertSame(postEntity.getUserEntity(), userEntity);
        verify(userRepository, times(1)).getByEmail(email);
        verify(postMapper, times(1)).mapToEntity(postTO);
        verify(postRepository, times(1)).persist(postEntity);
    }

    @Nested
    @DisplayName("Delete post")
    class DeletePostTest {
        @Test
        @DisplayName("Delete successful")
        void deletePostSuccessful() {
            String email = "mail@mail.com";
            PostEntity postEntity = new PostEntity();
            UserEntity userEntity = new UserEntity();
            userEntity.setEmail(email);
            postEntity.setUserEntity(userEntity);
            doReturn(postEntity).when(postRepository).findById(1L);

            postBC.deletePost(1L, email);

            verify(postRepository, times(1)).findById(1L);
            verify(postRepository, times(1)).delete(postEntity);
        }

        @Test
        @DisplayName("Delete failed")
        void deletePostFailed() {
            PostEntity postEntity = new PostEntity();
            UserEntity userEntity = new UserEntity();
            userEntity.setEmail("users_email@mail.com");
            postEntity.setUserEntity(userEntity);
            doReturn(postEntity).when(postRepository).findById(1L);

            assertThrows(IllegalArgumentException.class, () -> postBC.deletePost(1L, "different_mail@mail.com"));
            verify(postRepository, times(1)).findById(1L);
        }
    }

    @Nested
    @DisplayName("Update post")
    class UpdatePostTest {

        @Test
        @DisplayName("Update successful")
        void updatePostSuccessful() {
            String email = "mail@mail.com";
            PostEntity postEntity = new PostEntity();
            UserEntity userEntity = new UserEntity();
            userEntity.setEmail(email);
            postEntity.setUserEntity(userEntity);

            PostTO postTO = new PostTO();
            postTO.setId(1L);
            postTO.setContent("Content");
            postTO.setTags("Tags");

            doReturn(postEntity).when(postRepository).findById(postTO.getId());

            postBC.updatePost(postTO, email);

            assertEquals(postEntity.getContent(), postTO.getContent());
            assertEquals(postEntity.getTags(), postTO.getTags());
            verify(postRepository, times(1)).findById(postTO.getId());
            verify(postRepository, times(1)).update(postEntity);
        }

        @Test
        @DisplayName("Update post failed")
        void updatePostFailed() {
            PostEntity postEntity = new PostEntity();
            UserEntity userEntity = new UserEntity();
            userEntity.setEmail("users_email@mail.com");
            postEntity.setUserEntity(userEntity);

            PostTO postTO = new PostTO();
            postTO.setId(1L);

            doReturn(postEntity).when(postRepository).findById(postTO.getId());

            assertThrows(IllegalArgumentException.class, () -> postBC.updatePost(postTO, "different_email@mail.com"));
            verify(postRepository, times(1)).findById(postTO.getId());
        }
    }


    @Test
    void getById() {
        PostEntity postEntity = new PostEntity();
        doReturn(postEntity).when(postRepository).findById(1L);
        PostTO mappedPostTO = new PostTO();
        doReturn(mappedPostTO).when(postMapper).mapToTO(postEntity);

        PostTO postTO = postBC.getById(1L);

        assertSame(postTO, mappedPostTO);
        verify(postRepository, times(1)).findById(1L);
        verify(postMapper, times(1)).mapToTO(postEntity);
    }
}