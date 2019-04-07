package com.adryslab.chess.validator.piece;

import com.adryslab.chess.controller.BoardMatrix;
import com.adryslab.chess.model.Position;
import com.adryslab.chess.model.cell.Cell;
import com.adryslab.chess.model.piece.Colour;
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
                .forEach(trivalidator -> Assert.assertTrue(trivalidator.validate(originCell, destCell, BoardMatrix.getInstance().getBoardMatrix()).isFailure()));
    }

    @Test
    public void moveBehindNotValidForBlack() {
        final Cell originCell = Cell.of(Position.of(3, 0), new Pawn(Colour.BLACK));
        final Cell destCell = Cell.of(Position.of(2, 0), new Pawn(Colour.NA));

        PieceValidatorProvider.getPieceMoveValidators(PieceType.PAWN)
                .forEach(trivalidator -> Assert.assertTrue(trivalidator.validate(originCell, destCell, BoardMatrix.getInstance().getBoardMatrix()).isFailure()));
    }

    @Test
    public void moveDiagonalMoreThanOneIsNotValid() {
        final Cell originCell = Cell.of(Position.of(3, 0), new Pawn(Colour.BLACK));
        final Cell destCell = Cell.of(Position.of(2, 2), new Pawn(Colour.NA));

        PieceValidatorProvider.getPieceMoveValidators(PieceType.PAWN)
                .forEach(trivalidator -> Assert.assertTrue(trivalidator.validate(originCell, destCell, BoardMatrix.getInstance().getBoardMatrix()).isFailure()));
    }

    @Test
    public void moveDiagonalMoreThanOneIfKillForBlack() {
        final Cell originCell = Cell.of(Position.of(2, 2), new Pawn(Colour.BLACK));
        final Cell destCell = Cell.of(Position.of(3, 3), new Pawn(Colour.WHITE));

        Cell[][] board = BoardMatrix.getInstance().getBoardMatrix();
        board[3][3] = destCell;

        PieceValidatorProvider.getPieceMoveValidators(PieceType.PAWN)
                .forEach(trivalidator -> Assert.assertTrue(trivalidator.validate(originCell, destCell, BoardMatrix.getInstance().getBoardMatrix()).isValid()));
    }

    @Test
    public void moveDiagonalMoreThanOneIfKillForWhite() {
        final Cell originCell = Cell.of(Position.of(3, 2), new Pawn(Colour.WHITE));
        final Cell destCell = Cell.of(Position.of(2, 3), new Pawn(Colour.BLACK));

        Cell[][] board = BoardMatrix.getInstance().getBoardMatrix();
        board[2][3] = destCell;

        PieceValidatorProvider.getPieceMoveValidators(PieceType.PAWN)
                .forEach(trivalidator -> Assert.assertTrue(trivalidator.validate(originCell, destCell, BoardMatrix.getInstance().getBoardMatrix()).isValid()));
    }

    @Test
    public void moveValidForWhite() {
        final Cell originCell = Cell.of(Position.of(3, 0), new Pawn(Colour.WHITE));
        final Cell destCell = Cell.of(Position.of(2, 0), new Pawn(Colour.NA));

        PieceValidatorProvider.getPieceMoveValidators(PieceType.PAWN)
                .forEach(trivalidator -> Assert.assertTrue(trivalidator.validate(originCell, destCell, BoardMatrix.getInstance().getBoardMatrix()).isValid()));
    }
}
