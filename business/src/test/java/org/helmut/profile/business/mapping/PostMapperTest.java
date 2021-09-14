package org.helmut.profile.business.mapping;

import org.helmut.profile.common.model.PostTO;
import org.helmut.profile.repository.entity.PostEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class PostMapperTest {

    @InjectMocks
    private PostMapper postMapper;

    @Test
    void mapToTO() {
        PostEntity postEntity = new PostEntity();
        postEntity.setId(1L);
        postEntity.setTitle("Title");
        postEntity.setContent("Content");
        postEntity.setDateCreated(OffsetDateTime.now());
        postEntity.setTags("Tags");

        PostTO postTO = postMapper.mapToTO(postEntity);

        assertEquals(postTO.getId(), postEntity.getId());
        assertEquals(postTO.getTitle(), postEntity.getTitle());
        assertEquals(postTO.getContent(), postEntity.getContent());
        assertEquals(postTO.getDateCreated(), postEntity.getDateCreated().format(DateTimeFormatter.ISO_INSTANT));
        assertEquals(postTO.getTags(), postEntity.getTags());
    }

    @Test
    void mapToEntity() {
        PostTO postTO = new PostTO();
        postTO.setTitle("Title");
        postTO.setContent("Content");
        postTO.setTags("Tags");

        PostEntity postEntity = postMapper.mapToEntity(postTO);

        assertEquals(postEntity.getTitle(), postTO.getTitle());
        assertEquals(postEntity.getContent(), postTO.getContent());
        assertEquals(postEntity.getTags(), postTO.getTags());
    }
}