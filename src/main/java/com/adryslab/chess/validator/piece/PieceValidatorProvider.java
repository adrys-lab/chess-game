package com.adryslab.chess.validator.piece;

import com.adryslab.chess.model.cell.Cell;
import com.adryslab.chess.model.piece.type.PieceType;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;

import java.util.Map;
import java.util.Set;

public class PieceValidatorProvider {

    private static final Map<PieceType, Set<TriValidator<Cell, Cell, Cell[][], String>>> PIECE_MOVE_VALIDATORS = new ImmutableMap.Builder<PieceType, Set<TriValidator<Cell, Cell, Cell[][], String>>>()
            .put(PieceType.QUEEN, ImmutableSet.of(StraightValidator.getInstance(), DiagonalValidator.getInstance()))
            .put(PieceType.KING, ImmutableSet.of(KingValidator.getInstance()))
            .put(PieceType.PAWN, ImmutableSet.of(PawnValidator.getInstance()))
            .put(PieceType.ROOK, ImmutableSet.of(StraightValidator.getInstance()))
            .put(PieceType.KNIGHT, ImmutableSet.of(KnightValidator.getInstance()))
            .put(PieceType.BISHOP, ImmutableSet.of(DiagonalValidator.getInstance()))
            .build();

    public static Set<TriValidator<Cell, Cell, Cell[][], String>> getPieceMoveValidators(final PieceType pieceType) {
        return PIECE_MOVE_VALIDATORS.get(pieceType);
    }
}
