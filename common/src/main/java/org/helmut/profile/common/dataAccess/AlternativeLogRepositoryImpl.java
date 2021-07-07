package org.helmut.profile.common.dataAccess;

import javax.enterprise.inject.Alternative;

@Alternative
public class AlternativeLogRepositoryImpl implements LogRepository {
    @Override
    public void persist(LogEntity entity) {
        System.out.println("Alternative repository");
    }
}
