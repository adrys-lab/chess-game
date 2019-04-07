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
 * Represents the Board in a double array Matrix[][]
 */
@Singleton
public class BoardMatrix {

    // Board Matrix must be only one instance in the whole application.
    private final Cell[][] BOARD_MATRIX;

    private static BoardMatrix instance;

    // no construct allowed -> Singleton pattern
    // Lazy load of the board, until instance is not requested, it is not initialized.
    private BoardMatrix() {
        BOARD_MATRIX = initBoardMatrix();
    }

    /*
     * Apply Double Checked Locking principle.
     * Singleton Pattern -> Lazy load.
     */
    public static BoardMatrix getInstance() {
        if(instance == null){
            synchronized (BoardMatrix.class) {
                if(instance == null){
                    instance = new BoardMatrix();
                }
            }
        }
        return instance;
    }

    /**
     * Initialization of the board.
     * Constructs the initial board just once --> only called in constructor which can only be called one time.
     * private visibility --> no one can ask for build the matrix.
     * @return the board representation in Cell[][]
     */
    private Cell[][] initBoardMatrix() {

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

    /**
     * Method to print the current state board.
     * This could also be placed in GameController, but regarding responsabilities (about the board), i prefered to place it here.
     * Keeps and remains with Single Responsability Principle --> Wrapper of the Board and it's state.
     */
    void drawBoard() {

        System.out.println("\n");

        for(int i = 0; i < BOARD_MATRIX.length; i++) {

            System.out.print(Constants.ANSI_RED + " ");
            int pieceIndex = i;
            System.out.print(Yaxis.fromNumber(i)
                    .orElseThrow(() -> new IllegalCellContentMove(String.format(ErrorMessages.IMPOSSIBLE_GET_YAXIS_NUMBER_AT, pieceIndex)))
                    .name() + " ");
            System.out.print(Constants.ANSI_RESET);

            for(int j = 0; j < BOARD_MATRIX.length; j++) {
                System.out.print("   ");
                System.out.print(ColourRepresentation.valueOf(BOARD_MATRIX[i][j].getSlot().getColour().name()).getAnsiColour() + " ");
                System.out.print(BOARD_MATRIX[i][j].getSlot().getCellContent().getSymbol() + " ");
                System.out.print(Constants.ANSI_RESET);
            }
            System.out.println("\n");
        }

        System.out.print("   ");
        System.out.print("   ");

        for(int line = 1; line <= BOARD_MATRIX.length; line++) {
            System.out.print(Constants.ANSI_RED + " " + line + "    " + Constants.ANSI_RESET);
        }

        System.out.println("\n");
    }

    /*
     * synchronize access since the field is shared among several threads.
     * however this application is a single-thread running, ensure it's single-thread-accesses.
     *
     * Another solution could be use a ReadWriteLock to lock it's read/write accesses.
     */
    public Cell[][] getBoardMatrix() {
        synchronized (BOARD_MATRIX) {
            return BOARD_MATRIX;
        }
    }
}
