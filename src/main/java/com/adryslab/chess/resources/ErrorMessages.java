package com.adryslab.chess.resources;

/**
 * Contains the list of errors displayed in the application.
 */
public class ErrorMessages {

    public static final String INTRODUCED_MOVEMENT_SECOND_CHAR_OVERFLOW = "Second position char must be between 1 and 8.";
    public static final String INTRODUCED_MOVEMENT_FIRST_CHAR_OVERFLOW = "First position char must be between A and H.";
    public static final String INTRODUCED_MOVEMENT_LENGTH_NO_MATCH = "Position Length must be of 2. From A1 to H8.";
    public static final String INTRODUCED_MOVEMENT_COLOUR_NO_MATCH = "The chosen piece %s with colour %s, doesnt correspond to the player %s with colour %s";
    public static final String INTRODUCED_MOVEMENT_EMPTY_CELL = "The chosen position is empty. Please select correct position.";
    public static final String INTRODUCED_MOVEMENT_PLAYER_IS_NOT_IN_TURN = "The Player is not in turn";
    public static final String IMPOSSIBLE_GET_PIECE_FROM_EMPTY = "Impossible get PieceType from Empty Cell Content.";
    public static final String IMPOSSIBLE_GET_YAXIS_NUMBER_AT = "Impossible get Y Axis number value from given %s.";

    //PIECES
    public static final String DESTINATION_IS_SAME_COLOUR = "Destination position has a piece of the same colour than the original.";
    public static final String ORIGIN_AND_DESTINATION_ARE_THE_SAME = "Destination position is the same than original. Please choose a correct destination.";
    public static final String PAWN_INCORRECT_MOVE = "Pawn can only move ahead, one by one, two at first turn or crossing one for kill.";
    public static final String KNIGHT_INCORRECT_MOVE = "Knight can only move doing L's movements.";
    public static final String DIAGONAL_INCORRECT_MOVE = "Incorrect Diagonal movement for %s.";
    public static final String STRAIGHT_INCORRECT_MOVE = "Incorrect Straight movement for %s.";
    public static final String KING_INCORRECT_MOVE = "King can only be moved one by one.";
}
