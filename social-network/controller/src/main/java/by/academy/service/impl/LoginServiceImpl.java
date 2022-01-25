package by.academy.service.impl;

import by.academy.dto.LoginCommand;
import by.academy.dto.UserDetailsDto;
import by.academy.exceptions.IncorrectUserPasswordException;
import by.academy.exceptions.UnknownException;
import by.academy.exceptions.UserNotFoundException;
import by.academy.pojo.account.User;
import by.academy.repository.AuthenticationInfoRepository;
import by.academy.repository.UserProfileRepository;
import by.academy.repository.UserRepository;
import by.academy.service.LoginService;
import by.academy.session.UserDetails;
import by.academy.util.BlobPictureUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;

@Service
public class LoginServiceImpl implements LoginService {
    private static final String USER_NOT_FOUND_MESSAGE = "Пользователь с таким логином не найден";
    private static final String INCORRECT_PASSWORD_MESSAGE = "Неправильный пароль";

    @Autowired
    private UserDetails userDetails;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthenticationInfoRepository authenticationInfoRepository;

    @Autowired
    private UserProfileRepository userProfileRepository;

    @Transactional(
            propagation = Propagation.REQUIRES_NEW,
            rollbackFor = {UserNotFoundException.class, UnknownException.class, IncorrectUserPasswordException.class},
            readOnly = true)
    public UserDetailsDto login(LoginCommand loginCommand) throws UserNotFoundException, IncorrectUserPasswordException, IOException {
        User user = userRepository.findUserByLogin(loginCommand.getLogin());
        if (user == null) {
            throw new UserNotFoundException(USER_NOT_FOUND_MESSAGE);
        }

        String password = authenticationInfoRepository.findCurrentPasswordByUserId(user.getId());
        if (!password.equals(loginCommand.getPassword())) {
            throw new IncorrectUserPasswordException(INCORRECT_PASSWORD_MESSAGE);
        }

        UserDetailsDto userDetailsDto = new UserDetailsDto();
        userDetailsDto.setUserId(user.getId());

        byte[] userAvatar = userProfileRepository.findUserAvatarByUserId(user.getId());
        userDetailsDto.setUserAvatar(BlobPictureUtil.getJspImageSrcUsingByteArray(userAvatar));
        return userDetailsDto;
    }
}