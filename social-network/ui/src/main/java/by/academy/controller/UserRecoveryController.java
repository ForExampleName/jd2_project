package by.academy.controller;

import by.academy.dto.PasswordCommand;
import by.academy.pojo.account.User;
import by.academy.service.AuthenticationInfoService;
import by.academy.service.UserRecoveryInfoService;
import by.academy.service.UserService;
import by.academy.validator.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;
import java.util.Optional;

@Controller
public class UserRecoveryController {
    private static final String USER_NOT_FOUND_MESSAGE = "Пользователь с таким логином не найден";
    private static final String UNCORRECT_ANSWER_MESSAGE = "Неправильный ответ. Попробуйте еще раз";

    @Autowired
    private UserService userService;

    @Autowired
    @Qualifier("passwordValidatorImpl")
    private Validator<PasswordCommand> validator;

    @Autowired
    private UserRecoveryInfoService userRecoveryInfoService;

    @Autowired
    private AuthenticationInfoService authenticationInfoService;

    @GetMapping("/user_recovery/first_phase.html")
    public String getLoginRecoveryPage(ModelMap model) {
        model.addAttribute("login", "");
        return "login_validate";
    }

    @PostMapping("/user_recovery/first_phase.do")
    public String loginRecovery(@ModelAttribute("login") String login, ModelMap model, HttpSession session) {
        User user = userService.findUserByLogin(login);
        if (user == null) {
            model.addAttribute("error", USER_NOT_FOUND_MESSAGE);
            return "login_validate";
        }
        session.setAttribute("recovery", Boolean.TRUE);
        session.setAttribute("user", user);

        return "redirect:/user_recovery/second_phase.html";
    }

    @GetMapping("/user_recovery/second_phase.html")
    public String getQuestionRecoveryPage(ModelMap model, HttpSession session) {
        User user = (User) session.getAttribute("user");
        String question = userRecoveryInfoService.findSecretQuestionByUserId(user.getId());
        model.addAttribute("question", question);
        model.addAttribute("answer", "");
        return "question_validate";
    }

    @PostMapping("/user_recovery/second_phase.do")
    public String questionRecovery(@ModelAttribute("answer") String answer, ModelMap model, HttpSession session) {
        User user = (User) session.getAttribute("user");

        String trueAnswer = userRecoveryInfoService.findAnswerByUserId(user.getId());
        if (!trueAnswer.equals(answer)) {
            model.addAttribute("error", UNCORRECT_ANSWER_MESSAGE);
            return "question_validate";
        }

        return "redirect:/user_recovery/change_password.html";
    }

    @GetMapping("/user_recovery/change_password.html")
    public String getChangePasswordPage(ModelMap model) {
        model.addAttribute("passwordCommand", new PasswordCommand());
        return "change_password";
    }

    @PostMapping("/user_recovery/change_password.do")
    public String changePassword(@ModelAttribute("passwordCommand") PasswordCommand passwordCommand, ModelMap model, HttpSession session) {
        Optional<String> validationError = validator.validate(passwordCommand);
        if (validationError.isPresent()) {
            model.addAttribute("error", validationError.get());
            return "change_password";
        }

        authenticationInfoService.saveNewPassword((User) session.getAttribute("user"), passwordCommand.getPassword());

        session.removeAttribute("user");
        session.removeAttribute("recovery");

        return "redirect:/login.html";
    }
}
