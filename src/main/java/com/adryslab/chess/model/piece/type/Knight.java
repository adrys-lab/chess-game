package com.adryslab.chess.model.piece.type;

import com.adryslab.chess.model.piece.Colour;
import com.adryslab.chess.model.piece.Slot;

public class Knight extends Slot {

    public Knight(final Colour colour) {
        super(PieceType.KNIGHT, colour);
    }
}
