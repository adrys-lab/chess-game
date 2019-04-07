package com.adryslab.chess.model;

import com.adryslab.chess.model.piece.Colour;

/**
 * Domain model which represents a player.
 * A player is defined by its name and pieces colour.
 * It will be modifying the state by if player is in turn or is the winner.
 */
public class Player {

    private final String name;
    private final Colour colour;

    private boolean isWinner;
    private boolean isInTurn;

    public Player(final String name, final Colour colour, final boolean isInTurn) {
        this.name = name;
        this.colour = colour;
        this.isInTurn = isInTurn;
        this.isWinner = false;
    }

    public String getName() {
        return name;
    }

    public Colour getColour() {
        return colour;
    }

    public boolean isWinner() {
        return isWinner;
    }

    public boolean isInTurn() {
        return isInTurn;
    }

    public void setWinner(boolean winner) {
        isWinner = winner;
    }

    public void setInTurn(boolean inTurn) {
        isInTurn = inTurn;
    }
}
