package by.academy.service;

import by.academy.dto.LoginCommand;
import by.academy.dto.UserDetailsDto;
import by.academy.exceptions.IncorrectUserPasswordException;
import by.academy.exceptions.UserNotFoundException;

import java.io.IOException;

public interface LoginService {
    UserDetailsDto login(LoginCommand loginDto) throws UserNotFoundException, IncorrectUserPasswordException, IOException;
}
