package by.academy.validator.impl;

import by.academy.dto.PasswordCommand;
import by.academy.validator.Validator;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class PasswordValidatorImpl implements Validator<PasswordCommand> {
    @Override
    public Optional<String> validate(PasswordCommand data) {
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
        if (!password.matches("[\\da-zA-Z_]+")) {
            return Optional.of("Поле 'Пароль' должно содержать только латинские буквы и цифры, а также символ \"_\"");
        }

        String repeatPassword = data.getRepeatPassword();
        if (repeatPassword == null || repeatPassword.isBlank()) {
            return Optional.of("Поле 'Подтверждение пароля' должно быть заполнено");
        }
        if (repeatPassword.split(" ").length > 1) {
            return Optional.of("Поле 'Подтверждение пароля' не должно содержать символы пробела");
        }
        if (repeatPassword.length() < 8 || repeatPassword.length() > 255) {
            return Optional.of("Длина поля 'Подтверждение пароля' находится в диапазоне 8-255 символов");
        }
        if (!repeatPassword.matches("[\\da-zA-Z_]+")) {
            return Optional.of("Поле 'Подтверждение пароля' должно содержать только латинские буквы и цифры, а также символ \"_\"");
        }
        if (!password.equals(repeatPassword)) {
            return Optional.of("Введенные пароли не совпадают");
        }

        return Optional.empty();
    }
}
