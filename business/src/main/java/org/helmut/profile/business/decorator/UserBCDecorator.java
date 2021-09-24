package org.helmut.profile.business.decorator;

import org.helmut.profile.business.bci.UserBCI;
import org.helmut.profile.common.model.SignUpUserTO;
import org.helmut.profile.repository.CvRepository;
import org.helmut.profile.repository.UserRepository;
import org.helmut.profile.repository.entity.CVEntity;

import javax.decorator.Decorator;
import javax.decorator.Delegate;
import javax.inject.Inject;
import java.util.logging.Logger;

@Decorator
public abstract class UserBCDecorator implements UserBCI {

    Logger logger = Logger.getLogger(this.getClass().getSimpleName());

    @Inject
    private UserRepository userRepository;

    @Inject
    private CvRepository cvRepository;

    @Inject
    @Delegate
    private UserBCI userBC;

    @Override
    public void signUp(SignUpUserTO signUpUserTO) {
        logger.info("In UserBC Delegator");
        userBC.signUp(signUpUserTO);
        CVEntity cvEntity = new CVEntity();
        cvEntity.setUserEntity(userRepository.findByUniqueProperty("email", signUpUserTO.getEmail()));
        cvRepository.persist(cvEntity);
    }
}
