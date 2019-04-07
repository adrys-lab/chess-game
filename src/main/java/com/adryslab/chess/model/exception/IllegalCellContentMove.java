package com.adryslab.chess.model.exception;

/**
 * Not much exceptions are needed in that game,
 * probably game can get rid of this exception too,
 * but in some cases is better to control logics --> almost impossible to happen because of validations.
 */
public class IllegalCellContentMove extends IllegalArgumentException {

    public IllegalCellContentMove(final String message) {
        super(message);
    }
}
