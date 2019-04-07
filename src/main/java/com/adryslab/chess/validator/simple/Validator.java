package com.adryslab.chess.validator.simple;

import com.adryslab.chess.model.Result;

@FunctionalInterface
public interface Validator<I, O> {

    Result<O> validate(I value);
}
