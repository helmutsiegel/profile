package org.helmut.profile.business;

import org.helmut.profile.mapping.ResumeMapper;
import org.helmut.profile.model.ResumeTO;
import org.helmut.profile.repository.CvRepository;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

@RequestScoped
public class ResumeBC {

    @Inject
    private ResumeMapper mapper;

    @Inject
    private CvRepository cvRepository;

    public ResumeTO getByUsername(String username) {
        return mapper.mapToTO(cvRepository.findByProperty("userEntity.username", username).get(0));
    }
}