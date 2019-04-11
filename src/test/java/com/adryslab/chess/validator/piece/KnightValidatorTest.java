package com.adryslab.chess.validator.piece;

import com.adryslab.chess.controller.BoardMatrixWrapper;
import com.adryslab.chess.model.Position;
import com.adryslab.chess.model.cell.Cell;
import com.adryslab.chess.model.piece.Colour;
import com.adryslab.chess.model.piece.type.EmptySlot;
import com.adryslab.chess.model.piece.type.King;
import com.adryslab.chess.model.piece.type.Knight;
import com.adryslab.chess.model.piece.type.PieceType;
import org.junit.Assert;
import org.junit.Test;

public class KnightValidatorTest {

    @Test
    public void firstCorrectMovement() {
        final Cell originCell = Cell.of(Position.of(0, 0), new Knight(Colour.BLACK));
        final Cell destCell = Cell.of(Position.of(2, 1), new EmptySlot());

        PieceValidatorProvider.getPieceMoveValidators(PieceType.KNIGHT)
                .forEach(trivalidator -> Assert.assertTrue(trivalidator.validate(originCell, destCell, BoardMatrixWrapper.getInstance().getBoardMatrix()).isValid()));

    }

    @Test
    public void secondCorrectMovement() {
        final Cell originCell = Cell.of(Position.of(0, 1), new Knight(Colour.BLACK));
        final Cell destCell = Cell.of(Position.of(2, 0), new EmptySlot());

        PieceValidatorProvider.getPieceMoveValidators(PieceType.KNIGHT)
                .forEach(trivalidator -> Assert.assertTrue(trivalidator.validate(originCell, destCell, BoardMatrixWrapper.getInstance().getBoardMatrix()).isValid()));
    }

    @Test
    public void thirdCorrectMovement() {
        final Cell originCell = Cell.of(Position.of(2, 1), new Knight(Colour.BLACK));
        final Cell destCell = Cell.of(Position.of(0, 0), new EmptySlot());

        PieceValidatorProvider.getPieceMoveValidators(PieceType.KNIGHT)
                .forEach(trivalidator -> Assert.assertTrue(trivalidator.validate(originCell, destCell, BoardMatrixWrapper.getInstance().getBoardMatrix()).isValid()));
    }

    @Test
    public void fourthCorrectMovement() {
        final Cell originCell = Cell.of(Position.of(2, 0), new Knight(Colour.BLACK));
        final Cell destCell = Cell.of(Position.of(0, 1), new EmptySlot());

        PieceValidatorProvider.getPieceMoveValidators(PieceType.KNIGHT)
                .forEach(trivalidator -> Assert.assertTrue(trivalidator.validate(originCell, destCell, BoardMatrixWrapper.getInstance().getBoardMatrix()).isValid()));
    }

    @Test
    public void fifthCorrectMovement() {
        final Cell originCell = Cell.of(Position.of(0, 0), new Knight(Colour.BLACK));
        final Cell destCell = Cell.of(Position.of(1, 2), new EmptySlot());

        PieceValidatorProvider.getPieceMoveValidators(PieceType.KNIGHT)
                .forEach(trivalidator -> Assert.assertTrue(trivalidator.validate(originCell, destCell, BoardMatrixWrapper.getInstance().getBoardMatrix()).isValid()));
    }

    @Test
    public void sixthCorrectMovement() {
        final Cell originCell = Cell.of(Position.of(1, 0), new Knight(Colour.BLACK));
        final Cell destCell = Cell.of(Position.of(0, 2), new EmptySlot());

        PieceValidatorProvider.getPieceMoveValidators(PieceType.KNIGHT)
                .forEach(trivalidator -> Assert.assertTrue(trivalidator.validate(originCell, destCell, BoardMatrixWrapper.getInstance().getBoardMatrix()).isValid()));
    }

    @Test
    public void seventhCorrectMovement() {
        final Cell originCell = Cell.of(Position.of(1, 2), new Knight(Colour.BLACK));
        final Cell destCell = Cell.of(Position.of(0, 0), new EmptySlot());

        PieceValidatorProvider.getPieceMoveValidators(PieceType.KNIGHT)
                .forEach(trivalidator -> Assert.assertTrue(trivalidator.validate(originCell, destCell, BoardMatrixWrapper.getInstance().getBoardMatrix()).isValid()));
    }

    @Test
    public void eigthCorrectMovement() {
        final Cell originCell = Cell.of(Position.of(0, 2), new Knight(Colour.BLACK));
        final Cell destCell = Cell.of(Position.of(1, 0), new EmptySlot());

        PieceValidatorProvider.getPieceMoveValidators(PieceType.KNIGHT)
                .forEach(trivalidator -> Assert.assertTrue(trivalidator.validate(originCell, destCell, BoardMatrixWrapper.getInstance().getBoardMatrix()).isValid()));
    }

    @Test
    public void incorrectMove() {
        final Cell originCell = Cell.of(Position.of(0, 2), new Knight(Colour.BLACK));
        final Cell destCell = Cell.of(Position.of(3, 0), new EmptySlot());

        PieceValidatorProvider.getPieceMoveValidators(PieceType.KNIGHT)
                .forEach(trivalidator -> Assert.assertTrue(trivalidator.validate(originCell, destCell, BoardMatrixWrapper.getInstance().getBoardMatrix()).isFailure()));
    }

    @Test
    public void firstCorrectKill() {
        final Cell originCell = Cell.of(Position.of(0, 0), new Knight(Colour.BLACK));

        Cell[][] board = BoardMatrixWrapper.getInstance().getBoardMatrix();
        board[2][1] = Cell.of(Position.of(2, 1), new King(Colour.WHITE));

        PieceValidatorProvider.getPieceMoveValidators(PieceType.KNIGHT)
                .forEach(validator -> Assert.assertTrue(validator.validate(originCell, BoardMatrixWrapper.getInstance().getBoardMatrix()).isValid()));

        board[2][1] = Cell.of(Position.of(2, 1), new EmptySlot());
    }

