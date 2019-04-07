package com.adryslab.chess.validator.context;

import com.adryslab.chess.mapper.PieceTypeMapper;
import com.adryslab.chess.model.Result;
import com.adryslab.chess.model.cell.Cell;
import com.adryslab.chess.model.piece.Colour;
import com.adryslab.chess.model.piece.type.PieceType;

import java.util.function.Function;

/**
 *
 * All classes inheriting this abstract, validate all piece-related movements,
 * until reach some piece in the middle of its allowed movement.
 *
 * If found piece is an against-king, it will return Result.valid.
 *
 * differs from Piece movements because there's no destination cell.
 */
class AbstractKillKingValidator {

    boolean isKingAgainst(final Cell cell, final Colour pieceColour) {
        return !cell.getSlot().getCellContent().isEmpty()
                && PieceTypeMapper.map(cell.getSlot().getCellContent()) == PieceType.KING
                && cell.getSlot().getColour() != pieceColour;
    }

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
