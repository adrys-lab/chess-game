package com.adryslab.chess.validator.piece;

import com.adryslab.chess.controller.BoardMatrix;
import com.adryslab.chess.model.Position;
import com.adryslab.chess.model.cell.Cell;
import com.adryslab.chess.model.piece.Colour;
import com.adryslab.chess.model.piece.type.*;
import org.junit.Assert;
import org.junit.Test;

public class BishopValidatorTest {

    @Test
    public void validMove() {
        final Cell originCell = Cell.of(Position.of(4, 4), new Bishop(Colour.BLACK));
        final Cell destCell = Cell.of(Position.of(5, 5), new EmptySlot());

        Cell[][] board = BoardMatrix.getInstance().getBoardMatrix();
        board[5][5] = destCell;

        PieceValidatorProvider.getPieceMoveValidators(PieceType.BISHOP)
                .forEach(trivalidator -> Assert.assertTrue(trivalidator.validate(originCell, destCell, BoardMatrix.getInstance().getBoardMatrix()).isValid()));

        board[5][5] = Cell.of(Position.of(5, 5), new EmptySlot());
    }

    @Test
    public void lowDiagonal() {
        final Cell originCell = Cell.of(Position.of(6, 2), new Bishop(Colour.BLACK));
        final Cell destCell = Cell.of(Position.of(4, 4), new EmptySlot());

        Cell[][] board = BoardMatrix.getInstance().getBoardMatrix();
        board[4][4] = destCell;

        PieceValidatorProvider.getPieceMoveValidators(PieceType.BISHOP)
                .forEach(trivalidator -> Assert.assertTrue(trivalidator.validate(originCell, destCell, BoardMatrix.getInstance().getBoardMatrix()).isValid()));

        board[4][4] = Cell.of(Position.of(2, 1), new EmptySlot());
    }

    @Test
    public void notValidWhenPiecesInTheMiddle() {
        final Cell originCell = Cell.of(Position.of(3, 0), new Bishop(Colour.BLACK));
        final Cell destCell = Cell.of(Position.of(7, 4), new EmptySlot());

        Cell[][] board = BoardMatrix.getInstance().getBoardMatrix();
        board[7][4] = destCell;

        PieceValidatorProvider.getPieceMoveValidators(PieceType.BISHOP)
                .forEach(trivalidator -> Assert.assertTrue(trivalidator.validate(originCell, destCell, BoardMatrix.getInstance().getBoardMatrix()).isFailure()));

        board[7][4] = Cell.of(Position.of(2, 1), new EmptySlot());
    }
}
