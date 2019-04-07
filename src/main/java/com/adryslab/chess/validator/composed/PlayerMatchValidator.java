package com.adryslab.chess.validator.composed;

import com.adryslab.chess.model.Player;
import com.adryslab.chess.model.Result;
import com.adryslab.chess.model.annotation.Singleton;
import com.adryslab.chess.model.cell.Cell;
import com.adryslab.chess.resources.ErrorMessages;

@Singleton
public class PlayerMatchValidator implements BiValidator<Cell, Player, String> {

    private static BiValidator<Cell, Player, String> instance;

    // no construct allowed -> Singleton pattern
    private PlayerMatchValidator() {
    }

    /*
     * Apply Double Checked Locking principle.
     * Singleton Pattern -> Lazy load.
     */
    static BiValidator<Cell, Player, String> getInstance() {
        if(instance == null){
            synchronized (PlayerMatchValidator.class) {
                if(instance == null){
                    instance = new PlayerMatchValidator();
                }
            }
        }
        return instance;
    }

    /**
     *
     * Validates that the colour of the selected piece, match with the player colour.
     * @param cell selected to be moved
     * @param player which wants to move
     * @return the result if valid or failure with the message
     */
    @Override
    public Result<String> validate(final Cell cell, final Player player) {

        if(cell.getSlot().getColour() != player.getColour()) {
            return Result.failure(String.format(ErrorMessages.INTRODUCED_MOVEMENT_COLOUR_NO_MATCH,
                    cell.getSlot().getCellContent().getName(),
                    cell.getSlot().getColour(),
                    player.getName(),
                    player.getColour().name()));
        }

        return Result.valid();
    }
}
