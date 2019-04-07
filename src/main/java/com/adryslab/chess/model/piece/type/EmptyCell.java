package com.adryslab.chess.model.piece.type;

import com.adryslab.chess.model.cell.CellContent;

public enum EmptyCell implements CellContent {

    EMPTY("X");

    private final String symbol;

    EmptyCell(final String symbol) {
        this.symbol = symbol;
    }

    @Override
    public String getSymbol() {
        return symbol;
    }

    @Override
    public boolean isEmpty() {
        return true;
    }

    @Override
    public String getName() {
        return name();
    }
}
