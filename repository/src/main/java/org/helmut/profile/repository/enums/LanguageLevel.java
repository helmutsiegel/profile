package org.helmut.profile.repository.enums;

public enum LanguageLevel {
    BEGINNER(0), ELEMENTARY(1), INTERMEDIATE(2), ADVANCED(3), PROFICIENT(4), NATIVE(5);

    private final long level;

    LanguageLevel(long level) {
        if (level < 0 || level > 5) {
            throw new IllegalArgumentException("Language level muss bee between 0 and 5.");
        }
        this.level = level;
    }

    public long getLevel() {
        return level;
    }
}
