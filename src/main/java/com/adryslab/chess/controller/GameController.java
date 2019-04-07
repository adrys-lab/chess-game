package com.adryslab.chess.controller;

import com.adryslab.chess.mapper.CoordinateMapper;
import com.adryslab.chess.model.Player;
import com.adryslab.chess.model.Position;
import com.adryslab.chess.model.Result;
import com.adryslab.chess.model.cell.Cell;
import com.adryslab.chess.model.state.GameState;
import com.adryslab.chess.resources.Constants;
import com.adryslab.chess.resources.Messages;
import com.adryslab.chess.validator.chain.BiValidatorChain;
import com.adryslab.chess.validator.composed.ComposedValidatorProvider;
import com.adryslab.chess.validator.simple.SimpleValidatorProvider;

import java.util.Scanner;

/**
 * Main controller for the Game logic.
 * Handles outputs and reads -> interactions with the user.
 * Responsible to print status and board.
 */
public class GameController {

    private final PlayerController playerController;
    private final BoardController boardController;

    private boolean endGame;
    private long turn;

    // Preferred way to inject dependencies via constructor, to help its testing.
    public GameController(final PlayerController playerController, final BoardController boardController) {
        this.turn = 0;
        this.playerController = playerController;
        this.boardController = boardController;
    }

    /**
     * Main method that starts the game and handles interactions until game finishes.
     * Hint --> Long method. Personally i don't like long methods, but hard to achieve with interactions with the user -> Split and Win.
     */
    public void start() {
        final Scanner scanner = new Scanner(System.in);

        // construct first BiValidator chain, used for the first user.
        // only first user as the second no needs getPlayerColourValidator --> second user can move to an empty cell.
        // build out of the loop -> try minimize objects allocations.
        final BiValidatorChain<Cell, Player, String> firstPlayerBiValidatorChain = new BiValidatorChain<Cell, Player, String>()
                .addValidator(ComposedValidatorProvider.getPlayerInTurnValidator())
                .addValidator(ComposedValidatorProvider.getPlayerColourValidator());

        final BiValidatorChain<Cell, Player, String> secondPlayerBiValidatorChain = new BiValidatorChain<Cell, Player, String>()
                .addValidator(ComposedValidatorProvider.getPlayerInTurnValidator());

        while (!endGame) {

            boardController.drawBoard();

            System.out.println("\n");

            System.out.println(String.format(Messages.ROUND_ENTER_COORDINATES_START, turn, playerController.getPlayerInTurn().getName()));

            final Cell cellStart = readAndValidate(scanner, firstPlayerBiValidatorChain);

            // the first piece selection can not be empty slot.
            final Result<String> result = SimpleValidatorProvider.getEmptyPositionValidator().validate(cellStart).ifFailure(System.out::println);

            if (result.isValid()) {

                showSelectedAndAskDestination(cellStart);

                final Cell cellDestination = readAndValidate(scanner, secondPlayerBiValidatorChain);

                System.out.println("\n");

                // at this point, origin cell and destination cell are corrects.
                // is time to execute the move over the Board.
                final Result<String> moveResult = boardController.move(cellStart, cellDestination);

                // if the move result is failure, it prints the given result without passing turn logic:
                // ---> therefore, print the given failure message and the user has to repeat the entries correctly.
                // this can happen, ie. if the movement result for the given piece is not correct.
                if(moveResult.isFailure()) {
                    moveResult.ifFailure(System.out::println);
                } else {
                    //if movement is correct, check the GameState to maybe finish the game.
                    moveResult.ifValid(System.out::println);
                    if(boardController.getGameState() == GameState.FINISHED) {
                        System.out.println(String.format(Messages.WINNER_PLAYER_GAME_OVER, playerController.getPlayerInTurn().getName()));
                        playerController.playerInTurnIsTheWinner();
                        endGame = true;
                        boardController.drawBoard();
                    } else {
                        playerController.nextTurn();
                        turn++;
                    }
                }
            }
        }
    }

    private Cell readAndValidate(final Scanner scanner, final BiValidatorChain<Cell, Player, String> biValidatorChain) {

        String input;
        Result<String> validationResult = Result.failure();
        Cell cell = null;

        //Loop consuming input from user until the input is valid.
        while(!validationResult.isValid() && !endGame) {

            input = scanner.nextLine();

            if(input.equals(Constants.FINISH_GAME_INPUT)) {
                endGame = true;
                break;
            }

            validationResult = SimpleValidatorProvider.getEnteredPositionValidator().validate(input);

            if (validationResult.isFailure()) {
                validationResult.ifFailure(System.out::println);
            } else {
                final Position positionStart = CoordinateMapper.map(input);
                cell = boardController.getBoard()[positionStart.getY()][positionStart.getX()];

                //executes the validator chain.
                // if there's just one error, validation result will be failure.
                validationResult = getBiValidationResult(biValidatorChain, cell);
            }
        }

        return cell;
    }

    private Result<String> getBiValidationResult(BiValidatorChain<Cell, Player, String> biValidatorChain, Cell cell) {
        return biValidatorChain.evaluateAll(cell, playerController.getPlayerInTurn()).getResult()
                .getEvaluatorMessages()
                .stream()
                .peek(validation -> validation.ifFailure(System.out::println))
                .filter(Result::isFailure)
                .findFirst()
                .orElseGet(Result::valid);
    }

    private void showSelectedAndAskDestination(Cell cellStart) {
        System.out.println("\n");
        System.out.println(String.format(Messages.SELECTED_PIECE, cellStart.getSlot().getCellContent().getName(), cellStart.getSlot().getColour().name()));
        System.out.println("\n");
        System.out.println(String.format(Messages.ROUND_ENTER_COORDINATES_DESTINATION, cellStart.getSlot().getCellContent().getName()));
        System.out.println("\n");
    }
}
