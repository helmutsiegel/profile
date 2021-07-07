package org.helmut.profile.common.logging;

/**
 * Implementation for {@link Logger}
 * This is a default logger
 */
public class DefaultLoggerImpl implements Logger {
    @Override
    public void info(String message, Class clazz) {
        System.out.println("Default Logger" + message);
    }
}
