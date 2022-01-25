package by.academy.service;

import by.academy.dto.SignUpCommand;
import by.academy.exceptions.UserAlreadyExistException;

import java.io.IOException;

public interface SignUpService {
    void signup(SignUpCommand signUpDto) throws UserAlreadyExistException, IOException;
}
