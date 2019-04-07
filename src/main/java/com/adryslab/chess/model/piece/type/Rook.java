package com.adryslab.chess.model.piece.type;

import com.adryslab.chess.model.piece.Colour;
import com.adryslab.chess.model.piece.Slot;

public class Rook extends Slot {

    public Rook(final Colour colour) {
        super(PieceType.ROOK, colour);
    }
}
