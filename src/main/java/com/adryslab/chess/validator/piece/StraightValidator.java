package com.adryslab.chess.validator.piece;

import com.adryslab.chess.model.Result;
import com.adryslab.chess.model.annotation.Singleton;
import com.adryslab.chess.model.cell.Cell;
import com.adryslab.chess.model.piece.Colour;
import com.adryslab.chess.resources.ErrorMessages;
import com.adryslab.chess.resources.Messages;

@Singleton
public class StraightValidator extends AbstractPieceValidator {

    private static AbstractPieceValidator instance;

    // no construct allowed -> Singleton pattern
    private StraightValidator() {
    }

    /*
     * Apply Double Checked Locking principle.
     * Singleton Pattern -> Lazy load.
     */
    static AbstractPieceValidator getInstance() {
        if(instance == null){
            synchronized (StraightValidator.class) {
                if(instance == null){
                    instance = new StraightValidator();
                }
            }
        }
        return instance;
    }

    /**
     * Method used to validate if the Straight (Vertical or horizontal) movement, from origin to destination, is correct or not.
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
