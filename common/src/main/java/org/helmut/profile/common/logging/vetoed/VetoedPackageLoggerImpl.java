package org.helmut.profile.common.logging.vetoed;

import org.helmut.profile.common.logging.Logger;

/**
 * Implementation for {@link Logger}
 * This is an example of vetoing an entire package for bean discovery
 * In the package-info.java we annotated the whole package with @Vetoed
 * If the package would not be vetoed, we would receive Deployment exception
 * Because the whole package is vetoed, we will not receive Deployment exception
 * Check out an another example example of vetoing in the class {@link org.helmut.profile.common.logging.VetoedLoggerImpl}
 */
public class VetoedPackageLoggerImpl implements Logger {
    @Override
    public void info(String message, Class clazz) {

    }
}
