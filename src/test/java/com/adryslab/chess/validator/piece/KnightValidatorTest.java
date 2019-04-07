package com.adryslab.chess.validator.piece;

import com.adryslab.chess.controller.BoardMatrix;
import com.adryslab.chess.model.Position;
import com.adryslab.chess.model.cell.Cell;
import com.adryslab.chess.model.piece.Colour;
import com.adryslab.chess.model.piece.type.EmptySlot;
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
                .forEach(trivalidator -> Assert.assertTrue(trivalidator.validate(originCell, destCell, BoardMatrix.getInstance().getBoardMatrix()).isValid()));

    }

    @Test
    public void secondCorrectMovement() {
        final Cell originCell = Cell.of(Position.of(0, 1), new Knight(Colour.BLACK));
        final Cell destCell = Cell.of(Position.of(2, 0), new EmptySlot());

        PieceValidatorProvider.getPieceMoveValidators(PieceType.KNIGHT)
                .forEach(trivalidator -> Assert.assertTrue(trivalidator.validate(originCell, destCell, BoardMatrix.getInstance().getBoardMatrix()).isValid()));
    }

    @Test
    public void thirdCorrectMovement() {
        final Cell originCell = Cell.of(Position.of(2, 1), new Knight(Colour.BLACK));
        final Cell destCell = Cell.of(Position.of(0, 0), new EmptySlot());

        PieceValidatorProvider.getPieceMoveValidators(PieceType.KNIGHT)
                .forEach(trivalidator -> Assert.assertTrue(trivalidator.validate(originCell, destCell, BoardMatrix.getInstance().getBoardMatrix()).isValid()));
    }

    @Test
    public void fourthCorrectMovement() {
        final Cell originCell = Cell.of(Position.of(2, 0), new Knight(Colour.BLACK));
        final Cell destCell = Cell.of(Position.of(0, 1), new EmptySlot());

        PieceValidatorProvider.getPieceMoveValidators(PieceType.KNIGHT)
                .forEach(trivalidator -> Assert.assertTrue(trivalidator.validate(originCell, destCell, BoardMatrix.getInstance().getBoardMatrix()).isValid()));
    }

    @Test
    public void fifthCorrectMovement() {
        final Cell originCell = Cell.of(Position.of(0, 0), new Knight(Colour.BLACK));
        final Cell destCell = Cell.of(Position.of(1, 2), new EmptySlot());

        PieceValidatorProvider.getPieceMoveValidators(PieceType.KNIGHT)
                .forEach(trivalidator -> Assert.assertTrue(trivalidator.validate(originCell, destCell, BoardMatrix.getInstance().getBoardMatrix()).isValid()));
    }

    @Test
    public void sixthCorrectMovement() {
        final Cell originCell = Cell.of(Position.of(1, 0), new Knight(Colour.BLACK));
        final Cell destCell = Cell.of(Position.of(0, 2), new EmptySlot());

        PieceValidatorProvider.getPieceMoveValidators(PieceType.KNIGHT)
                .forEach(trivalidator -> Assert.assertTrue(trivalidator.validate(originCell, destCell, BoardMatrix.getInstance().getBoardMatrix()).isValid()));
    }

    @Test
    public void seventhCorrectMovement() {
        final Cell originCell = Cell.of(Position.of(1, 2), new Knight(Colour.BLACK));
        final Cell destCell = Cell.of(Position.of(0, 0), new EmptySlot());

        PieceValidatorProvider.getPieceMoveValidators(PieceType.KNIGHT)
                .forEach(trivalidator -> Assert.assertTrue(trivalidator.validate(originCell, destCell, BoardMatrix.getInstance().getBoardMatrix()).isValid()));
    }

    @Test
    public void eigthCorrectMovement() {
        final Cell originCell = Cell.of(Position.of(0, 2), new Knight(Colour.BLACK));
        final Cell destCell = Cell.of(Position.of(1, 0), new EmptySlot());

        PieceValidatorProvider.getPieceMoveValidators(PieceType.KNIGHT)
                .forEach(trivalidator -> Assert.assertTrue(trivalidator.validate(originCell, destCell, BoardMatrix.getInstance().getBoardMatrix()).isValid()));
    }

    @Test
    public void incorrectMove() {
        final Cell originCell = Cell.of(Position.of(0, 2), new Knight(Colour.BLACK));
        final Cell destCell = Cell.of(Position.of(3, 0), new EmptySlot());

        PieceValidatorProvider.getPieceMoveValidators(PieceType.KNIGHT)
                .forEach(trivalidator -> Assert.assertTrue(trivalidator.validate(originCell, destCell, BoardMatrix.getInstance().getBoardMatrix()).isFailure()));
    }


}
