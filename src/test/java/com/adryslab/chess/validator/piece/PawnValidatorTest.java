package com.adryslab.chess.validator.piece;

import com.adryslab.chess.controller.BoardMatrixWrapper;
import com.adryslab.chess.model.Position;
import com.adryslab.chess.model.cell.Cell;
import com.adryslab.chess.model.piece.Colour;
import com.adryslab.chess.model.piece.type.EmptySlot;
import com.adryslab.chess.model.piece.type.King;
import com.adryslab.chess.model.piece.type.Pawn;
import com.adryslab.chess.model.piece.type.PieceType;
import org.junit.Assert;
import org.junit.Test;

public class PawnValidatorTest {

    @Test
    public void moveThreeAheadNotValid() {
        final Cell originCell = Cell.of(Position.of(0, 0), new Pawn(Colour.BLACK));
        final Cell destCell = Cell.of(Position.of(3, 0), new Pawn(Colour.NA));

        PieceValidatorProvider.getPieceMoveValidators(PieceType.PAWN)
                .forEach(trivalidator -> Assert.assertTrue(trivalidator.validate(originCell, destCell, BoardMatrixWrapper.getInstance().getBoardMatrix()).isFailure()));
    }

    @Test
    public void moveBehindNotValidForBlack() {
        final Cell originCell = Cell.of(Position.of(3, 0), new Pawn(Colour.BLACK));
        final Cell destCell = Cell.of(Position.of(2, 0), new Pawn(Colour.NA));

        PieceValidatorProvider.getPieceMoveValidators(PieceType.PAWN)
                .forEach(trivalidator -> Assert.assertTrue(trivalidator.validate(originCell, destCell, BoardMatrixWrapper.getInstance().getBoardMatrix()).isFailure()));
    }

    @Test
    public void moveDiagonalMoreThanOneIsNotValid() {
        final Cell originCell = Cell.of(Position.of(3, 0), new Pawn(Colour.BLACK));
        final Cell destCell = Cell.of(Position.of(2, 2), new Pawn(Colour.NA));

        PieceValidatorProvider.getPieceMoveValidators(PieceType.PAWN)
                .forEach(trivalidator -> Assert.assertTrue(trivalidator.validate(originCell, destCell, BoardMatrixWrapper.getInstance().getBoardMatrix()).isFailure()));
    }

    @Test
    public void moveDiagonalMoreThanOneIfKillForBlack() {
        final Cell originCell = Cell.of(Position.of(2, 2), new Pawn(Colour.BLACK));
        final Cell destCell = Cell.of(Position.of(3, 3), new Pawn(Colour.WHITE));

        Cell[][] board = BoardMatrixWrapper.getInstance().getBoardMatrix();
        board[3][3] = destCell;

        PieceValidatorProvider.getPieceMoveValidators(PieceType.PAWN)
                .forEach(trivalidator -> Assert.assertTrue(trivalidator.validate(originCell, destCell, BoardMatrixWrapper.getInstance().getBoardMatrix()).isValid()));
    }

    @Test
    public void moveDiagonalMoreThanOneIfKillForWhite() {
        final Cell originCell = Cell.of(Position.of(3, 2), new Pawn(Colour.WHITE));
        final Cell destCell = Cell.of(Position.of(2, 3), new Pawn(Colour.BLACK));

        Cell[][] board = BoardMatrixWrapper.getInstance().getBoardMatrix();
        board[2][3] = destCell;

        PieceValidatorProvider.getPieceMoveValidators(PieceType.PAWN)
                .forEach(trivalidator -> Assert.assertTrue(trivalidator.validate(originCell, destCell, BoardMatrixWrapper.getInstance().getBoardMatrix()).isValid()));
    }

    @Test
    public void moveValidForWhite() {
        final Cell originCell = Cell.of(Position.of(3, 0), new Pawn(Colour.WHITE));
        final Cell destCell = Cell.of(Position.of(2, 0), new Pawn(Colour.NA));

        PieceValidatorProvider.getPieceMoveValidators(PieceType.PAWN)
                .forEach(trivalidator -> Assert.assertTrue(trivalidator.validate(originCell, destCell, BoardMatrixWrapper.getInstance().getBoardMatrix()).isValid()));
    }

