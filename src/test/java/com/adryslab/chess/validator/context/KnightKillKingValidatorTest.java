package com.adryslab.chess.validator.context;

import com.adryslab.chess.controller.BoardMatrix;
import com.adryslab.chess.model.Position;
import com.adryslab.chess.model.cell.Cell;
import com.adryslab.chess.model.piece.Colour;
import com.adryslab.chess.model.piece.type.*;
import com.adryslab.chess.validator.piece.PieceValidatorProvider;
import org.junit.Assert;
import org.junit.Test;

public class KnightKillKingValidatorTest {

    @Test
    public void firstCorrectKill() {
        final Cell originCell = Cell.of(Position.of(0, 0), new Knight(Colour.BLACK));

        Cell[][] board = BoardMatrix.getInstance().getBoardMatrix();
        board[2][1] = Cell.of(Position.of(2, 1), new King(Colour.WHITE));

        PieceKillKingValidatorProvider.getPieceMoveValidators(PieceType.KNIGHT)
                .forEach(validator -> Assert.assertTrue(validator.validate(originCell, BoardMatrix.getInstance().getBoardMatrix()).isValid()));

        board[2][1] = Cell.of(Position.of(2, 1), new EmptySlot());
    }

    @Test
    public void secondCorrectKill() {
        final Cell originCell = Cell.of(Position.of(0, 1), new Knight(Colour.BLACK));

        Cell[][] board = BoardMatrix.getInstance().getBoardMatrix();
        board[2][0] = Cell.of(Position.of(2, 0), new King(Colour.WHITE));

        PieceKillKingValidatorProvider.getPieceMoveValidators(PieceType.KNIGHT)
                .forEach(validator -> Assert.assertTrue(validator.validate(originCell, BoardMatrix.getInstance().getBoardMatrix()).isValid()));

        board[2][0] = Cell.of(Position.of(2, 0), new EmptySlot());

    }

    @Test
    public void thirdCorrectMovement() {
        final Cell originCell = Cell.of(Position.of(2, 1), new Knight(Colour.BLACK));

        Cell[][] board = BoardMatrix.getInstance().getBoardMatrix();
        board[0][0] = Cell.of(Position.of(0, 0), new King(Colour.WHITE));

        PieceKillKingValidatorProvider.getPieceMoveValidators(PieceType.KNIGHT)
                .forEach(validator -> Assert.assertTrue(validator.validate(originCell, BoardMatrix.getInstance().getBoardMatrix()).isValid()));

        board[0][0] = Cell.of(Position.of(0, 0), new EmptySlot());
    }

    @Test
    public void fourthCorrectMovement() {
        final Cell originCell = Cell.of(Position.of(2, 0), new Knight(Colour.BLACK));

        Cell[][] board = BoardMatrix.getInstance().getBoardMatrix();
        board[0][1] = Cell.of(Position.of(0, 1), new King(Colour.WHITE));

        PieceKillKingValidatorProvider.getPieceMoveValidators(PieceType.KNIGHT)
                .forEach(validator -> Assert.assertTrue(validator.validate(originCell, BoardMatrix.getInstance().getBoardMatrix()).isValid()));

        board[0][1] = Cell.of(Position.of(0, 1), new EmptySlot());
    }

    @Test
    public void fifthCorrectMovement() {
        final Cell originCell = Cell.of(Position.of(0, 0), new Knight(Colour.BLACK));

        Cell[][] board = BoardMatrix.getInstance().getBoardMatrix();
        board[1][2] = Cell.of(Position.of(1, 2), new King(Colour.WHITE));

        PieceKillKingValidatorProvider.getPieceMoveValidators(PieceType.KNIGHT)
                .forEach(validator -> Assert.assertTrue(validator.validate(originCell, BoardMatrix.getInstance().getBoardMatrix()).isValid()));

        board[1][2] = Cell.of(Position.of(1, 2), new EmptySlot());
    }

    @Test
    public void sixthCorrectMovement() {
        final Cell originCell = Cell.of(Position.of(1, 0), new Knight(Colour.BLACK));

        Cell[][] board = BoardMatrix.getInstance().getBoardMatrix();
        board[0][2] = Cell.of(Position.of(0, 2), new King(Colour.WHITE));

        PieceKillKingValidatorProvider.getPieceMoveValidators(PieceType.KNIGHT)
                .forEach(validator -> Assert.assertTrue(validator.validate(originCell, BoardMatrix.getInstance().getBoardMatrix()).isValid()));

        board[0][2] = Cell.of(Position.of(0, 2), new EmptySlot());
    }

    @Test
    public void seventhCorrectMovement() {
        final Cell originCell = Cell.of(Position.of(1, 2), new Knight(Colour.BLACK));

        Cell[][] board = BoardMatrix.getInstance().getBoardMatrix();
        board[0][0] = Cell.of(Position.of(0, 0), new King(Colour.WHITE));

        PieceKillKingValidatorProvider.getPieceMoveValidators(PieceType.KNIGHT)
                .forEach(validator -> Assert.assertTrue(validator.validate(originCell, BoardMatrix.getInstance().getBoardMatrix()).isValid()));

        board[0][0] = Cell.of(Position.of(0, 0), new EmptySlot());
    }

    @Test
    public void eigthCorrectMovement() {
        final Cell originCell = Cell.of(Position.of(0, 2), new Knight(Colour.BLACK));

        Cell[][] board = BoardMatrix.getInstance().getBoardMatrix();
        board[1][0] = Cell.of(Position.of(1, 0), new King(Colour.WHITE));

        PieceKillKingValidatorProvider.getPieceMoveValidators(PieceType.KNIGHT)
                .forEach(validator -> Assert.assertTrue(validator.validate(originCell, BoardMatrix.getInstance().getBoardMatrix()).isValid()));

        board[1][0] = Cell.of(Position.of(1, 0), new EmptySlot());
    }

    @Test
    public void incorrectForColour() {
        final Cell originCell = Cell.of(Position.of(0, 2), new Knight(Colour.BLACK));

        Cell[][] board = BoardMatrix.getInstance().getBoardMatrix();
        board[1][0] = Cell.of(Position.of(1, 0), new King(Colour.BLACK));

        PieceKillKingValidatorProvider.getPieceMoveValidators(PieceType.KNIGHT)
                .forEach(validator -> Assert.assertTrue(validator.validate(originCell, BoardMatrix.getInstance().getBoardMatrix()).isFailure()));

        board[1][0] = Cell.of(Position.of(1, 0), new EmptySlot());
    }
}
