package com.adryslab.chess.validator.piece;

import com.adryslab.chess.controller.BoardMatrixWrapper;
import com.adryslab.chess.model.Position;
import com.adryslab.chess.model.Result;
import com.adryslab.chess.model.cell.Cell;
import com.adryslab.chess.model.piece.Colour;
import com.adryslab.chess.model.piece.type.EmptySlot;
import com.adryslab.chess.model.piece.type.PieceType;
import com.adryslab.chess.model.piece.type.Queen;
import org.junit.Assert;
import org.junit.Test;

public class QueenValidatorTest {

    @Test
    public void straightMoveIsCorrect() {
        final Cell originCell = Cell.of(Position.of(2, 4), new Queen(Colour.BLACK));
        final Cell destCell = Cell.of(Position.of(5, 4), new EmptySlot());

        Cell[][] board = BoardMatrixWrapper.getInstance().getBoardMatrix();
        board[5][4] = destCell;

        Assert.assertTrue(PieceValidatorProvider.getPieceMoveValidators(PieceType.QUEEN)
                .stream()
                .map(trivalidator -> trivalidator.validate(originCell, destCell, BoardMatrixWrapper.getInstance().getBoardMatrix()))
                .filter(Result::isValid)
                .findAny()
                .orElseGet(Result::failure).isValid());

        board[5][4] = Cell.of(Position.of(5, 4), new EmptySlot());
    }

    @Test
    public void secondStraightMoveIsCorrect() {
        final Cell originCell = Cell.of(Position.of(5, 4), new Queen(Colour.BLACK));
        final Cell destCell = Cell.of(Position.of(2, 4), new EmptySlot());

        Cell[][] board = BoardMatrixWrapper.getInstance().getBoardMatrix();
        board[2][4] = destCell;

        Assert.assertTrue(PieceValidatorProvider.getPieceMoveValidators(PieceType.QUEEN)
                .stream()
                .map(trivalidator -> trivalidator.validate(originCell, destCell, BoardMatrixWrapper.getInstance().getBoardMatrix()))
                .filter(Result::isValid)
                .findAny()
                .orElseGet(Result::failure).isValid());

        board[2][4] = Cell.of(Position.of(2, 4), new EmptySlot());
    }

    @Test
    public void thirdStraightMoveIsCorrect() {
        final Cell originCell = Cell.of(Position.of(5, 2), new Queen(Colour.BLACK));
        final Cell destCell = Cell.of(Position.of(5, 5), new EmptySlot());

        Cell[][] board = BoardMatrixWrapper.getInstance().getBoardMatrix();
        board[5][5] = destCell;

        Assert.assertTrue(PieceValidatorProvider.getPieceMoveValidators(PieceType.QUEEN)
                .stream()
                .map(trivalidator -> trivalidator.validate(originCell, destCell, BoardMatrixWrapper.getInstance().getBoardMatrix()))
                .filter(Result::isValid)
                .findAny()
                .orElseGet(Result::failure).isValid());

        board[3][5] = Cell.of(Position.of(3, 5), new EmptySlot());
    }

    @Test
    public void fourthStraightMoveIsCorrect() {
        final Cell originCell = Cell.of(Position.of(5, 5), new Queen(Colour.BLACK));
        final Cell destCell = Cell.of(Position.of(5, 2), new EmptySlot());

        Cell[][] board = BoardMatrixWrapper.getInstance().getBoardMatrix();
        board[5][2] = destCell;

        Assert.assertTrue(PieceValidatorProvider.getPieceMoveValidators(PieceType.QUEEN)
                .stream()
                .map(trivalidator -> trivalidator.validate(originCell, destCell, BoardMatrixWrapper.getInstance().getBoardMatrix()))
                .filter(Result::isValid)
                .findAny()
                .orElseGet(Result::failure).isValid());

        board[5][2] = Cell.of(Position.of(5, 2), new EmptySlot());
    }

    @Test
    public void firstDiagonalMoveIsCorrect() {
        final Cell originCell = Cell.of(Position.of(5, 5), new Queen(Colour.BLACK));
        final Cell destCell = Cell.of(Position.of(3, 3), new EmptySlot());

        Cell[][] board = BoardMatrixWrapper.getInstance().getBoardMatrix();
        board[3][3] = destCell;

        Assert.assertTrue(PieceValidatorProvider.getPieceMoveValidators(PieceType.QUEEN)
                .stream()
                .map(trivalidator -> trivalidator.validate(originCell, destCell, BoardMatrixWrapper.getInstance().getBoardMatrix()))
                .filter(Result::isValid)
                .findAny()
                .orElseGet(Result::failure)
                .isValid());

        board[3][3] = Cell.of(Position.of(3, 3), new EmptySlot());
    }
}
