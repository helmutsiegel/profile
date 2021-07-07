package org.helmut.profile.common.logging;

import javax.enterprise.inject.Vetoed;

/**
 * Implementation for {@link Logger}
 * This class is created just as an example for cdi vetoed
 * Currently we have two implementation for Logger with default qualifier in this package: {@link DefaultLoggerImpl} and this class
 * If we do not add the @Vetoed annotation to either of the mentioned classes,
 * then by deploying the application we will get and DeploymentException with the message 'Ambiguous dependencies for type Logger...'
 */
@Vetoed
public class VetoedLoggerImpl implements Logger {
    @Override
    public void info(String message, Class clazz) {

    }
}
