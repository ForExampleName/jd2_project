package by.academy.service.impl;

import by.academy.enums.AccountStatus;
import by.academy.pojo.account.User;
import by.academy.pojo.account.UserStatus;
import by.academy.repository.UserRepository;
import by.academy.repository.UserStatusRepository;
import by.academy.service.UserStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
public class UserStatusServiceImpl implements UserStatusService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    UserStatusRepository userStatusRepository;

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW, readOnly = true)
    public AccountStatus findCurrentAccountStatusByUserId(String userId) {
        return userStatusRepository.findCurrentAccountStatusByUserId(userId);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW, readOnly = true)
    public AccountStatus findCurrentAccountStatusByUserLogin(String userLogin) {
        return userStatusRepository.findCurrentAccountStatusByUserLogin(userLogin);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void blockUserAccount(String userId) {
        User user = userRepository.findUserById(userId);
        UserStatus userStatus = new UserStatus(user, AccountStatus.BLOCKED, LocalDateTime.now());
        userStatusRepository.save(userStatus);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void unblockUserAccount(String userId) {
        User user = userRepository.findUserById(userId);
        UserStatus userStatus = new UserStatus(user, AccountStatus.ACTIVE, LocalDateTime.now());
        userStatusRepository.save(userStatus);
    }
}
