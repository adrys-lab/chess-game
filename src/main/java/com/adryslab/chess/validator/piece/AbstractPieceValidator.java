package com.adryslab.chess.validator.piece;

import com.adryslab.chess.model.Result;
import com.adryslab.chess.model.cell.Cell;
import com.adryslab.chess.model.piece.Colour;
import com.adryslab.chess.resources.ErrorMessages;

import java.util.function.Function;

/**
 *
 * All classes inheriting this abstract, validate all piece-related movements,
 * until reach destination cell.
 *
 * validate if the start cell and end cell, draw a valid movement for the given piece.
 */
abstract class AbstractPieceValidator {

    Result<String> commonValidate(Cell originCell, Cell destinationCell) {

        if(originCell.getPosition().equals(destinationCell.getPosition())) {
            return Result.failure(ErrorMessages.ORIGIN_AND_DESTINATION_ARE_THE_SAME);
        } else if(originCell.getSlot().getColour() == destinationCell.getSlot().getColour()) {
            return Result.failure(ErrorMessages.DESTINATION_IS_SAME_COLOUR);
        }

        return Result.valid();
    }

    /**
     * reused method among several validators to check several directions.
     * @param yAcum how increment yaxis
     * @param xAcum how increment xaxis
     * @param yStart start for yaxis
     * @param xStart start for xaxis
     * @param board board representation
     * @param destinationCell cell where move the piece
     * @param pieceColour the moved piece colour.
     * @return if valid or not with message.
     */
    Result<String> validateLinear(Function<Integer, Integer> yAcum, Function<Integer, Integer> xAcum, int yStart, int xStart, Cell[][] board, Cell destinationCell, final Colour pieceColour) {

        int y = yStart;
        int x = xStart;

        while (y >= 0 && y < board.length && x >= 0 && x < board.length) {

            Cell cell = board[y][x];
            if(!cell.getSlot().getCellContent().isEmpty() && cell != destinationCell) {
                return Result.failure();
            } else if(!cell.getSlot().getCellContent().isEmpty() && cell.getSlot().getColour() != pieceColour && cell == destinationCell) {
                return Result.valid();
            } else if (cell == destinationCell && cell.getSlot().getCellContent().isEmpty()) {
                return Result.valid();
            }
            x = xAcum.apply(x);
            y = yAcum.apply(y);
        }

        return Result.failure();
    }
}
