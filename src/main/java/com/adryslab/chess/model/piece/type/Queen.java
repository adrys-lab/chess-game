package com.adryslab.chess.model.piece.type;

import com.adryslab.chess.model.piece.Colour;
import com.adryslab.chess.model.piece.Slot;

public class Queen extends Slot {

    public Queen(final Colour colour) {
        super(PieceType.QUEEN, colour);
    }
}
