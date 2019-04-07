package com.adryslab.chess.validator.simple;

import com.adryslab.chess.model.Result;
import com.adryslab.chess.model.annotation.Singleton;
import com.adryslab.chess.resources.ErrorMessages;

@Singleton
public class ValidEnteredCharsValidator implements Validator<String, String> {

    // no construct allowed -> Singleton pattern
    private ValidEnteredCharsValidator() {
    }

    private static Validator<String, String> instance;

    /*
     * Apply Double Checked Locking principle.
     * Singleton Pattern -> Lazy load.
     */
    static Validator<String, String> getInstance() {
        if(instance == null){
            synchronized (ValidEnteredCharsValidator.class) {
                if(instance == null){
                    instance = new ValidEnteredCharsValidator();
                }
            }
        }
        return instance;
    }

    /**
     * validates if the entered chars for the position are correct or not, understanding correct as valid ranges.
     *
     * only 2 chars allowed.
     *
     * and range A1-H8.
     *
     * @param position is the entered cell position.
     *
     * @return
     */
    @Override
    public Result<String> validate(final String position) {

        if(position.length() != 2) {
            return Result.failure(ErrorMessages.INTRODUCED_MOVEMENT_LENGTH_NO_MATCH);
        }

        char first = position.charAt(0);
        char second = position.charAt(1);

        if(first < 65 || first > 72) {
            return Result.failure(ErrorMessages.INTRODUCED_MOVEMENT_FIRST_CHAR_OVERFLOW);
        }

        if(second < 49 || second > 56) {
            return Result.failure(ErrorMessages.INTRODUCED_MOVEMENT_SECOND_CHAR_OVERFLOW);
        }

        return Result.valid();
    }
}
