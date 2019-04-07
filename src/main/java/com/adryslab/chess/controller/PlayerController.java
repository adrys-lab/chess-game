package com.adryslab.chess.controller;

import com.adryslab.chess.model.Player;

/**
 * Wraps the players logic, handling it's interaction with the game.
 */
public class PlayerController {

    private Player player1;
    private Player player2;

    public PlayerController(final Player player1, final Player player2) {
        this.player1 = player1;
        this.player2 = player2;
    }

    Player getPlayerInTurn() {
        return player1.isInTurn() ? player1 : player2;
    }

    void nextTurn() {
        player1.setInTurn(!player1.isInTurn());
        player2.setInTurn(!player2.isInTurn());
    }

    void playerInTurnIsTheWinner() {
        player1.setWinner(player1.isInTurn());
        player2.setWinner(player2.isInTurn());
    }
}
