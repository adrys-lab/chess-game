package com.adryslab.chess.validator.piece;

import com.adryslab.chess.model.Result;
import com.adryslab.chess.model.annotation.Singleton;
import com.adryslab.chess.model.cell.Cell;
import com.adryslab.chess.resources.ErrorMessages;

@Singleton
public class KnightValidator extends AbstractPieceValidator implements TriValidator<Cell, Cell, Cell[][], String> {

    private static TriValidator<Cell, Cell, Cell[][], String> instance;

    // no construct allowed -> Singleton pattern
    private KnightValidator() {
    }

    /*
     * Apply Double Checked Locking principle.
     * Singleton Pattern -> Lazy load.
     */
    static TriValidator<Cell, Cell, Cell[][], String> getInstance() {
        if(instance == null){
            synchronized (KnightValidator.class) {
                if(instance == null){
                    instance = new KnightValidator();
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

        final int yStart = originCell.getPosition().getY();
        final int yEnd = destinationCell.getPosition().getY();
        final int xStart = originCell.getPosition().getX();
        final int xEnd = destinationCell.getPosition().getX();

        final int yDifference = yStart - yEnd;
        final int xDifference = xStart - xEnd;

        if ((yDifference == -2 || yDifference == 2) && (xDifference == -1 || xDifference == 1)
            || (yDifference == -1 || yDifference == 1) && (xDifference == -2 || xDifference == 2)) {
            return Result.valid();
        } else {
            return Result.failure(ErrorMessages.KNIGHT_INCORRECT_MOVE);
        }
    }
}