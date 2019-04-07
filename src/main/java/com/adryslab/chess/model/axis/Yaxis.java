package com.adryslab.chess.model.axis;

import com.adryslab.chess.model.annotation.Immutable;

import java.util.Arrays;
import java.util.Optional;

/**
 * Enum used to determine, for a given letter, which Index it belongs in the Board.
 */
@Immutable
public enum Yaxis implements Axis {

    A(7),
    B(6),
    C(5),
    D(4),
    E(3),
    F(2),
    G(1),
    H(0);

    private final int number;

    Yaxis(int number) {
        this.number = number;
    }

    @Override
    public int getIndex() {
        return number;
    }

    public static Optional<Yaxis> fromNumber(final int number) {
        return Arrays.stream(values()).filter(xaxis -> xaxis.getIndex() == number).findFirst();
    }
}
