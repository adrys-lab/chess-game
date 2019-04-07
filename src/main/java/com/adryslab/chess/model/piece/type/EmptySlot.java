package com.adryslab.chess.model.piece.type;

import com.adryslab.chess.model.piece.Colour;
import com.adryslab.chess.model.piece.Slot;

public class EmptySlot extends Slot {

    public EmptySlot() {
        super(EmptyCell.EMPTY, Colour.NA);
    }
}
