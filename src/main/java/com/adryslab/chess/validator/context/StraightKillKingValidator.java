package com.adryslab.chess.validator.context;

import com.adryslab.chess.model.Result;
import com.adryslab.chess.model.annotation.Singleton;
import com.adryslab.chess.model.cell.Cell;
import com.adryslab.chess.model.piece.Colour;
import com.adryslab.chess.resources.Messages;
import com.adryslab.chess.validator.composed.BiValidator;

@Singleton
public class StraightKillKingValidator extends AbstractKillKingValidator implements BiValidator<Cell, Cell[][], String>  {

    private static BiValidator<Cell, Cell[][], String> instance;

    // no construct allowed -> Singleton pattern
    private StraightKillKingValidator() {
    }

    /*
     * Apply Double Checked Locking principle.
     * Singleton Pattern -> Lazy load.
     */
    static BiValidator<Cell, Cell[][], String> getInstance() {
        if(instance == null){
            synchronized (StraightKillKingValidator.class) {
                if(instance == null){
                    instance = new StraightKillKingValidator();
                }
            }
        }
        return instance;
    }

    public Result<String> validate(final Cell originCell, final Cell[][] board) {

        final Colour pieceColour = originCell.getSlot().getColour();

        int yStart = originCell.getPosition().getY();
        int xStart = originCell.getPosition().getX();

        if (findKing((i) -> i, (j) -> ++j, yStart, xStart + 1, board, pieceColour).isValid()
                || findKing((i) -> i, (j) -> --j, yStart, xStart - 1, board, pieceColour).isValid()
                || findKing((i) -> ++i, (j) -> j, yStart + 1, xStart, board, pieceColour).isValid()
                || findKing((i) -> --i, (j) -> j, yStart - 1, xStart, board, pieceColour).isValid()) {
            return Result.valid(String.format(Messages.CHECK, board[yStart][xStart].getSlot().getCellContent().getName()));
        } else {
            return Result.failure();
        }
    }


}
