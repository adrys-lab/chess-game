package com.adryslab.chess.validator.context;

import com.adryslab.chess.controller.BoardMatrix;
import com.adryslab.chess.model.Position;
import com.adryslab.chess.model.cell.Cell;
import com.adryslab.chess.model.piece.Colour;
import com.adryslab.chess.model.piece.type.Bishop;
import com.adryslab.chess.model.piece.type.EmptySlot;
import com.adryslab.chess.model.piece.type.King;
import com.adryslab.chess.model.piece.type.PieceType;
import org.junit.Assert;
import org.junit.Test;

public class DiagonalKillKingValidatorTest {

    @Test
    public void checkDiagonalValid() {
        final Cell originCell = Cell.of(Position.of(6, 2), new Bishop(Colour.BLACK));

        Cell[][] board = BoardMatrix.getInstance().getBoardMatrix();
        board[4][4] = Cell.of(Position.of(4, 4), new King(Colour.WHITE));

        PieceKillKingValidatorProvider.getPieceMoveValidators(PieceType.BISHOP)
                .forEach(validator -> Assert.assertTrue(validator.validate(originCell, BoardMatrix.getInstance().getBoardMatrix()).isValid()));

        board[4][4] = Cell.of(Position.of(4, 4), new EmptySlot());
    }

    @Test
    public void checkDiagonalNotValidForColour() {
        final Cell originCell = Cell.of(Position.of(6, 2), new Bishop(Colour.BLACK));

        Cell[][] board = BoardMatrix.getInstance().getBoardMatrix();
        board[4][4] = Cell.of(Position.of(4, 4), new King(Colour.BLACK));

        PieceKillKingValidatorProvider.getPieceMoveValidators(PieceType.BISHOP)
                .forEach(validator -> Assert.assertTrue(validator.validate(originCell, BoardMatrix.getInstance().getBoardMatrix()).isFailure()));

        board[4][4] = Cell.of(Position.of(4, 4), new EmptySlot());
    }

    @Test
    public void checkDiagonalNotValidForKingPosition() {
        final Cell originCell = Cell.of(Position.of(6, 2), new Bishop(Colour.BLACK));

        Cell[][] board = BoardMatrix.getInstance().getBoardMatrix();
        board[4][3] = Cell.of(Position.of(4, 4), new King(Colour.WHITE));

        PieceKillKingValidatorProvider.getPieceMoveValidators(PieceType.BISHOP)
                .forEach(validator -> Assert.assertTrue(validator.validate(originCell, BoardMatrix.getInstance().getBoardMatrix()).isFailure()));

        board[4][3] = Cell.of(Position.of(4, 4), new EmptySlot());
    }


}
