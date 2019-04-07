package com.adryslab.chess.validator.piece;

import com.adryslab.chess.model.Result;
import com.adryslab.chess.model.annotation.Singleton;
import com.adryslab.chess.model.cell.Cell;
import com.adryslab.chess.model.piece.Colour;
import com.adryslab.chess.resources.ErrorMessages;

@Singleton
public class PawnValidator extends AbstractPieceValidator implements TriValidator<Cell, Cell, Cell[][], String> {

    private static TriValidator<Cell, Cell, Cell[][], String> instance;

    // no construct allowed -> Singleton pattern
    private PawnValidator() {
    }

    /*
     * Apply Double Checked Locking principle.
     * Singleton Pattern -> Lazy load.
     */
    static TriValidator<Cell, Cell, Cell[][], String> getInstance() {
        if(instance == null){
            synchronized (PawnValidator.class) {
                if(instance == null){
                    instance = new PawnValidator();
                }
            }
        }
        return instance;
    }

    @Override
    public Result<String> validate(final Cell originCell, final Cell destinationCell, final Cell[][] board) {

        final Result<String> validationResult = super.commonValidate(originCell, destinationCell);

        if(validationResult.isFailure()) {
            return validationResult;
        }

        final Colour pieceColour = originCell.getSlot().getColour();

        final int yStart = originCell.getPosition().getY();
        final int yEnd = destinationCell.getPosition().getY();
        final int xStart = originCell.getPosition().getX();
        final int xEnd = destinationCell.getPosition().getX();

        final int yDifference = yStart - yEnd;
        final int xDifference = xStart - xEnd;

        if (pieceColour == Colour.WHITE) {
            if (outOfRange(0, 3, yDifference)) {
                return Result.failure(ErrorMessages.PAWN_INCORRECT_MOVE);
            } else if (outOfRange(-2, 2, xDifference)) {
                return Result.failure(ErrorMessages.PAWN_INCORRECT_MOVE);
            } else if (yDifference == 2 && yStart != 6) {
                return Result.failure(ErrorMessages.PAWN_INCORRECT_MOVE);
            } else if (xDifference == 0 && !board[yEnd][xEnd].getSlot().getCellContent().isEmpty()) {
                return Result.failure(ErrorMessages.PAWN_INCORRECT_MOVE);
            } else if ((xDifference == 1 || xDifference == -1)
                    && yDifference == 1
                    && (board[yEnd][xEnd].getSlot().getCellContent().isEmpty() ||  board[yEnd][xEnd].getSlot().getColour() == pieceColour)) {
                return Result.failure(ErrorMessages.PAWN_INCORRECT_MOVE);
            }
        } else {
            if (outOfRange(-3, 0, yDifference)) {
                return Result.failure(ErrorMessages.PAWN_INCORRECT_MOVE);
            } else if (outOfRange(-2, 2, xDifference)) {
                return Result.failure(ErrorMessages.PAWN_INCORRECT_MOVE);
            } else if (yDifference == -2 && yStart != 1) {
                return Result.failure(ErrorMessages.PAWN_INCORRECT_MOVE);
            } else if (xDifference == 0 && !board[yEnd][xEnd].getSlot().getCellContent().isEmpty()) {
                return Result.failure(ErrorMessages.PAWN_INCORRECT_MOVE);
            } else if ((xDifference == 1 || xDifference == -1)
                    && yDifference == -1
                    && (board[yEnd][xEnd].getSlot().getCellContent().isEmpty() || board[yEnd][xEnd].getSlot().getColour() == pieceColour)) {
                return Result.failure(ErrorMessages.PAWN_INCORRECT_MOVE);
            }
        }

        return Result.valid();
    }

    private boolean outOfRange(int min, int max, int value) {
        return value <= min || value >= max;
    }

}
