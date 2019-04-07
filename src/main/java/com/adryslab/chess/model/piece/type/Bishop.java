package com.adryslab.chess.model.piece.type;

import com.adryslab.chess.model.piece.Colour;
import com.adryslab.chess.model.piece.Slot;

public class Bishop extends Slot {

    public Bishop(final Colour colour) {
        super(PieceType.BISHOP, colour);
    }
}
