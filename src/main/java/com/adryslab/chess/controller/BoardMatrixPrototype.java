package com.adryslab.chess.controller;

import com.adryslab.chess.model.Position;
import com.adryslab.chess.model.annotation.Singleton;
import com.adryslab.chess.model.axis.Yaxis;
import com.adryslab.chess.model.cell.Cell;
import com.adryslab.chess.model.exception.IllegalCellContentMove;
import com.adryslab.chess.model.piece.Colour;
import com.adryslab.chess.model.piece.type.*;
import com.adryslab.chess.resources.ColourRepresentation;
import com.adryslab.chess.resources.Constants;
import com.adryslab.chess.resources.ErrorMessages;


/**
 * Board Prototype Pattern.
 * Builds Board initialized instances
 */
class BoardMatrixPrototype {

    // no construct allowed.
    private BoardMatrixPrototype() {
    }

    /**
     * Initialization of the board.
     * Constructs the initial board just once --> only called in constructor which can only be called one time.
     * private visibility --> no one can ask for build the matrix.
     * @return the board representation in Cell[][]
     */
    static Cell[][] build() {

        final Cell[][] board = new Cell[Constants.BOARD_WIDTH][Constants.BOARD_WIDTH];

        for(int i = 0 ; i < 8; i++) {
            board[i] = new Cell[8];
        }

        int x = 0;

        board[0][x] = Cell.of(Position.of(0, x++), new Rook(Colour.BLACK));
        board[0][x] = Cell.of(Position.of(0, x++), new Knight(Colour.BLACK));
        board[0][x] = Cell.of(Position.of(0, x++), new Bishop(Colour.BLACK));
        board[0][x] = Cell.of(Position.of(0, x++), new Queen(Colour.BLACK));
        board[0][x] = Cell.of(Position.of(0, x++), new King(Colour.BLACK));
        board[0][x] = Cell.of(Position.of(0, x++), new Bishop(Colour.BLACK));
        board[0][x] = Cell.of(Position.of(0, x++), new Knight(Colour.BLACK));
        board[0][x] = Cell.of(Position.of(0, x), new Rook(Colour.BLACK));

        for(int i = 0; i < board.length; i++) {
            board[1][i] = Cell.of(Position.of(1, i), new Pawn(Colour.BLACK));
            board[6][i] = Cell.of(Position.of(6, i), new Pawn(Colour.WHITE));
        }

        for(int i = 2; i < 6; i++) {
            for(int j = 0; j < board.length; j++) {
                board[i][j] = Cell.of(Position.of(i, j), new EmptySlot());
            }
        }

        x = 0;
        board[7][x] = Cell.of(Position.of(7, x++), new Rook(Colour.WHITE));
        board[7][x] = Cell.of(Position.of(7, x++), new Knight(Colour.WHITE));
        board[7][x] = Cell.of(Position.of(7, x++), new Bishop(Colour.WHITE));
        board[7][x] = Cell.of(Position.of(7, x++), new Queen(Colour.WHITE));
        board[7][x] = Cell.of(Position.of(7, x++), new King(Colour.WHITE));
        board[7][x] = Cell.of(Position.of(7, x++), new Bishop(Colour.WHITE));
        board[7][x] = Cell.of(Position.of(7, x++), new Knight(Colour.WHITE));
        board[7][x] = Cell.of(Position.of(7, x), new Rook(Colour.WHITE));

        return board;
    }
}
