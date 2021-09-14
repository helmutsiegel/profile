package org.helmut.profile.common.util;

import java.util.Objects;

/**
 * Util methods for the project
 */
public class ProfileUtils {
    public static boolean isNullOrEmpty(String value) {
        return Objects.isNull(value) || value.length() == 0;
    }
}
