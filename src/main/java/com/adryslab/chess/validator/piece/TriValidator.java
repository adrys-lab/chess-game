package com.adryslab.chess.validator.piece;

import com.adryslab.chess.model.Result;
import com.adryslab.chess.validator.composed.BiValidator;

public interface TriValidator<I, P, T, O>  extends BiValidator<I, T, O> {

    Result<O> validate(final I value, final P secondValue, final T thirdValue);
}
