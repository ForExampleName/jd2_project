package by.academy.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class UserPageProfileDto {
    private String id;
    private String firstName;
    private String lastName;
    private String avatar;
    private String birthday;
    private String phone;
    private String email;
    private String country;
    private String city;
}
