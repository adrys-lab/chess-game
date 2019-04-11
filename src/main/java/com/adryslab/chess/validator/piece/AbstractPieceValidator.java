package com.adryslab.chess.validator.piece;

import com.adryslab.chess.mapper.PieceTypeMapper;
import com.adryslab.chess.model.Result;
import com.adryslab.chess.model.cell.Cell;
import com.adryslab.chess.model.piece.Colour;
import com.adryslab.chess.model.piece.type.PieceType;
import com.adryslab.chess.resources.ErrorMessages;
import com.adryslab.chess.validator.composed.BiValidator;

import java.util.function.Function;

/**
 *
 * All classes inheriting this abstract, validate all piece-related movements:
 *
 * Validations can be related to piece correctness movement, and validation to king Check all around the board.
 */
public abstract class AbstractPieceValidator implements TriValidator<Cell, Cell, Cell[][], String>, BiValidator<Cell, Cell[][], String> {

    Result<String> commonValidate(final Cell originCell, final Cell destinationCell) {

        if(originCell.getPosition().equals(destinationCell.getPosition())) {
            return Result.failure(ErrorMessages.ORIGIN_AND_DESTINATION_ARE_THE_SAME);
        } else if(originCell.getSlot().getColour() == destinationCell.getSlot().getColour()) {
            return Result.failure(ErrorMessages.DESTINATION_IS_SAME_COLOUR);
        }

        return Result.valid();
    }

    /**
     * reused method among several validators to check several directions (Diagonal, Vertical and horizontal --> linear)
     *
     * @param yAcum how increment yaxis
     * @param xAcum how increment xaxis
     * @param yStart start for yaxis
     * @param xStart start for xaxis
     * @param board board representation
     * @param destinationCell cell where move the piece
     * @param pieceColour the moved piece colour.
     * @return if valid or not with message.
     */
    Result<String> validateLinear(final Function<Integer, Integer> yAcum, final Function<Integer, Integer> xAcum, final int yStart, final int xStart, final Cell[][] board, final Cell destinationCell, final Colour pieceColour) {

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

    boolean isKingAgainst(final Cell cell, final Colour pieceColour) {
        return !cell.getSlot().getCellContent().isEmpty()
                && PieceTypeMapper.map(cell.getSlot().getCellContent()) == PieceType.KING
                && cell.getSlot().getColour() != pieceColour;
    }

    /**
     * Function used for finding the king within the board.
     *
     * If found piece is an against-king, it will return Result.valid.
     *
     * @param yAcum how increment yaxis
     * @param xAcum how increment xaxis
     * @param yStart start for yaxis
     * @param xStart start for xaxis
     * @param board board representation
     * @param pieceColour the moved piece colour.
     * @return valid if a king threaten can occur
     */
    Result<String> findKing(Function<Integer, Integer> yAcum, Function<Integer, Integer> xAcum, int yStart, int xStart, Cell[][] board, final Colour pieceColour) {

        int y = yStart;
        int x = xStart;

        while (areInRange(board, y, x)) {

            Cell cell = board[y][x];
            if(isKingAgainst(cell, pieceColour)) {
                return Result.valid();
            } else if(!cell.getSlot().getCellContent().isEmpty()) {
                break;
            }
            x = xAcum.apply(x);
            y = yAcum.apply(y);
        }

        return Result.failure();
    }

    boolean areInRange(Cell[][] board, int y, int x) {
        return y >= 0 && y < board.length && x >= 0 && x < board.length;
    }
}
