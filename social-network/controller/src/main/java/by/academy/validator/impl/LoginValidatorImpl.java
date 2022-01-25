package by.academy.validator.impl;

import by.academy.dto.LoginCommand;
import by.academy.validator.Validator;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class LoginValidatorImpl implements Validator<LoginCommand> {
    @Override
    public Optional<String> validate(LoginCommand data) {
        String login = data.getLogin();
        if (login == null || login.isBlank()) {
            return Optional.of("Поле 'Логин' должно быть заполнено");
        }
        if (login.split(" ").length > 1) {
            return Optional.of("Поле 'Логин' не должно содержать символы пробела");
        }
        if (login.length() > 255) {
            return Optional.of("Длина поля 'Логин' не должна превышать 255 символов");
        }

        String password = data.getPassword();
        if (password == null || password.isBlank()) {
            return Optional.of("Поле 'Пароль' должно быть заполнено");
        }
        if (password.split(" ").length > 1) {
            return Optional.of("Поле 'Пароль' не должно содержать символы пробела");
        }
        if (password.length() < 8 || password.length() > 255) {
            return Optional.of("Длина поля 'Пароль' находится в диапазоне 8-255 символов");
        }

        return Optional.empty();
    }
}
