package by.academy.validator.impl;

import by.academy.dto.SignUpCommand;
import by.academy.validator.Validator;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Optional;
import java.util.regex.Pattern;

@Component
public class SignUpValidatorImpl implements Validator<SignUpCommand> {
    @Override
    public Optional<String> validate(SignUpCommand data) {
        String firstName = data.getFirstName();
        if (firstName == null || firstName.isBlank()) {
            return Optional.of("Поле 'Имя' должно быть заполнено");
        }
        if (firstName.length() > 255) {
            return Optional.of("Длина поля 'Имя' не должно превышать 255 символов");
        }

        String lastName = data.getLastName();
        if (lastName == null || lastName.isBlank()) {
            return Optional.of("Поле 'Фамилия' должно быть заполнено");
        }
        if (lastName.length() > 255) {
            return Optional.of("Длина поля 'Фамилия' не должно превышать 255 символов");
        }

        String birthday = data.getBirthday();
        if (birthday == null || birthday.isBlank()) {
            return Optional.of("Поле 'Дата рождения' должно быть заполнено");
        }
        LocalDate birthdayDate = LocalDate.parse(birthday);
        LocalDate today = LocalDate.now();
        if (birthdayDate.isEqual(today) || birthdayDate.isAfter(today)) {
            return Optional.of("Недопустимое значение поля 'Дата рождения'");
        }

        String gender = data.getGender();
        if (gender == null || gender.isBlank()) {
            return Optional.of("Поле 'Пол' должно быть заполнено");
        }

        String email = data.getEmail();
        if (email == null || email.isBlank()) {
            return Optional.of("Поле 'E-mail' должно быть заполнено");
        }
        String pattern = "[a-zA-Z]+@[a-zA-Z]+\\.[a-zA-Z]+";
        if (!email.matches(pattern)) {
            return Optional.of("Формат поля 'E-mail' следующий: local-part@domain (напр. email@mail.ru)");
        }

        String phone = data.getPhone();
        if (phone == null || phone.isBlank()) {
            return Optional.of("Поле 'Телефон' должно быть заполнено");
        }
        pattern = "(\\+\\d{1,3}\\(\\d{2}\\))?\\d{3}-\\d{2}-\\d{2}";
        if (!phone.matches(pattern)) {
            return Optional.of("Формат поля 'Телефон' следующий: +xxx(yy)zzz-zz-zz или xxx-yy-xx");
        }

        String country = data.getCountry();
        if (country != null && !country.isEmpty()) {
            if (country.isBlank()) {
                return Optional.of("Поле 'Страна' не обязательно к заполнению, однако содержит только символы пробела");
            }
            if (country.length() > 255) {
                return Optional.of("Длина поля 'Страна' не должно превышать 255 символов");
            }
            Pattern anyNumberPattern = Pattern.compile("[0-9]+");
            if (anyNumberPattern.matcher(country).find()) {
                return Optional.of("Поле 'Страна' не должно содержать цифры");
            }
        }

        String city = data.getCity();
        if (city != null && !city.isEmpty()) {
            if (city.isBlank()) {
                return Optional.of("Поле 'Город' не обязательно к заполнению, однако содержит только символы пробела");
            }
            if (city.length() > 255) {
                return Optional.of("Длина поля 'Город' не должно превышать 255 символов");
            }
        }

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
        if (!login.matches("[\\da-zA-Z_]+")) {
            return Optional.of("Поле 'Логин' должно содержать только латинские буквы и цифры, а также символ \"_\"");
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
        if (!password.matches("[\\da-zA-Z_]+")) {
            return Optional.of("Поле 'Пароль' должно содержать только латинские буквы и цифры, а также символ \"_\"");
        }

        String repeatPassword = data.getRepeatPassword();
        if (repeatPassword == null || repeatPassword.isBlank()) {
            return Optional.of("Поле 'Повторите пароль' должно быть заполнено");
        }
        if (repeatPassword.split(" ").length > 1) {
            return Optional.of("Поле 'Повторите пароль' не должно содержать символы пробела");
        }
        if (repeatPassword.length() < 8 || repeatPassword.length() > 255) {
            return Optional.of("Длина поля 'Повторите пароль' находится в диапазоне 8-255 символов");
        }
        if (!repeatPassword.matches("[\\da-zA-Z_]+")) {
            return Optional.of("Поле 'Пароль' должно содержать только латинские буквы и цифры, а также символ \"_\"");
        }
        if (!password.equals(repeatPassword)) {
            return Optional.of("Введенные пароли не совпадают");
        }

        Long questionId = data.getQuestionId();
        if (questionId == null) {
            return Optional.of("Поле 'Секретный вопрос' должно быть заполнено");
        }

        String answer = data.getAnswer();
        if (answer == null || answer.isBlank()) {
            return Optional.of("Поле 'Ответ' должно быть заполнено");
        }

        return Optional.empty();
    }
}
