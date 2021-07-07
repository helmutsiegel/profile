package org.helmut.profile.common.logging;

/**
 * Logger interface
 * Currently there is two implementation for this interface: one for logging in the database and one for default logging
 */
public interface Logger {
    void info(String message, Class clazz);
}
