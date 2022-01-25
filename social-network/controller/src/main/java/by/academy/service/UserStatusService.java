package by.academy.service;

import by.academy.enums.AccountStatus;

public interface UserStatusService {
    AccountStatus findCurrentAccountStatusByUserId(String userId);

    AccountStatus findCurrentAccountStatusByUserLogin(String userLogin);

    void blockUserAccount(String userId);

    void unblockUserAccount(String userId);
}
