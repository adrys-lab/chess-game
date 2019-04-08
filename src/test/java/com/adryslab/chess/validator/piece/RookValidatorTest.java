package com.adryslab.chess.validator.piece;

import com.adryslab.chess.controller.BoardMatrix;
import com.adryslab.chess.model.Position;
import com.adryslab.chess.model.cell.Cell;
import com.adryslab.chess.model.piece.Colour;
import com.adryslab.chess.model.piece.type.EmptySlot;
import com.adryslab.chess.model.piece.type.PieceType;
import com.adryslab.chess.model.piece.type.Rook;
import org.junit.Assert;
import org.junit.Test;

public class RookValidatorTest {

    @Test
    public void yAxisHigherIsValid() {
        final Cell originCell = Cell.of(Position.of(4, 4), new Rook(Colour.BLACK));
        final Cell destCell = Cell.of(Position.of(5, 4), new EmptySlot());

        Cell[][] board = BoardMatrix.getInstance().getBoardMatrix();
        board[5][4] = destCell;

        PieceValidatorProvider.getPieceMoveValidators(PieceType.ROOK)
                .forEach(trivalidator -> Assert.assertTrue(trivalidator.validate(originCell, destCell, BoardMatrix.getInstance().getBoardMatrix()).isValid()));

        board[5][4] = Cell.of(Position.of(5, 4), new EmptySlot());
    }

    @Test
    public void xAxisHigherIsValid() {
        final Cell originCell = Cell.of(Position.of(4, 4), new Rook(Colour.BLACK));
        final Cell destCell = Cell.of(Position.of(4, 0), new EmptySlot());

        Cell[][] board = BoardMatrix.getInstance().getBoardMatrix();
        board[4][0] = destCell;

        PieceValidatorProvider.getPieceMoveValidators(PieceType.ROOK)
                .forEach(trivalidator -> Assert.assertTrue(trivalidator.validate(originCell, destCell, BoardMatrix.getInstance().getBoardMatrix()).isValid()));

        board[4][0] = Cell.of(Position.of(4, 0), new EmptySlot());
    }

    @Test
    public void notValidWhenPiecesInTheMiddle() {
        final Cell originCell = Cell.of(Position.of(3, 0), new Rook(Colour.BLACK));
        final Cell destCell = Cell.of(Position.of(7, 1), new EmptySlot());

        Cell[][] board = BoardMatrix.getInstance().getBoardMatrix();
        board[7][1] = destCell;

        PieceValidatorProvider.getPieceMoveValidators(PieceType.ROOK)
                .forEach(trivalidator -> Assert.assertTrue(trivalidator.validate(originCell, destCell, BoardMatrix.getInstance().getBoardMatrix()).isFailure()));

        board[7][1] = Cell.of(Position.of(7, 1), new EmptySlot());
    }
}
