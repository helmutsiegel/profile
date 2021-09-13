package org.helmut.profile.common.logging;

import org.helmut.profile.common.dataAccess.LogEntity;
import org.helmut.profile.common.dataAccess.LogRepository;
import org.helmut.profile.common.dataAccess.LogType;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class DatabaseLoggerImplTest {

    @InjectMocks
    private DatabaseLoggerImpl logger;

    @Mock
    private LogRepository logRepository;

    @Test
    void info() {
        LogEntity logEntity = new LogEntity();
        logEntity.setLogType(LogType.INFO);
        logEntity.setClassName(TestEntity.class.getName());
        logEntity.setMessage("message");

        logger.info("message", TestEntity.class);

        verify(logRepository, times(1)).persist(argThat(logEntity::equals));
    }
}

class TestEntity {
}