package by.academy.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class UserPageAdminDto {
    private boolean showAdminPanel;
    private boolean userBlocked;
}
