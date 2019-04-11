package com.adryslab.chess.controller;

import com.adryslab.chess.model.annotation.Singleton;
import com.adryslab.chess.model.axis.Yaxis;
import com.adryslab.chess.model.cell.Cell;
import com.adryslab.chess.model.exception.IllegalCellContentMove;
import com.adryslab.chess.resources.ColourRepresentation;
import com.adryslab.chess.resources.Constants;
import com.adryslab.chess.resources.ErrorMessages;


/**
 * Represents the Board in a double array Matrix[][]
 */
@Singleton
public class BoardMatrixWrapper {

    // Board Matrix must be only one instance in the whole application --> considering this game a single-thread application.
    // in an scenario where several games can be running in parallel, this behaviour must be changed making one board matrix per game.
    private final Cell[][] boardMatrix;

    private static BoardMatrixWrapper instance;

    // no construct allowed -> Singleton pattern
    // Lazy load of the board, until instance is not requested, it is not initialized.
    private BoardMatrixWrapper() {
        boardMatrix = BoardMatrixPrototype.build();
    }

    /*
     * Apply Double Checked Locking principle.
     * Singleton Pattern -> Lazy load.
     */
    public static BoardMatrixWrapper getInstance() {
        if(instance == null){
            synchronized (BoardMatrixWrapper.class) {
                if(instance == null){
                    instance = new BoardMatrixWrapper();
                }
            }
        }
        return instance;
    }

    /**
     * Method to print the current state board.
     * This could also be placed in GameController, but regarding responsabilities (about the board), i prefered to place it here.
     * Keeps and remains with Single Responsability Principle --> Wrapper of the Board and it's state.
     */
    void drawBoard() {

        System.out.println("\n");

        for(int i = 0; i < boardMatrix.length; i++) {

            System.out.print(Constants.ANSI_RED + " ");
            int pieceIndex = i;
            System.out.print(Yaxis.fromNumber(i)
                    .orElseThrow(() -> new IllegalCellContentMove(String.format(ErrorMessages.IMPOSSIBLE_GET_YAXIS_NUMBER_AT, pieceIndex)))
                    .name() + " ");
            System.out.print(Constants.ANSI_RESET);

            for(int j = 0; j < boardMatrix.length; j++) {
                System.out.print("   ");
                System.out.print(ColourRepresentation.valueOf(boardMatrix[i][j].getSlot().getColour().name()).getAnsiColour() + " ");
                System.out.print(boardMatrix[i][j].getSlot().getCellContent().getSymbol() + " ");
                System.out.print(Constants.ANSI_RESET);
            }
            System.out.println("\n");
        }

        System.out.print("   ");
        System.out.print("   ");

        for(int line = 1; line <= boardMatrix.length; line++) {
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
        synchronized (boardMatrix) {
            return boardMatrix;
        }
    }
}
