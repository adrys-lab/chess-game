package com.adryslab.chess.mapper;

import com.adryslab.chess.model.cell.CellContent;
import com.adryslab.chess.model.exception.IllegalCellContentMove;
import com.adryslab.chess.model.piece.type.PieceType;
import com.adryslab.chess.resources.ErrorMessages;

/**
 * Mapper which acts as Function<T, P>
 * receives a cell content, and returns a PieceType.java
 * Used for obtain the PieceType of a concrete cell by controlling its empty state.
 */
public class PieceTypeMapper {

    public static PieceType map(final CellContent cellContent) {

        if(cellContent.isEmpty()) {
            throw new IllegalCellContentMove(ErrorMessages.IMPOSSIBLE_GET_PIECE_FROM_EMPTY);
        }

        return PieceType.valueOf(cellContent.getName());
    }
}
