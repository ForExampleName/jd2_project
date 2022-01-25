package by.academy.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class UserDetailsDto {
    private String userId;
    private String userAvatar;
    private boolean isAuthenticated;
}
