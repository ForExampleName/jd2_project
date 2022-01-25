package by.academy.service.impl;

import by.academy.pojo.account.User;
import by.academy.repository.UserRepository;
import by.academy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Transactional(propagation = Propagation.REQUIRES_NEW, readOnly = true)
    public User findUserByLogin(String login) {
        return userRepository.findUserByLogin(login);
    }
}
