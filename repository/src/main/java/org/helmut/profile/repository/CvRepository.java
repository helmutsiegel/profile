package org.helmut.profile.repository;

import org.helmut.profile.repository.entity.CVEntity;

public class CvRepository extends BaseRepository<CVEntity> {
    public CVEntity findByEmail(String email) {
        return findByUniqueProperty("userEntity.email", email);
    }
}
