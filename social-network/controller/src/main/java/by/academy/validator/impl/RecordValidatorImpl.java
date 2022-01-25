package by.academy.validator.impl;

import by.academy.dto.RecordCommand;
import by.academy.validator.Validator;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class RecordValidatorImpl implements Validator<RecordCommand> {
    @Override
    public Optional<String> validate(RecordCommand data) {
        String header = data.getHeader();
        if (header == null || header.isBlank()) {
            return Optional.of("Поле 'Заголовок' должно быть заполнено");
        }
        if (header.length() > 255) {
            return Optional.of("Длина поля 'Заголовок' не должна превышать 255 символов");
        }

        String description = data.getDescription();
        if (description == null || description.isBlank()) {
            return Optional.of("Поле 'Основное содержание' должно быть заполнено");
        }
        if (description.length() > 255) {
            return Optional.of("Длина поля 'Основное содержание' не должна превышать 255 символов");
        }
        return Optional.empty();
    }
}
