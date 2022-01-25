package by.academy.service.impl;

import by.academy.dto.UserPageAdminDto;
import by.academy.enums.AccountStatus;
import by.academy.enums.UserRoleType;
import by.academy.repository.UserRoleRepository;
import by.academy.repository.UserStatusRepository;
import by.academy.service.AdminService;
import by.academy.session.UserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    private UserDetails userDetails;

    @Autowired
    private UserRoleRepository userRoleRepository;

    @Autowired
    private UserStatusRepository userStatusRepository;

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW, readOnly = true)
    public boolean isAdmin(String userId) {
        return userRoleRepository.findCurrentUserRoleByUserId(userId).getRole() == UserRoleType.ADMIN;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW, readOnly = true)
    public UserPageAdminDto getUserPageAdminDto(String userId) {
        UserPageAdminDto adminDto = new UserPageAdminDto();
        boolean itsMe = userId.equals(userDetails.getUserId());
        if (itsMe) {
            return adminDto;
        }
        boolean iAmAdmin = isAdmin(userDetails.getUserId());
        adminDto.setShowAdminPanel(iAmAdmin);
        if (iAmAdmin) {
            adminDto.setUserBlocked(
                    userStatusRepository.findCurrentAccountStatusByUserId(userId) == AccountStatus.BLOCKED
            );
        }
        return adminDto;
    }
}
