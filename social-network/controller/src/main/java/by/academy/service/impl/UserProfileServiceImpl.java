package by.academy.service.impl;

import by.academy.dto.UserPageProfileDto;
import by.academy.pojo.profile.UserProfile;
import by.academy.repository.UserProfileRepository;
import by.academy.service.UserProfileService;
import by.academy.session.UserDetails;
import by.academy.util.BlobPictureUtil;
import by.academy.util.MonthUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.Month;

@Service
public class UserProfileServiceImpl implements UserProfileService {
    private static final String EMPTY_PROFILE_FIELD = "Не указано";

    @Autowired
    private UserDetails userDetails;

    @Autowired
    private UserProfileRepository userProfileRepository;

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW, readOnly = true)
    public UserPageProfileDto getUserPageProfileDto(String userId) {
        boolean myPage = userDetails.getUserId().equals(userId);
        UserPageProfileDto profileDto = new UserPageProfileDto();

        UserProfile userProfile = userProfileRepository.findCurrentUserProfileByUserId(userId);
        profileDto.setId(userProfile.getUser().getId());
        profileDto.setFirstName(userProfile.getFirstName());
        profileDto.setLastName(userProfile.getLastName());

        if (myPage) {
            profileDto.setAvatar(userDetails.getUserAvatar());
        } else {
            profileDto.setAvatar(BlobPictureUtil.getJspImageSrcUsingByteArray(userProfile.getAvatar()));
        }

        int birthdayDay = userProfile.getBirthday().getDayOfMonth();
        Month birthdayMonth = userProfile.getBirthday().getMonth();
        profileDto.setBirthday(birthdayDay + " " + MonthUtil.getRussianMonthRepresentation(birthdayMonth));

        profileDto.setPhone(userProfile.getPhone());
        profileDto.setEmail(userProfile.getEmail());
        String country = userProfile.getCountry();
        profileDto.setCountry(country.isEmpty() ? EMPTY_PROFILE_FIELD : country);
        String city = userProfile.getCity();
        profileDto.setCity(city.isEmpty() ? EMPTY_PROFILE_FIELD : city);

        return profileDto;
    }
}
