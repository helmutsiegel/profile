package org.helmut.profile.repository.enums;

public enum Seniority {
    JUNIOR(0), MID(1), SENIOR(2);

    private final long level;

    Seniority(long level) {
        if (level < 0 || level > 2) {
            throw new IllegalArgumentException("Seniority level muss bee between 0 and 2.");
        }
        this.level = level;
    }

    public long getLevel() {
        return level;
    }
}
