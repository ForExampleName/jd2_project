package by.academy.service.impl;

import by.academy.pojo.account.AuthenticationInfo;
import by.academy.pojo.account.User;
import by.academy.repository.AuthenticationInfoRepository;
import by.academy.service.AuthenticationInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
public class AuthenticationInfoServiceInfo implements AuthenticationInfoService {
    @Autowired
    private AuthenticationInfoRepository authenticationInfoRepository;

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void saveNewPassword(User user, String password) {
        AuthenticationInfo authenticationInfo = new AuthenticationInfo(user, password, LocalDateTime.now());
        authenticationInfoRepository.save(authenticationInfo);
    }
}
