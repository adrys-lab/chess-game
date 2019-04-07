package com.adryslab.chess.model.piece.type;

import com.adryslab.chess.model.cell.CellContent;

public enum PieceType implements CellContent {

    KING("K"),
    QUEEN("Q"),
    ROOK("R"),
    PAWN("P"),
    KNIGHT("N"),
    BISHOP("B");

    private final String symbol;

    PieceType(final String symbol) {
        this.symbol = symbol;
    }

    @Override
    public String getSymbol() {
        return symbol;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public String getName() {
        return name();
    }
}
