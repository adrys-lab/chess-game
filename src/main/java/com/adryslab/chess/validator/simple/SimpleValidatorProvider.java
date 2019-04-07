package com.adryslab.chess.validator.simple;

import com.adryslab.chess.model.cell.Cell;

/**
 * Factory Pattern.
 * Provides instances of Validator<?, ?> --> from same package.
 */
public class SimpleValidatorProvider {

    //no construct allowed
    private SimpleValidatorProvider() {
    }

    public static Validator<String, String> getEnteredPositionValidator() {
        return ValidEnteredCharsValidator.getInstance();
    }

    public static Validator<Cell, String> getEmptyPositionValidator() {
        return EmptyPositionValidator.getInstance();
    }
}
