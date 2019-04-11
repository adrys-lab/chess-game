package com.adryslab.chess.validator.piece;

import com.adryslab.chess.model.Result;
import com.adryslab.chess.model.annotation.Singleton;
import com.adryslab.chess.model.cell.Cell;
import com.adryslab.chess.model.piece.Colour;
import com.adryslab.chess.resources.ErrorMessages;
import com.adryslab.chess.resources.Messages;

@Singleton
public class KingValidator extends AbstractPieceValidator {

    private static AbstractPieceValidator instance;

    // no construct allowed -> Singleton pattern
    private KingValidator() {
    }

    /*
     * Apply Double Checked Locking principle.
     * Singleton Pattern -> Lazy load.
     */
    static AbstractPieceValidator getInstance() {
        if(instance == null){
            synchronized (KingValidator.class) {
                if(instance == null){
                    instance = new KingValidator();
                }
            }
        }
        return instance;
    }

    /**
     * Method used to validate if the king movement, from origin to destination, is correct or not.
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

        final int yStart = originCell.getPosition().getY();
        final int yEnd = destinationCell.getPosition().getY();
        final int xStart = originCell.getPosition().getX();
        final int xEnd = destinationCell.getPosition().getX();

        final int yDifference = yStart - yEnd;
        final int xDifference = xStart - xEnd;

        final Colour pieceColour = originCell.getSlot().getColour();

        if (((yDifference == -1 || yDifference == 1) && (xDifference == -1 || xDifference == 1)
                || ((yDifference == -1 || yDifference == 1) && xDifference == 0)
                || (yDifference == 0) && (xDifference == -1 || xDifference == 1))
                && (board[yEnd][xEnd].getSlot().getColour() != pieceColour)) {
            return Result.valid();
        } else {
            return Result.failure(ErrorMessages.KING_INCORRECT_MOVE);
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
    public Result<String> validate(Cell cell, final Cell[][] board) {
        final int yStart = cell.getPosition().getY();
        final int xStart = cell.getPosition().getX();

        final Colour pieceColour = cell.getSlot().getColour();

        if ((areInRange(board, yStart + 1, xStart) && isKingAgainst(board[yStart + 1][xStart], pieceColour))
                || (areInRange(board, yStart - 1, xStart) && isKingAgainst(board[yStart - 1][xStart], pieceColour))
                || (areInRange(board, yStart, xStart + 1) && isKingAgainst(board[yStart][xStart + 1], pieceColour))
                || (areInRange(board, yStart, xStart - 1) && isKingAgainst(board[yStart][xStart - 1], pieceColour))
                || (areInRange(board, yStart + 1, xStart + 1) && isKingAgainst(board[yStart + 1][xStart + 1], pieceColour))
                || (areInRange(board, yStart - 1, xStart - 1) && isKingAgainst(board[yStart - 1][xStart - 1], pieceColour))) {
            return Result.valid(String.format(Messages.CHECK, board[yStart][xStart].getSlot().getCellContent().getName()));
        } else {
            return Result.failure();
        }
    }
}
