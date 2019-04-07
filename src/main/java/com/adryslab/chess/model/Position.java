package com.adryslab.chess.model;

/**
 * Represents a position in the Board.
 * It's used to locate Cells in the Board and also to link a piece with a place.
 *
 * Prefered to be immutable, but setters are needed for Board Swap functions.
 */
public class Position {

    private int y;
    private int x;

    public Position(final int y, final int x) {
        this.y = y;
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public int getX() {
        return x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setX(int x) {
        this.x = x;
    }

    // a builder pattern could be provided too, but since its mutable, i prefered to not use builder.
    public static Position of(final int y, final int x) {
        return new Position(y, x);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        return prime * y * x;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        Position other = (Position) obj;
        return this.y == other.y && this.x == other.x;
    }
}
