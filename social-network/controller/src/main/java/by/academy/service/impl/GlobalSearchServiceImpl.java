package by.academy.service.impl;

import by.academy.dto.UserPresentInfoDto;
import by.academy.enums.UserRoleType;
import by.academy.repository.UserProfileRepository;
import by.academy.repository.UserRepository;
import by.academy.repository.UserRoleRepository;
import by.academy.repository.UserStatusRepository;
import by.academy.service.GlobalSearchService;
import by.academy.session.UserDetails;
import by.academy.util.BlobPictureUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class GlobalSearchServiceImpl implements GlobalSearchService {
    @Autowired
    private UserDetails userDetails;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserProfileRepository userProfileRepository;

    @Autowired
    private UserStatusRepository userStatusRepository;

    @Autowired
    private UserRoleRepository userRoleRepository;

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW, readOnly = true)
    public List<UserPresentInfoDto> searchAllUsers() {
        List<UserPresentInfoDto> result = new ArrayList<>();
        List<String> userIds;

        boolean iAmAdmin
                = userRoleRepository.findCurrentUserRoleByUserId(userDetails.getUserId()).getRole() == UserRoleType.ADMIN;

        if (iAmAdmin) {
            userIds = userRepository.findAllUserIdsExceptUserId(userDetails.getUserId());
        } else {
            userIds = userStatusRepository.findAllActiveUserAccountIdsExceptUserId(userDetails.getUserId());
        }

        List<Object[]> usersInfo = userProfileRepository.searchUsersByIds(userIds);
        for (Object[] inst : usersInfo) {
            UserPresentInfoDto userInfo = new UserPresentInfoDto();
            userInfo.setId((String) inst[0]);
            userInfo.setFirstName((String) inst[1]);
            userInfo.setLastName((String) inst[2]);
            userInfo.setAvatar(BlobPictureUtil.getJspImageSrcUsingByteArray((byte[]) inst[3]));
            result.add(userInfo);
        }
        return result;
    }
}
