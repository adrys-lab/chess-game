package com.adryslab.chess.model.piece.type;

import com.adryslab.chess.model.piece.Colour;
import com.adryslab.chess.model.piece.Slot;

public class King extends Slot {

    public King(final Colour colour) {
        super(PieceType.KING, colour);
    }
}
