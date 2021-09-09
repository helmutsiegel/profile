package org.helmut.profile.rest.service;

import org.helmut.profile.business.bc.PostBC;
import org.helmut.profile.business.model.PostTO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import java.util.Collections;
import java.util.List;

import static org.helmut.profile.rest.service.Constants.CURRENT_USER_EMAIL;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PostServiceTest {

    @InjectMocks
    private PostService postService;

    @Mock
    private PostBC postBC;

    @Mock
    private HttpHeaders httpHeaders;

    @Test
    void getByEmail() {
        String email = "mail@mail.com";
        List<PostTO> postsFromBC = Collections.singletonList(new PostTO());
        doReturn(postsFromBC).when(postBC).getPostsByEmail(email);

        List<PostTO> postTOS = postService.getByEmail(email);

        assertSame(postsFromBC, postTOS);
        verify(postBC, times(1)).getPostsByEmail(email);
    }

    @Test
    void getById() {
        long id = 1L;
        PostTO postFromBC = new PostTO();
        doReturn(postFromBC).when(postBC).getById(id);

        PostTO postTO = postService.getById(id);

        assertSame(postFromBC, postTO);
        verify(postBC, times(1)).getById(id);
    }

    @Nested
    @DisplayName("New post test")
    class NewPostTest {
        @Test
        @DisplayName("Create successful")
        void newPostSuccessful() {
            String email = "mail@mail.com";
            doReturn(email).when(httpHeaders).getHeaderString(CURRENT_USER_EMAIL);

            PostTO postToCreate = new PostTO();
            Response response = postService.newPost(postToCreate);

            verify(httpHeaders, times(1)).getHeaderString(CURRENT_USER_EMAIL);
            verify(postBC, times(1)).newPost(postToCreate, email);
            assertEquals(response.getStatus(), Response.Status.OK.getStatusCode());
        }

        @Test
        @DisplayName("Create failed")
        void newPostFailed() {
            doThrow(new IllegalArgumentException()).when(postBC).newPost(any(), any());

            assertEquals(postService.newPost(new PostTO()).getStatus(), Response.Status.BAD_REQUEST.getStatusCode());
            verify(postBC, times(1)).newPost(any(), any());
        }
    }

    @Nested
    @DisplayName("Update post test")
    class UpdatePostTest {
        @Test
        @DisplayName("Update successful")
        void updatePostSuccessful() {
            String email = "mail@mail.com";
            doReturn(email).when(httpHeaders).getHeaderString(CURRENT_USER_EMAIL);

            PostTO postToCreate = new PostTO();
            Response response = postService.updatePost(postToCreate);

            verify(httpHeaders, times(1)).getHeaderString(CURRENT_USER_EMAIL);
            verify(postBC, times(1)).updatePost(postToCreate, email);
            assertEquals(response.getStatus(), Response.Status.OK.getStatusCode());
        }

        @Test
        @DisplayName("Update failed")
        void updatePostFailed() {
            doThrow(new IllegalArgumentException()).when(postBC).updatePost(any(), any());

            assertEquals(postService.updatePost(new PostTO()).getStatus(), Response.Status.BAD_REQUEST.getStatusCode());
            verify(postBC, times(1)).updatePost(any(), any());
        }
    }

    @Nested
    @DisplayName("Delete post test")
    class DeletePostTest {
        @Test
        @DisplayName("Delete successful")
        void deletePostSuccessful() {
            String email = "mail@mail.com";
            doReturn(email).when(httpHeaders).getHeaderString(CURRENT_USER_EMAIL);

            Response response = postService.delete(1L);

            verify(httpHeaders, times(1)).getHeaderString(CURRENT_USER_EMAIL);
            verify(postBC, times(1)).deletePost(1L, email);
            assertEquals(response.getStatus(), Response.Status.OK.getStatusCode());
        }

        @Test
        @DisplayName("Delete failed")
        void deletePostFailed() {
            doThrow(new IllegalArgumentException()).when(postBC).deletePost(any(), any());

            assertEquals(postService.delete(1L).getStatus(), Response.Status.BAD_REQUEST.getStatusCode());
            verify(postBC, times(1)).deletePost(any(), any());
        }
    }
}