    @Test
    public void pawnBlackFirstCanKillValid() {
        final Cell originCell = Cell.of(Position.of(4, 4), new Pawn(Colour.BLACK));

        Cell[][] board = BoardMatrixWrapper.getInstance().getBoardMatrix();
        board[3][3] = Cell.of(Position.of(3, 3), new King(Colour.WHITE));

        PieceValidatorProvider.getPieceMoveValidators(PieceType.PAWN)
                .forEach(validator -> Assert.assertTrue(validator.validate(originCell, BoardMatrixWrapper.getInstance().getBoardMatrix()).isValid()));

        board[3][3] = Cell.of(Position.of(3, 3), new EmptySlot());
    }

    @Test
    public void pawnBlackSecondCanKillValid() {
        final Cell originCell = Cell.of(Position.of(4, 4), new Pawn(Colour.BLACK));

        Cell[][] board = BoardMatrixWrapper.getInstance().getBoardMatrix();
        board[3][5] = Cell.of(Position.of(3, 5), new King(Colour.WHITE));

        PieceValidatorProvider.getPieceMoveValidators(PieceType.PAWN)
                .forEach(validator -> Assert.assertTrue(validator.validate(originCell, BoardMatrixWrapper.getInstance().getBoardMatrix()).isValid()));

        board[3][5] = Cell.of(Position.of(3, 5), new EmptySlot());
    }

    @Test
    public void pawnBlackCanNotKillforColour() {
        final Cell originCell = Cell.of(Position.of(4, 4), new Pawn(Colour.BLACK));

        Cell[][] board = BoardMatrixWrapper.getInstance().getBoardMatrix();
        board[3][5] = Cell.of(Position.of(3, 5), new King(Colour.BLACK));

        PieceValidatorProvider.getPieceMoveValidators(PieceType.PAWN)
                .forEach(validator -> Assert.assertTrue(validator.validate(originCell, BoardMatrixWrapper.getInstance().getBoardMatrix()).isFailure()));

        board[3][5] = Cell.of(Position.of(3, 5), new EmptySlot());
    }

    @Test
    public void pawnWhiteSFirstCanKillValid() {
        final Cell originCell = Cell.of(Position.of(4, 4), new Pawn(Colour.WHITE));

        Cell[][] board = BoardMatrixWrapper.getInstance().getBoardMatrix();
        board[5][5] = Cell.of(Position.of(5, 5), new King(Colour.BLACK));

        PieceValidatorProvider.getPieceMoveValidators(PieceType.PAWN)
                .forEach(validator -> Assert.assertTrue(validator.validate(originCell, BoardMatrixWrapper.getInstance().getBoardMatrix()).isValid()));

        board[5][5] = Cell.of(Position.of(5, 5), new EmptySlot());
    }

    @Test
    public void pawnWhiteSSecondCanKillValid() {
        final Cell originCell = Cell.of(Position.of(4, 4), new Pawn(Colour.WHITE));

        Cell[][] board = BoardMatrixWrapper.getInstance().getBoardMatrix();
        board[5][3] = Cell.of(Position.of(5, 3), new King(Colour.BLACK));

        PieceValidatorProvider.getPieceMoveValidators(PieceType.PAWN)
                .forEach(validator -> Assert.assertTrue(validator.validate(originCell, BoardMatrixWrapper.getInstance().getBoardMatrix()).isValid()));

        board[5][3] = Cell.of(Position.of(3, 3), new EmptySlot());
    }

    @Test
    public void pawnWhiteCanNotKillforColour() {
        final Cell originCell = Cell.of(Position.of(4, 4), new Pawn(Colour.WHITE));

        Cell[][] board = BoardMatrixWrapper.getInstance().getBoardMatrix();
        board[3][5] = Cell.of(Position.of(5, 5), new King(Colour.WHITE));

        PieceValidatorProvider.getPieceMoveValidators(PieceType.PAWN)
                .forEach(validator -> Assert.assertTrue(validator.validate(originCell, BoardMatrixWrapper.getInstance().getBoardMatrix()).isFailure()));

        board[3][5] = Cell.of(Position.of(3, 5), new EmptySlot());
    }
}
