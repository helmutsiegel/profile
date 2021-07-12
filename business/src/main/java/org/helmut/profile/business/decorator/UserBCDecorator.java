package org.helmut.profile.business.decorator;

import org.helmut.profile.business.bci.UserBCI;
import org.helmut.profile.business.model.SignUpUserTO;
import org.helmut.profile.repository.CvRepository;
import org.helmut.profile.repository.UserRepository;
import org.helmut.profile.repository.entity.CVEntity;

import javax.decorator.Decorator;
import javax.decorator.Delegate;
import javax.inject.Inject;

@Decorator
public abstract class UserBCDecorator implements UserBCI {

    @Inject
    private UserRepository userRepository;

    @Inject
    private CvRepository cvRepository;

    @Inject
    @Delegate
    private UserBCI userBC;

    @Override
    public void signUp(SignUpUserTO signUpUserTO) {
        userBC.signUp(signUpUserTO);
        CVEntity cvEntity = new CVEntity();
        cvEntity.setUserEntity(userRepository.findByUniqueProperty("email", signUpUserTO.getEmail()));
        cvRepository.persist(cvEntity);
        System.out.println("in decorator");
    }
}