    @Test
    public void secondCorrectKill() {
        final Cell originCell = Cell.of(Position.of(0, 1), new Knight(Colour.BLACK));

        Cell[][] board = BoardMatrixWrapper.getInstance().getBoardMatrix();
        board[2][0] = Cell.of(Position.of(2, 0), new King(Colour.WHITE));

        PieceValidatorProvider.getPieceMoveValidators(PieceType.KNIGHT)
                .forEach(validator -> Assert.assertTrue(validator.validate(originCell, BoardMatrixWrapper.getInstance().getBoardMatrix()).isValid()));

        board[2][0] = Cell.of(Position.of(2, 0), new EmptySlot());

    }

    @Test
    public void thirdCorrectKill() {
        final Cell originCell = Cell.of(Position.of(2, 1), new Knight(Colour.BLACK));

        Cell[][] board = BoardMatrixWrapper.getInstance().getBoardMatrix();
        board[0][0] = Cell.of(Position.of(0, 0), new King(Colour.WHITE));

        PieceValidatorProvider.getPieceMoveValidators(PieceType.KNIGHT)
                .forEach(validator -> Assert.assertTrue(validator.validate(originCell, BoardMatrixWrapper.getInstance().getBoardMatrix()).isValid()));

        board[0][0] = Cell.of(Position.of(0, 0), new EmptySlot());
    }

    @Test
    public void fourthCorrectKill() {
        final Cell originCell = Cell.of(Position.of(2, 0), new Knight(Colour.BLACK));

        Cell[][] board = BoardMatrixWrapper.getInstance().getBoardMatrix();
        board[0][1] = Cell.of(Position.of(0, 1), new King(Colour.WHITE));

        PieceValidatorProvider.getPieceMoveValidators(PieceType.KNIGHT)
                .forEach(validator -> Assert.assertTrue(validator.validate(originCell, BoardMatrixWrapper.getInstance().getBoardMatrix()).isValid()));

        board[0][1] = Cell.of(Position.of(0, 1), new EmptySlot());
    }

    @Test
    public void fifthCorrectKill() {
        final Cell originCell = Cell.of(Position.of(0, 0), new Knight(Colour.BLACK));

        Cell[][] board = BoardMatrixWrapper.getInstance().getBoardMatrix();
        board[1][2] = Cell.of(Position.of(1, 2), new King(Colour.WHITE));

        PieceValidatorProvider.getPieceMoveValidators(PieceType.KNIGHT)
                .forEach(validator -> Assert.assertTrue(validator.validate(originCell, BoardMatrixWrapper.getInstance().getBoardMatrix()).isValid()));

        board[1][2] = Cell.of(Position.of(1, 2), new EmptySlot());
    }

    @Test
    public void sixthCorrectKill() {
        final Cell originCell = Cell.of(Position.of(1, 0), new Knight(Colour.BLACK));

        Cell[][] board = BoardMatrixWrapper.getInstance().getBoardMatrix();
        board[0][2] = Cell.of(Position.of(0, 2), new King(Colour.WHITE));

        PieceValidatorProvider.getPieceMoveValidators(PieceType.KNIGHT)
                .forEach(validator -> Assert.assertTrue(validator.validate(originCell, BoardMatrixWrapper.getInstance().getBoardMatrix()).isValid()));

        board[0][2] = Cell.of(Position.of(0, 2), new EmptySlot());
    }

    @Test
    public void seventhCorrectKill() {
        final Cell originCell = Cell.of(Position.of(1, 2), new Knight(Colour.BLACK));

        Cell[][] board = BoardMatrixWrapper.getInstance().getBoardMatrix();
        board[0][0] = Cell.of(Position.of(0, 0), new King(Colour.WHITE));

        PieceValidatorProvider.getPieceMoveValidators(PieceType.KNIGHT)
                .forEach(validator -> Assert.assertTrue(validator.validate(originCell, BoardMatrixWrapper.getInstance().getBoardMatrix()).isValid()));

        board[0][0] = Cell.of(Position.of(0, 0), new EmptySlot());
    }

    @Test
    public void eigthCorrectKill() {
        final Cell originCell = Cell.of(Position.of(0, 2), new Knight(Colour.BLACK));

        Cell[][] board = BoardMatrixWrapper.getInstance().getBoardMatrix();
        board[1][0] = Cell.of(Position.of(1, 0), new King(Colour.WHITE));

        PieceValidatorProvider.getPieceMoveValidators(PieceType.KNIGHT)
                .forEach(validator -> Assert.assertTrue(validator.validate(originCell, BoardMatrixWrapper.getInstance().getBoardMatrix()).isValid()));

        board[1][0] = Cell.of(Position.of(1, 0), new EmptySlot());
    }

    @Test
    public void incorrectKillForColour() {
        final Cell originCell = Cell.of(Position.of(0, 2), new Knight(Colour.BLACK));

        Cell[][] board = BoardMatrixWrapper.getInstance().getBoardMatrix();
        board[1][0] = Cell.of(Position.of(1, 0), new King(Colour.BLACK));

        PieceValidatorProvider.getPieceMoveValidators(PieceType.KNIGHT)
                .forEach(validator -> Assert.assertTrue(validator.validate(originCell, BoardMatrixWrapper.getInstance().getBoardMatrix()).isFailure()));

        board[1][0] = Cell.of(Position.of(1, 0), new EmptySlot());
    }

}
