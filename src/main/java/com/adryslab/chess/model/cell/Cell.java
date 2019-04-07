package com.adryslab.chess.model.cell;

import com.adryslab.chess.model.Position;
import com.adryslab.chess.model.annotation.Immutable;
import com.adryslab.chess.model.piece.Slot;

/**
 * Main Class domain which represents a CELL wrap in the board.
 * It's composed by a position and an Slot.
 * It's Immutable.
 */
@Immutable
public class Cell {

    private Position position;
    private Slot slot;

    private Cell(Position position, final Slot cellContent) {
        this.position = position;
        this.slot = cellContent;
    }

    public Slot getSlot() {
        return slot;
    }

    public Position getPosition() {
        return position;
    }

    public static Cell of(final Position position, final Slot slot) {
        return new Cell(position, slot);
    }


}
