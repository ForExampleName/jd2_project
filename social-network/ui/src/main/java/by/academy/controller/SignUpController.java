package by.academy.controller;

import by.academy.dto.SignUpCommand;
import by.academy.exceptions.UserAlreadyExistException;
import by.academy.pojo.account.SecretQuestion;
import by.academy.service.SecretQuestionService;
import by.academy.service.SignUpService;
import by.academy.validator.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Controller
public class SignUpController {
    @Autowired
    @Qualifier("signUpValidatorImpl")
    private Validator<SignUpCommand> validator;

    @Autowired
    private SignUpService signUpService;

    @Autowired
    private SecretQuestionService secretQuestionService;

    @GetMapping(path = "/signup.html")
    public String getSignUpPage(ModelMap model) {
        List<SecretQuestion> questions = secretQuestionService.findAll();
        model.addAttribute("questions", questions);
        model.addAttribute("signUpCommand", new SignUpCommand());
        return "signup";
    }

    @PostMapping(path = "/signup.do")
    public String executeSignUp(@ModelAttribute("signUpCommand") SignUpCommand signUpCommand, ModelMap model) throws IOException {
        Optional<String> validationError = validator.validate(signUpCommand);
        if (validationError.isPresent()) {
            model.addAttribute("error", validationError.get());
            return "signup";
        } else {
            try {
                signUpService.signup(signUpCommand);
            } catch (UserAlreadyExistException e) {
                model.addAttribute("error", e.getMessage());
                return "signup";
            }
        }
        return "redirect:/login.html";
    }
}
