package com.adryslab.chess.validator.composed;

import com.adryslab.chess.model.Result;

/**
 * Validator interface which has two inputs and returns one.
 * @param <I> first input
 * @param <P> second input
 * @param <O> output
 */
@FunctionalInterface
public interface BiValidator<I, P, O> {

    /**
     * Returns the result of validate the 2 given parameters.
     */
    Result<O> validate(final I value, final P secondValue);
}
