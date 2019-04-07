package com.adryslab.chess.resources;

/**
 * Used for View-side to represent the colour of each peace in Console.
 */
public enum ColourRepresentation {

    BLACK("\u001B[30m"),
    WHITE("\u001B[34m"),
    NA("\u001B[32m");

    final String ansiColour;

    ColourRepresentation(final String ansiColour) {
        this.ansiColour = ansiColour;
    }

    public String getAnsiColour() {
        return ansiColour;
    }

}
