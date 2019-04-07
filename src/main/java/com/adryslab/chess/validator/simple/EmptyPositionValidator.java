package com.adryslab.chess.validator.simple;

import com.adryslab.chess.model.Result;
import com.adryslab.chess.model.annotation.Singleton;
import com.adryslab.chess.model.cell.Cell;
import com.adryslab.chess.resources.ErrorMessages;

@Singleton
public class EmptyPositionValidator implements Validator<Cell, String> {

    private static Validator<Cell, String> instance;

    // no construct allowed -> Singleton pattern
    private EmptyPositionValidator() {
    }

    /*
     * Apply Double Checked Locking principle.
     * Singleton Pattern -> Lazy load.
     */
    static Validator<Cell, String> getInstance() {
        if(instance == null){
            synchronized (EmptyPositionValidator.class) {
                if(instance == null){
                    instance = new EmptyPositionValidator();
                }
            }
        }
        return instance;
    }

    /**
     * validates whether the chosen cell is empty or not
     * @param cell
     * @return
     */
    @Override
    public Result<String> validate(final Cell cell) {

        if(cell.getSlot().getCellContent().isEmpty()) {
            return Result.failure(ErrorMessages.INTRODUCED_MOVEMENT_EMPTY_CELL);
        }

        return Result.valid();
    }
}
