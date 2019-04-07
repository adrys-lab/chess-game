package com.adryslab.chess.validator.piece;

import com.adryslab.chess.model.Result;

@FunctionalInterface
public interface TriValidator<I, P, T, O> {

    Result<O> validate(final I value, final P secondValue, final T thirdValue);
}
