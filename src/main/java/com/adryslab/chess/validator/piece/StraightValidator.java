package com.adryslab.chess.validator.piece;

import com.adryslab.chess.model.Result;
import com.adryslab.chess.model.annotation.Singleton;
import com.adryslab.chess.model.cell.Cell;
import com.adryslab.chess.model.piece.Colour;
import com.adryslab.chess.resources.ErrorMessages;

@Singleton
public class StraightValidator extends AbstractPieceValidator implements TriValidator<Cell, Cell, Cell[][], String> {

    private static TriValidator<Cell, Cell, Cell[][], String> instance;

    // no construct allowed -> Singleton pattern
    private StraightValidator() {
    }

    /*
     * Apply Double Checked Locking principle.
     * Singleton Pattern -> Lazy load.
     */
    static TriValidator<Cell, Cell, Cell[][], String> getInstance() {
        if(instance == null){
            synchronized (StraightValidator.class) {
                if(instance == null){
                    instance = new StraightValidator();
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

        int yStart = originCell.getPosition().getY();
        int xStart = originCell.getPosition().getX();

        //check 4 Straight movements, checking if there's any piece in the middle.

        if (validateLinear((i) -> i, (j) -> ++j, yStart, xStart + 1, board, destinationCell, pieceColour).isValid()
                || validateLinear((i) -> i, (j) -> --j, yStart, xStart - 1, board, destinationCell, pieceColour).isValid()
                || validateLinear((i) -> ++i, (j) -> j, yStart + 1, xStart, board, destinationCell, pieceColour).isValid()
                || validateLinear((i) -> --i, (j) -> j, yStart - 1, xStart, board, destinationCell, pieceColour).isValid()) {
            return Result.valid();
        } else {
            return Result.failure(String.format(ErrorMessages.STRAIGHT_INCORRECT_MOVE, originCell.getSlot().getCellContent().getName()));
        }
    }

}
