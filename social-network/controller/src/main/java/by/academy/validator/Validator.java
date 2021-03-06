package by.academy.validator;

import java.util.Optional;

public interface Validator<T> {
    Optional<String> validate(T toValidate);
}
