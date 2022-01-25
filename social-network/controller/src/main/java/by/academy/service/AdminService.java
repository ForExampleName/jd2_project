package by.academy.service;

import by.academy.dto.UserPageAdminDto;

public interface AdminService {
    boolean isAdmin(String userId);

    UserPageAdminDto getUserPageAdminDto(String userId);
}
