package by.academy.session;

import by.academy.dto.UserDetailsDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

@NoArgsConstructor
@Getter
@Setter
@Component(value = "userDetails")
@SessionScope
public class UserDetails {
    private String userId;
    private String userAvatar;
    private boolean isAuthenticated;

    public void applyData(UserDetailsDto data) {
        this.userId = data.getUserId();
        this.userAvatar = data.getUserAvatar();
        this.isAuthenticated = data.isAuthenticated();
    }
}
