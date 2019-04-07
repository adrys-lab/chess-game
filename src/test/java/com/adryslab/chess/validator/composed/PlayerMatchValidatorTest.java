package com.adryslab.chess.validator.composed;

import com.adryslab.chess.model.Player;
import com.adryslab.chess.model.Position;
import com.adryslab.chess.model.cell.Cell;
import com.adryslab.chess.model.piece.Colour;
import com.adryslab.chess.model.piece.type.Pawn;
import org.junit.Assert;
import org.junit.Test;

public class PlayerMatchValidatorTest {

    @Test
    public void expectValidAnswer() {
        Assert.assertTrue(ComposedValidatorProvider.getPlayerColourValidator().validate(Cell.of(Position.of(0, 0), new Pawn(Colour.BLACK)), new Player("", Colour.BLACK, true)).isValid());
    }

    @Test
    public void notValidWhenDifferentColour() {
        Assert.assertFalse(ComposedValidatorProvider.getPlayerColourValidator().validate(Cell.of(Position.of(0, 0), new Pawn(Colour.BLACK)), new Player("", Colour.WHITE, true)).isValid());
    }
}
