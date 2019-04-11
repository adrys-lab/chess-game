package com.adryslab.chess;

import com.adryslab.chess.controller.BoardController;
import com.adryslab.chess.controller.BoardMatrixWrapper;
import com.adryslab.chess.controller.GameController;
import com.adryslab.chess.controller.PlayerController;
import com.adryslab.chess.model.Player;
import com.adryslab.chess.model.piece.Colour;
import com.adryslab.chess.resources.Messages;

import java.util.Scanner;

public class RunChess {

    public static void main(String[] args) {

        System.out.println(Messages.WELCOME);
        System.out.println(Messages.WELCOME_START);
        System.out.println(Messages.INFO);

        final Scanner scanner = new Scanner(System.in);

        System.out.println(Messages.INFO_COORDINATES);

        System.out.println(Messages.STARTING_FIRST_PLAYER_NAME);

        final String playerOneName = scanner.nextLine();

        System.out.println(Messages.STARTING_SECOND_PLAYER_NAME);

        final String PlayerTwoName = scanner.nextLine();

        final PlayerController playerController = new PlayerController(new Player(playerOneName, Colour.WHITE, true), new Player(PlayerTwoName, Colour.BLACK, false));
        final BoardController boardController = new BoardController(BoardMatrixWrapper.getInstance());
        final GameController gameController = new GameController(playerController, boardController);

        System.out.println(Messages.LETS_START);

        gameController.start();

    }
}
