package com.adryslab.chess.model.piece.type;

import com.adryslab.chess.model.piece.Colour;
import com.adryslab.chess.model.piece.Slot;

public class Pawn extends Slot {

    public Pawn(final Colour colour) {
        super(PieceType.PAWN, colour);
    }
}
