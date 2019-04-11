package com.adryslab.chess.validator.piece;

import com.adryslab.chess.model.Result;

/**
 * Validator interface which has three inputs and returns one.
 * @param <I> first input
 * @param <P> second input
 * @param <T> third input
 * @param <O> output
 */
public interface TriValidator<I, P, T, O>  {

    Result<O> validate(final I value, final P secondValue, final T thirdValue);
}
