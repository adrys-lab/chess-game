package com.adryslab.chess.validator.piece;

import com.adryslab.chess.controller.BoardMatrixWrapper;
import com.adryslab.chess.model.Position;
import com.adryslab.chess.model.cell.Cell;
import com.adryslab.chess.model.piece.Colour;
import com.adryslab.chess.model.piece.type.Bishop;
import com.adryslab.chess.model.piece.type.EmptySlot;
import com.adryslab.chess.model.piece.type.King;
import com.adryslab.chess.model.piece.type.PieceType;
import org.junit.Assert;
import org.junit.Test;

public class DiagonalValidatorTest {

    @Test
    public void checkDiagonalValid() {
        final Cell originCell = Cell.of(Position.of(6, 2), new Bishop(Colour.BLACK));

        Cell[][] board = BoardMatrixWrapper.getInstance().getBoardMatrix();
        board[4][4] = Cell.of(Position.of(4, 4), new King(Colour.WHITE));

        PieceValidatorProvider.getPieceMoveValidators(PieceType.BISHOP)
                .forEach(validator -> Assert.assertTrue(validator.validate(originCell, BoardMatrixWrapper.getInstance().getBoardMatrix()).isValid()));

        board[4][4] = Cell.of(Position.of(4, 4), new EmptySlot());
    }

    @Test
    public void checkDiagonalNotValidForColour() {
        final Cell originCell = Cell.of(Position.of(6, 2), new Bishop(Colour.BLACK));

        Cell[][] board = BoardMatrixWrapper.getInstance().getBoardMatrix();
        board[4][4] = Cell.of(Position.of(4, 4), new King(Colour.BLACK));

        PieceValidatorProvider.getPieceMoveValidators(PieceType.BISHOP)
                .forEach(validator -> Assert.assertTrue(validator.validate(originCell, BoardMatrixWrapper.getInstance().getBoardMatrix()).isFailure()));

        board[4][4] = Cell.of(Position.of(4, 4), new EmptySlot());
    }

    @Test
    public void checkDiagonalNotValidForKingPosition() {
        final Cell originCell = Cell.of(Position.of(6, 2), new Bishop(Colour.BLACK));

        Cell[][] board = BoardMatrixWrapper.getInstance().getBoardMatrix();
        board[4][3] = Cell.of(Position.of(4, 4), new King(Colour.WHITE));

        PieceValidatorProvider.getPieceMoveValidators(PieceType.BISHOP)
                .forEach(validator -> Assert.assertTrue(validator.validate(originCell, BoardMatrixWrapper.getInstance().getBoardMatrix()).isFailure()));

        board[4][3] = Cell.of(Position.of(4, 4), new EmptySlot());
    }
}
