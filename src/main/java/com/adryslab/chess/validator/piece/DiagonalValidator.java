package com.adryslab.chess.validator.piece;

import com.adryslab.chess.model.Result;
import com.adryslab.chess.model.annotation.Singleton;
import com.adryslab.chess.model.cell.Cell;
import com.adryslab.chess.model.piece.Colour;
import com.adryslab.chess.resources.ErrorMessages;
import com.adryslab.chess.resources.Messages;

@Singleton
public class DiagonalValidator extends AbstractPieceValidator implements TriValidator<Cell, Cell, Cell[][], String> {

    private static TriValidator<Cell, Cell, Cell[][], String> instance;

    // no construct allowed -> Singleton pattern
    private DiagonalValidator() {
    }

    /*
     * Apply Double Checked Locking principle.
     * Singleton Pattern -> Lazy load.
     */
    static TriValidator<Cell, Cell, Cell[][], String> getInstance() {
        if(instance == null){
            synchronized (DiagonalValidator.class) {
                if(instance == null){
                    instance = new DiagonalValidator();
                }
            }
        }
        return instance;
    }

    /**
     * Method used to validate if the diagonal movement, from origin to destination, is correct or not.
     *
     * @param originCell starting cell
     * @param destinationCell destination cell
     * @param board board representation
     * @return valid of failure and message
     */
    @Override
    public Result<String> validate(final Cell originCell, final Cell destinationCell, final Cell[][] board) {

        final Result<String> validationResult = super.commonValidate(originCell, destinationCell);

        if(validationResult.isFailure()) {
            return validationResult;
        }

        final Colour pieceColour = originCell.getSlot().getColour();

        int yStart = originCell.getPosition().getY();
        int xStart = originCell.getPosition().getX();

        if (validateLinear((i) -> ++i, (j) -> ++j, yStart + 1, xStart + 1, board, destinationCell, pieceColour).isValid()
                || validateLinear((i) -> --i, (j) -> ++j, yStart - 1, xStart + 1, board, destinationCell, pieceColour).isValid()
                || validateLinear((i) -> ++i, (j) -> --j, yStart + 1, xStart - 1, board, destinationCell, pieceColour).isValid()
                || validateLinear((i) -> --i, (j) -> --j, yStart - 1, xStart - 1, board, destinationCell, pieceColour).isValid()) {
            return Result.valid();
        } else {
            return Result.failure(String.format(ErrorMessages.DIAGONAL_INCORRECT_MOVE, originCell.getSlot().getCellContent().getName()));
        }
    }

    /**
     * Method used to check if the given piece with its respective allowed movements, can find a king to kill --> used to CHECK strategy
     *
     * @param cell current piece cell
     * @param board board representation
     * @return valid of failure and message
     */
    @Override
    public Result<String> validate(final Cell cell, final Cell[][] board) {
        final Colour pieceColour = cell.getSlot().getColour();

        int yStart = cell.getPosition().getY();
        int xStart = cell.getPosition().getX();

        if (findKing((i) -> ++i, (j) -> ++j, yStart + 1, xStart + 1, board, pieceColour).isValid()
                || findKing((i) -> --i, (j) -> ++j, yStart - 1, xStart + 1, board, pieceColour).isValid()
                || findKing((i) -> ++i, (j) -> --j, yStart + 1, xStart - 1, board, pieceColour).isValid()
                || findKing((i) -> --i, (j) -> --j, yStart - 1, xStart - 1, board, pieceColour).isValid()) {
            return Result.valid(String.format(Messages.CHECK, board[yStart][xStart].getSlot().getCellContent().getName()));
        } else {
            return Result.failure();
        }
    }
}
