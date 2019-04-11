package com.adryslab.chess.controller;

import com.adryslab.chess.model.Position;
import com.adryslab.chess.model.Result;
import com.adryslab.chess.model.cell.Cell;
import com.adryslab.chess.model.piece.Colour;
import com.adryslab.chess.model.piece.type.Bishop;
import com.adryslab.chess.model.piece.type.EmptySlot;
import com.adryslab.chess.model.piece.type.King;
import com.adryslab.chess.model.state.GameState;
import com.adryslab.chess.resources.ErrorMessages;
import com.adryslab.chess.resources.Messages;
import org.junit.Assert;
import org.junit.Test;

public class BoardControllerTest {

    private final BoardController boardController = new BoardController(BoardMatrixWrapper.getInstance());

    @Test
    public void assertCanNotMoveToSamePieceColourCell() {
        final Cell originCell = Cell.of(Position.of(3, 3), new Bishop(Colour.BLACK));
        final Cell destCell = Cell.of(Position.of(5, 5), new Bishop(Colour.BLACK));

        Cell[][] board = BoardMatrixWrapper.getInstance().getBoardMatrix();
        board[4][5] = Cell.of(Position.of(4, 5), new King(Colour.WHITE));

        Result<String> result = boardController.move(originCell, destCell);

        Assert.assertTrue(result.isFailure());
        Assert.assertEquals(result.getInstance().get(), ErrorMessages.DESTINATION_IS_SAME_COLOUR);

        board[0][2] = Cell.of(Position.of(0, 2), new EmptySlot());
    }

    @Test
    public void assertCheck() {
        final Cell originCell = Cell.of(Position.of(3, 3), new Bishop(Colour.BLACK));
        final Cell destCell = Cell.of(Position.of(4, 4), new EmptySlot());

        Cell[][] board = BoardMatrixWrapper.getInstance().getBoardMatrix();
        board[3][3] = originCell;
        board[4][4] = destCell;
        board[3][5] = Cell.of(Position.of(3, 5), new King(Colour.WHITE));

        Result<String> result = boardController.move(originCell, destCell);

        Assert.assertTrue(result.isValid());
        Assert.assertTrue(result.getInstance().get().contains("Check !"));
        Assert.assertSame(GameState.CHECK, boardController.getGameState());

        board[3][5] = Cell.of(Position.of(3, 5), new EmptySlot());
    }

    @Test
    public void assertGameFinishIfKillTheKing() {
        final Cell originCell = Cell.of(Position.of(3, 3), new Bishop(Colour.BLACK));
        final Cell destCell = Cell.of(Position.of(4, 4), new King(Colour.WHITE));

        Cell[][] board = BoardMatrixWrapper.getInstance().getBoardMatrix();
        board[3][3] = originCell;
        board[4][4] = destCell;

        Result<String> result = boardController.move(originCell, destCell);

        Assert.assertTrue(result.isValid());
        Assert.assertTrue(result.getInstance().get().contains(Messages.WINNER_GAME_OVER));
        Assert.assertSame(GameState.FINISHED, boardController.getGameState());

        board[3][5] = Cell.of(Position.of(3, 5), new EmptySlot());
    }
}
