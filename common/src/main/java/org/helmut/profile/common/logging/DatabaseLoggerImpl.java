package org.helmut.profile.common.logging;

import org.helmut.profile.common.dataAccess.LogEntity;
import org.helmut.profile.common.dataAccess.LogRepository;
import org.helmut.profile.common.dataAccess.LogType;
import org.helmut.profile.common.qualifier.DatabaseLogger;

import javax.inject.Inject;

@DatabaseLogger
public class DatabaseLoggerImpl implements Logger {

    @Inject
    private LogRepository logRepository;

    @Override
    public void info(String message, Class clazz) {
        LogEntity logEntity = new LogEntity();
        logEntity.setLogType(LogType.INFO);
        logEntity.setClassName(clazz.getName());
        logEntity.setMessage(message);
        logRepository.persist(logEntity);
    }
}
