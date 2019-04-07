package com.adryslab.chess.validator.chain;

import com.adryslab.chess.model.Player;
import com.adryslab.chess.model.Position;
import com.adryslab.chess.model.cell.Cell;
import com.adryslab.chess.model.piece.Colour;
import com.adryslab.chess.model.piece.type.Pawn;
import com.adryslab.chess.validator.composed.ComposedValidatorProvider;
import org.junit.Assert;
import org.junit.Test;

public class BiValidatorChainTest {

    @Test
    public void assertTwoErrors() {

        final BiValidatorChain<Cell, Player, String> biValidatorChain = new BiValidatorChain<Cell, Player, String>()
                .addValidator(ComposedValidatorProvider.getPlayerColourValidator())
                .addValidator(ComposedValidatorProvider.getPlayerInTurnValidator());

        final Cell cell = Cell.of(Position.of(0, 0), new Pawn(Colour.BLACK));
        final Player player = new Player("", Colour.WHITE, false);

        Assert.assertEquals(2, biValidatorChain.evaluateAll(cell, player).getResult().getEvaluatorMessages().size());
        Assert.assertTrue(biValidatorChain.evaluateAll(cell, player).getResult().hasMessages());

    }

    @Test
    public void assertOneErrors() {

        final BiValidatorChain<Cell, Player, String> biValidatorChain = new BiValidatorChain<Cell, Player, String>()
                .addValidator(ComposedValidatorProvider.getPlayerColourValidator())
                .addValidator(ComposedValidatorProvider.getPlayerInTurnValidator());

        final Cell cell = Cell.of(Position.of(0, 0), new Pawn(Colour.BLACK));
        final Player player = new Player("", Colour.BLACK, false);

        Assert.assertEquals(1, biValidatorChain.evaluateAll(cell, player).getResult().getEvaluatorMessages().size());
        Assert.assertTrue(biValidatorChain.evaluateAll(cell, player).getResult().hasMessages());

    }
}
