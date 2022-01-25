package by.academy.service;

import by.academy.pojo.account.User;

public interface UserService {
    User findUserByLogin(String login);
}
