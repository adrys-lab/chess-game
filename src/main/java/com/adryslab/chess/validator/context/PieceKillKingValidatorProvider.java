package com.adryslab.chess.validator.context;

import com.adryslab.chess.model.cell.Cell;
import com.adryslab.chess.model.piece.type.PieceType;
import com.adryslab.chess.validator.composed.BiValidator;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;

import java.util.Map;
import java.util.Set;

public class PieceKillKingValidatorProvider {

    private static final Map<PieceType, Set<BiValidator<Cell, Cell[][], String>>> PIECE_KILL_KING_VALIDATORS = new ImmutableMap.Builder<PieceType, Set<BiValidator<Cell, Cell[][], String>>>()
            .put(PieceType.QUEEN, ImmutableSet.of(StraightKillKingValidator.getInstance(), DiagonalKillKingValidator.getInstance()))
            .put(PieceType.KING, ImmutableSet.of(KingKillKingValidator.getInstance()))
            .put(PieceType.PAWN, ImmutableSet.of(PawnKillKingValidator.getInstance()))
            .put(PieceType.ROOK, ImmutableSet.of(StraightKillKingValidator.getInstance()))
            .put(PieceType.KNIGHT, ImmutableSet.of(KnightKillKingValidator.getInstance()))
            .put(PieceType.BISHOP, ImmutableSet.of(DiagonalKillKingValidator.getInstance()))
            .build();

    public static Set<BiValidator<Cell, Cell[][], String>> getPieceMoveValidators(final PieceType pieceType) {
        return PIECE_KILL_KING_VALIDATORS.get(pieceType);
    }
}
