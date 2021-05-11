package com.game.validator;

import com.game.exception.ValidationException;

import java.util.List;
import java.util.StringJoiner;

public interface Validator<ENTITY> {

    List<String> validate(ENTITY entity);

    default void throwIfNotValid(ENTITY entity) {
        List<String> errors = validate(entity);
        if (!errors.isEmpty()) {
            StringJoiner stringJoiner = new StringJoiner("\n");
            errors.forEach(stringJoiner::add);
            throw new ValidationException(stringJoiner.toString());
        }
    }
}
