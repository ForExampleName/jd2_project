package by.academy.controller;

import by.academy.dto.LoginCommand;
import by.academy.dto.UserDetailsDto;
import by.academy.exceptions.IncorrectUserPasswordException;
import by.academy.exceptions.UserNotFoundException;
import by.academy.service.LoginService;
import by.academy.session.UserDetails;
import by.academy.validator.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Optional;

@Controller
public class LoginController {
    @Autowired
    private UserDetails userDetails;

    @Autowired
    @Qualifier("loginValidatorImpl")
    private Validator<LoginCommand> loginValidator;

    @Autowired
    private LoginService loginService;

    @GetMapping(path = "/login.html")
    public String getLoginPage(HttpSession session, ModelMap model) {
        if (userDetails.isAuthenticated()) {
            return "redirect:/my_page.do";
        }

        model.addAttribute("loginCommand", new LoginCommand());
        return "login";
    }

    @PostMapping(path = "/login.do")
    public String login(@ModelAttribute("loginCommand") LoginCommand loginCommand, ModelMap model) throws IOException {
        Optional<String> validationError = loginValidator.validate(loginCommand);
        if (validationError.isPresent()) {
            model.addAttribute("error", validationError.get());
            return "login";
        }

        UserDetailsDto userDetailsDto;
        try {
            userDetailsDto = loginService.login(loginCommand);
        } catch (UserNotFoundException | IncorrectUserPasswordException e) {
            model.addAttribute("error", e.getMessage());
            return "login";
        }

        userDetailsDto.setAuthenticated(true);
        userDetails.applyData(userDetailsDto);

        return "redirect:/my_page.do";
    }
}
