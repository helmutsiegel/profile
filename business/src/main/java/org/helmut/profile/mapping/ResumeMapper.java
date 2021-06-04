package org.helmut.profile.mapping;

import org.helmut.profile.model.ResumeTO;
import org.helmut.profile.repository.entity.CVEntity;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

@RequestScoped
public class ResumeMapper {

    @Inject
    private UserMapper userMapper;

    public ResumeTO mapToTO(CVEntity entity){
        ResumeTO resumeTO = new ResumeTO();
        resumeTO.setUserTO(userMapper.mapToTO(entity.getUserEntity()));
        return resumeTO;
    }
}
