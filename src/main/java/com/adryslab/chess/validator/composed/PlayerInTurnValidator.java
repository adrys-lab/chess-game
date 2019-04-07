package com.adryslab.chess.validator.composed;

import com.adryslab.chess.model.Player;
import com.adryslab.chess.model.Result;
import com.adryslab.chess.model.annotation.Singleton;
import com.adryslab.chess.model.cell.Cell;
import com.adryslab.chess.resources.ErrorMessages;

@Singleton
public class PlayerInTurnValidator implements BiValidator<Cell, Player, String> {

    private static BiValidator<Cell, Player, String> instance;

    // no construct allowed -> Singleton pattern
    private PlayerInTurnValidator() {
    }

    /*
     * Apply Double Checked Locking principle.
     * Singleton Pattern -> Lazy load.
     */
    static BiValidator<Cell, Player, String> getInstance() {
        if(instance == null){
            synchronized (PlayerInTurnValidator.class) {
                if(instance == null){
                    instance = new PlayerInTurnValidator();
                }
            }
        }
        return instance;
    }

    /**
     * Validates that the player which moves is in turn to move.
     * @param cell selected to be moved
     * @param player which wants to move
     * @return the result if valid or failure with the message
     */
    @Override
    public Result<String> validate(final Cell cell, final Player player) {
        if(!player.isInTurn()) {
            return Result.failure(ErrorMessages.INTRODUCED_MOVEMENT_PLAYER_IS_NOT_IN_TURN);
        }

        return Result.valid();
    }
}
