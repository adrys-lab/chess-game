package com.adryslab.chess.validator.context;

import com.adryslab.chess.controller.BoardMatrix;
import com.adryslab.chess.model.Position;
import com.adryslab.chess.model.cell.Cell;
import com.adryslab.chess.model.piece.Colour;
import com.adryslab.chess.model.piece.type.*;
import org.junit.Assert;
import org.junit.Test;

public class PawnKillKingValidatorTest {

    @Test
    public void pawnBlackFirstCanKillValid() {
        final Cell originCell = Cell.of(Position.of(4, 4), new Pawn(Colour.BLACK));

        Cell[][] board = BoardMatrix.getInstance().getBoardMatrix();
        board[3][3] = Cell.of(Position.of(3, 3), new King(Colour.WHITE));

        PieceKillKingValidatorProvider.getPieceMoveValidators(PieceType.PAWN)
                .forEach(validator -> Assert.assertTrue(validator.validate(originCell, BoardMatrix.getInstance().getBoardMatrix()).isValid()));

        board[3][3] = Cell.of(Position.of(3, 3), new EmptySlot());
    }

    @Test
    public void pawnBlackSecondCanKillValid() {
        final Cell originCell = Cell.of(Position.of(4, 4), new Pawn(Colour.BLACK));

        Cell[][] board = BoardMatrix.getInstance().getBoardMatrix();
        board[3][5] = Cell.of(Position.of(3, 5), new King(Colour.WHITE));

        PieceKillKingValidatorProvider.getPieceMoveValidators(PieceType.PAWN)
                .forEach(validator -> Assert.assertTrue(validator.validate(originCell, BoardMatrix.getInstance().getBoardMatrix()).isValid()));

        board[3][5] = Cell.of(Position.of(3, 5), new EmptySlot());
    }

    @Test
    public void pawnBlackCanNotKillforColour() {
        final Cell originCell = Cell.of(Position.of(4, 4), new Pawn(Colour.BLACK));

        Cell[][] board = BoardMatrix.getInstance().getBoardMatrix();
        board[3][5] = Cell.of(Position.of(3, 5), new King(Colour.BLACK));

        PieceKillKingValidatorProvider.getPieceMoveValidators(PieceType.PAWN)
                .forEach(validator -> Assert.assertTrue(validator.validate(originCell, BoardMatrix.getInstance().getBoardMatrix()).isFailure()));

        board[3][5] = Cell.of(Position.of(3, 5), new EmptySlot());
    }

    @Test
    public void pawnWhiteSFirstCanKillValid() {
        final Cell originCell = Cell.of(Position.of(4, 4), new Pawn(Colour.WHITE));

        Cell[][] board = BoardMatrix.getInstance().getBoardMatrix();
        board[5][5] = Cell.of(Position.of(5, 5), new King(Colour.BLACK));

        PieceKillKingValidatorProvider.getPieceMoveValidators(PieceType.PAWN)
                .forEach(validator -> Assert.assertTrue(validator.validate(originCell, BoardMatrix.getInstance().getBoardMatrix()).isValid()));

        board[5][5] = Cell.of(Position.of(5, 5), new EmptySlot());
    }

    @Test
    public void pawnWhiteSSecondCanKillValid() {
        final Cell originCell = Cell.of(Position.of(4, 4), new Pawn(Colour.WHITE));

        Cell[][] board = BoardMatrix.getInstance().getBoardMatrix();
        board[5][3] = Cell.of(Position.of(5, 3), new King(Colour.BLACK));

        PieceKillKingValidatorProvider.getPieceMoveValidators(PieceType.PAWN)
                .forEach(validator -> Assert.assertTrue(validator.validate(originCell, BoardMatrix.getInstance().getBoardMatrix()).isValid()));

        board[5][3] = Cell.of(Position.of(3, 3), new EmptySlot());
    }

    @Test
    public void pawnWhiteCanNotKillforColour() {
        final Cell originCell = Cell.of(Position.of(4, 4), new Pawn(Colour.WHITE));

        Cell[][] board = BoardMatrix.getInstance().getBoardMatrix();
        board[3][5] = Cell.of(Position.of(5, 5), new King(Colour.WHITE));

        PieceKillKingValidatorProvider.getPieceMoveValidators(PieceType.PAWN)
                .forEach(validator -> Assert.assertTrue(validator.validate(originCell, BoardMatrix.getInstance().getBoardMatrix()).isFailure()));

        board[3][5] = Cell.of(Position.of(3, 5), new EmptySlot());
    }


}
