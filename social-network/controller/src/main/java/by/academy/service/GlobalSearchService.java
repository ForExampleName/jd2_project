package by.academy.service;

import by.academy.dto.UserPresentInfoDto;

import java.util.List;

public interface GlobalSearchService {
    List<UserPresentInfoDto> searchAllUsers();
}
