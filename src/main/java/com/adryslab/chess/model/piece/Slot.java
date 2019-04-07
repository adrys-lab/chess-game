package com.adryslab.chess.model.piece;

import com.adryslab.chess.model.cell.CellContent;

/**
 * An slot contains a Cell Content, which can be a Piece or an Empty cell.
 * it contains also a colour, represented by the piece colour or NA for empty.
 */
public abstract class Slot {

    private final CellContent cellContent;
    private final Colour colour;

    public Slot(CellContent cellContent, final Colour colour) {
        this.cellContent = cellContent;
        this.colour = colour;
    }

    public CellContent getCellContent() {
        return cellContent;
    }

    public Colour getColour() {
        return colour;
    }
}
