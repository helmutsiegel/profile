package org.helmut.profile.common.logging;

public class DefaultLoggerImpl implements Logger {
    @Override
    public void info(String message, Class clazz) {
        System.out.println("Default Logger" + message);
    }
}
