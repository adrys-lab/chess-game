package com.adryslab.chess.validator.composed;

import com.adryslab.chess.model.Player;
import com.adryslab.chess.model.cell.Cell;

/**
 * Factory Pattern.
 * Provides instances of BiValidator<Cell, Player, String> --> from same package.
 */
public class ComposedValidatorProvider {

    //no construct allowed
    private ComposedValidatorProvider() {
    }

    public static BiValidator<Cell, Player, String> getPlayerColourValidator() {
        return PlayerMatchValidator.getInstance();
    }

    public static BiValidator<Cell, Player, String> getPlayerInTurnValidator() {
        return PlayerInTurnValidator.getInstance();
    }

}
