package by.academy.service.impl;

import by.academy.dto.SignUpCommand;
import by.academy.enums.AccountStatus;
import by.academy.enums.Gender;
import by.academy.enums.UserRoleType;
import by.academy.exceptions.UnknownException;
import by.academy.exceptions.UserAlreadyExistException;
import by.academy.pojo.account.*;
import by.academy.pojo.profile.UserProfile;
import by.academy.repository.*;
import by.academy.service.SignUpService;
import by.academy.session.UserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ResourceUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Service
public class SignUpServiceImpl implements SignUpService {
    private static final String USER_ALREADY_EXISTS_MESSAGE = "Пользователь с таким логином уже существует";

    @Autowired
    private UserDetails userDetails;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthenticationInfoRepository authenticationInfoRepository;

    @Autowired
    private UserRoleRepository userRoleRepository;

    @Autowired
    private UserStatusRepository userStatusRepository;

    @Autowired
    private UserProfileRepository userProfileRepository;

    @Autowired
    private SecretQuestionRepository secretQuestionRepository;

    @Autowired
    private UserRecoveryInfoRepository userRecoveryInfoRepository;

    @Transactional(
            propagation = Propagation.REQUIRES_NEW,
            rollbackFor = {UserAlreadyExistException.class, UnknownException.class})
    public void signup(SignUpCommand dto) throws UserAlreadyExistException, IOException {
        LocalDateTime creationTime = LocalDateTime.now();

        if (userRepository.findUserByLogin(dto.getLogin()) != null) {
            throw new UserAlreadyExistException(USER_ALREADY_EXISTS_MESSAGE);
        }

        User user = new User(dto.getLogin(), creationTime);
        user = userRepository.save(user);

        UserRole userRole = new UserRole(user, dto.getLogin().equals("admin") ? UserRoleType.ADMIN : UserRoleType.USER, creationTime);
        userRoleRepository.save(userRole);

        UserStatus userStatus = new UserStatus(user, AccountStatus.ACTIVE, creationTime);
        userStatusRepository.save(userStatus);

        AuthenticationInfo authenticationInfo = new AuthenticationInfo(user, dto.getPassword(), creationTime);
        authenticationInfoRepository.save(authenticationInfo);

        SecretQuestion secretQuestion = secretQuestionRepository.getById(dto.getQuestionId());

        UserRecoveryInfo userRecoveryInfo = new UserRecoveryInfo(user, secretQuestion, dto.getAnswer(), creationTime);
        userRecoveryInfoRepository.save(userRecoveryInfo);

        UserProfile userProfile = new UserProfile();
        userProfile.setUser(user);
        userProfile.setFirstName(dto.getFirstName());
        userProfile.setLastName(dto.getLastName());

        byte[] avatar = dto.getAvatar().getBytes();
        if (avatar.length == 0) {
            userProfile.setAvatar(Files.readAllBytes(
                    Path.of(ResourceUtils.getFile("classpath:default-avatar.jpg").getAbsolutePath())));
        } else {
            userProfile.setAvatar(avatar);
        }

        userProfile.setGender(Gender.getGenderByDefinition(dto.getGender()));
        userProfile.setBirthday(LocalDate.parse(dto.getBirthday()));
        userProfile.setPhone(dto.getPhone());
        userProfile.setEmail(dto.getEmail());
        userProfile.setCountry(dto.getCountry());
        userProfile.setCity(dto.getCity());
        userProfile.setCreationTime(creationTime);
        userProfileRepository.save(userProfile);
    }
}
