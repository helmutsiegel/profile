package org.helmut.profile.repository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.persistence.EntityManager;

@ExtendWith(MockitoExtension.class)
class CvRepositoryTest {

    @Mock
    private EntityManager em;

    @InjectMocks
    private CvRepository cvRepository;

    @Test
    void findByEmail() {
        //TODO figure out how to test
    }
}