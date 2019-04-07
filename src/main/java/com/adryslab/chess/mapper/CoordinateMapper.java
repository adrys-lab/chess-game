package com.adryslab.chess.mapper;

import com.adryslab.chess.model.Position;
import com.adryslab.chess.model.axis.Yaxis;

/**
 * Mapper which acts as Function<T, P>
 * receives an String coordinate, and returns a Position.java object.
 */
public class CoordinateMapper {

    public static Position map(final String coordinate) {
        return new Position(Yaxis.valueOf(String.valueOf(coordinate.charAt(0))).getIndex(),
                Integer.valueOf(String.valueOf(coordinate.charAt(1))) - 1);
    }
}
