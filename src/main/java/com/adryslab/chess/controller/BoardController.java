package com.adryslab.chess.controller;

import com.adryslab.chess.mapper.PieceTypeMapper;
import com.adryslab.chess.model.Position;
import com.adryslab.chess.model.Result;
import com.adryslab.chess.model.cell.Cell;
import com.adryslab.chess.model.piece.type.EmptySlot;
import com.adryslab.chess.model.piece.type.PieceType;
import com.adryslab.chess.model.state.GameState;
import com.adryslab.chess.resources.Messages;
import com.adryslab.chess.validator.context.PieceKillKingValidatorProvider;
import com.adryslab.chess.validator.piece.PieceValidatorProvider;
import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 *  Controller to handle all the Board interactions besides the user interactions.
 *  All that happens between pieces is handled in this Controller.
 */
public class BoardController {

    private final BoardMatrix boardMatrix;

    private GameState gameState = GameState.ONGOING;

    // Preferred way to inject dependencies via constructor, to help its testing.
    public BoardController(final BoardMatrix boardMatrix) {
        this.boardMatrix = boardMatrix;
    }

    /**
     * Executes the operation of Move from origin to destination.
     * It triggers all validations for the given Piece contained in cellStart
     * @param cellStart the original cell position
     * @param cellDestination the desired destination for the original cell.
     * @return if the move is valid or not, wrapping Messages
     */
    Result<String> move(final Cell cellStart, final Cell cellDestination) {

        //Obtain the piece type wrapped in the original cell. --> that's the piece the user wants to move.
        final PieceType pieceType = PieceTypeMapper.map(cellStart.getSlot().getCellContent());

        //Once the pieces is obtained, execute all PieceValidators for the given piece Type.
        //These validators will return if the intended movement is valid for the Given piece.
        final List<Result<String>> results = PieceValidatorProvider.getPieceMoveValidators(pieceType)
                .stream()
                .map(triValidator -> triValidator.validate(cellStart, cellDestination, getBoard()))
                .collect(Collectors.toList());

        //If at least one movement of the given movement results is valid, means the movement is valid (ie. Queen can return invalid Diagonal, but valid straight)
        for(Result<String> result : results) {
            if (result.isValid()) {

                //if at least one is valid, we can proceed to swap the pieces in the board.
                final Result<String> swapResult = this.swap(cellStart, cellDestination);

                //after swap pieces, proceed to evaluate the whole board and check if it exists any CHECK possibility.
                return combineSwapAndCheckResults(swapResult);
            }
        }

        return results.stream().filter(Result::isFailure).findFirst().orElseGet(Result::failure);
    }

    /**
     * Swap the pieces in the board.
     * Original cell now will be placed in the destination.
     * If destination contained an enemy piece, means theres a Kill --> therefore should be removed and replaced by an empty slot.
     *
     * here
     * @param cellStart the original cell position
     * @param cellDestination the desired destination for the original cell.
     * @return if the move is valid or not, wrapping Messages
     */
    private Result<String> swap(final Cell cellStart, final Cell cellDestination) {

        gameState = GameState.ONGOING;

        String message = Messages.CORRECT_MOVE;

        int startX = cellStart.getPosition().getX();
        int startY = cellStart.getPosition().getY();
        int destX = cellDestination.getPosition().getX();
        int destY = cellDestination.getPosition().getY();

        Cell newCellDestination = cellDestination;

        if(!cellDestination.getSlot().getCellContent().isEmpty()) {
            final PieceType pieceType = PieceTypeMapper.map(cellDestination.getSlot().getCellContent());
            newCellDestination = Cell.of(Position.of(destY, destX), new EmptySlot());
            if(pieceType == PieceType.KING) {
                message = Messages.WINNER_GAME_OVER;
                gameState = GameState.FINISHED;
            } else {
                message = String.format(Messages.KILLED_PIECE, cellStart.getSlot().getCellContent().getName(), cellDestination.getSlot().getCellContent().getName());
            }
        }

        getBoard()[startY][startX] = newCellDestination;
        getBoard()[destY][destX] = cellStart;

        newCellDestination.getPosition().setX(cellStart.getPosition().getX());
        newCellDestination.getPosition().setY(cellStart.getPosition().getY());

        cellStart.getPosition().setX(destX);
        cellStart.getPosition().setY(destY);

        return Result.valid(message);
    }

    /**
     * This method combines the SWAP and CHECK results.
     * If the game is not finished, it will trigger the CHECK validations and, if any result exists, will combine swap and check messages.
     * if not, only SWAP message will be returned.
     * @return combination of messages or Swap result.
     */
    private Result<String> combineSwapAndCheckResults(final Result<String> swapResult) {
        if (gameState != GameState.FINISHED) {
            String checkMessages = getCheckStatusResult();

            if(!checkMessages.isEmpty()) {
                gameState = GameState.CHECK;
                return swapResult.map(message -> message.concat(checkMessages));
            }
        }

        return swapResult;
    }

    /**
     * This method triggers all PieceKillKingValidators for each piece in the board.
     * The aim is to check if it exists eny CHECK possibility anfter the movement has been released in the board.
     * For each piece in the board, discarding empties, it gets the piece, and evaluates if there's any KING enemy threatened.
     * If there's, it concatenates all threatened possibilities to be returned to the user.
     * @return all the CHECK messages obtained from validation.
     */
    private String getCheckStatusResult() {
        return Arrays.stream(getBoard(), 0, 8)
                .map(cell -> Arrays.stream(cell, 0, 8)
                        .filter(cellSlot -> !cellSlot.getSlot().getCellContent().isEmpty())
                        .map(cellSlot -> PieceKillKingValidatorProvider.getPieceMoveValidators(PieceTypeMapper.map(cellSlot.getSlot().getCellContent()))
                                .stream()
                                .map(validator -> validator.validate(cellSlot, getBoard()))
                                .filter(Result::isValid)
                                .map(validCheck -> validCheck.getInstance().orElse(""))
                                .filter(StringUtils::isNotBlank)
                                .reduce("", String::concat))
                        .filter(StringUtils::isNotBlank)
                        .reduce("", String::concat))
                .filter(StringUtils::isNotBlank)
                .reduce(System.lineSeparator(), String::concat);
    }

    GameState getGameState() {
        return gameState;
    }

    void drawBoard() {
        boardMatrix.drawBoard();
    }

    Cell[][] getBoard() {
        return boardMatrix.getBoardMatrix();
    }
}
