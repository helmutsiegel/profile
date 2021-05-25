package org.helmut.profile.business;

import org.helmut.profile.mapping.CvMapper;
import org.helmut.profile.model.CvTO;
import org.helmut.profile.repository.CvRepository;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

@RequestScoped
public class CvBC {

    @Inject
    private CvRepository cvRepository;

    @Inject
    private CvMapper cvMapper;

    public CvTO getByUsername(String username) {
        return cvMapper.mapCvTO(cvRepository.findByProperty("userEntity.userName", username).get(0));
    }
}
