package by.academy.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class UserPresentInfoDto {
    private String id;
    private String firstName;
    private String lastName;
    private String avatar;
}
