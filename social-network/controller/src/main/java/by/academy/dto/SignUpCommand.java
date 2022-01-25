package by.academy.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@NoArgsConstructor
@Getter
@Setter
public class SignUpCommand {
    private String firstName;
    private String lastName;
    private String birthday;
    private String gender;
    private String email;
    private String phone;
    private MultipartFile avatar;
    private String country;
    private String city;
    private String login;
    private String password;
    private String repeatPassword;
    private Long questionId;
    private String answer;
}
