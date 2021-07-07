package org.helmut.profile.common.qualifier;

import javax.inject.Qualifier;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;

/**
 * Qualifier used by {@link org.helmut.profile.common.logging.DatabaseLoggerImpl}
 * It differentiates between default logging and logging in the database
 */
@Target({TYPE, METHOD, PARAMETER, FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Qualifier
public @interface DatabaseLogger {
}
