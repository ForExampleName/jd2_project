package by.academy.service;

import by.academy.pojo.account.User;

public interface AuthenticationInfoService {
    void saveNewPassword(User user, String password);
}
