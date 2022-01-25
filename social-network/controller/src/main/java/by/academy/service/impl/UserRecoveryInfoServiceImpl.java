package by.academy.service.impl;

import by.academy.repository.UserRecoveryInfoRepository;
import by.academy.service.UserRecoveryInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserRecoveryInfoServiceImpl implements UserRecoveryInfoService {
    @Autowired
    private UserRecoveryInfoRepository userRecoveryInfoRepository;

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW, readOnly = true)
    public String findSecretQuestionByUserId(String userId) {
        return userRecoveryInfoRepository.findSecretQuestionByUserId(userId);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW, readOnly = true)
    public String findAnswerByUserId(String userId) {
        return userRecoveryInfoRepository.findAnswerByUserId(userId);
    }
}
