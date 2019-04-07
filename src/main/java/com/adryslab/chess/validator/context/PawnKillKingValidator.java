package com.adryslab.chess.validator.context;

import com.adryslab.chess.model.Result;
import com.adryslab.chess.model.annotation.Singleton;
import com.adryslab.chess.model.cell.Cell;
import com.adryslab.chess.model.piece.Colour;
import com.adryslab.chess.resources.Messages;
import com.adryslab.chess.validator.composed.BiValidator;

@Singleton
public class PawnKillKingValidator extends AbstractKillKingValidator implements BiValidator<Cell, Cell[][], String>  {

    private static BiValidator<Cell, Cell[][], String> instance;

    // no construct allowed -> Singleton pattern
    private PawnKillKingValidator() {
    }

    /*
     * Apply Double Checked Locking principle.
     * Singleton Pattern -> Lazy load.
     */
    static BiValidator<Cell, Cell[][], String> getInstance() {
        if(instance == null){
            synchronized (PawnKillKingValidator.class) {
                if(instance == null){
                    instance = new PawnKillKingValidator();
                }
            }
        }
        return instance;
    }

    public Result<String> validate(final Cell originCell, final Cell[][] board) {

        final int xStart = originCell.getPosition().getX();
        final int yStart = originCell.getPosition().getY();

        final Colour pieceColour = originCell.getSlot().getColour();

        if (pieceColour == Colour.WHITE) {
            if ((areInRange(board, yStart + 1, xStart + 1) && isKingAgainst(board[yStart + 1][xStart + 1], pieceColour))
                    || (areInRange(board, yStart + 1, xStart - 1) && isKingAgainst(board[yStart + 1][xStart - 1], pieceColour))) {
                return Result.valid(String.format(Messages.CHECK, board[yStart][xStart].getSlot().getCellContent().getName()));
            }
        } else if ((areInRange(board, yStart - 1, xStart + 1) && isKingAgainst(board[yStart - 1][xStart + 1], pieceColour))
                    || (areInRange(board, yStart - 1, xStart - 1) && isKingAgainst(board[yStart - 1][xStart - 1], pieceColour))) {
                return Result.valid(String.format(Messages.CHECK, board[yStart][xStart].getSlot().getCellContent().getName()));
        }

        return Result.failure();
    }


}
