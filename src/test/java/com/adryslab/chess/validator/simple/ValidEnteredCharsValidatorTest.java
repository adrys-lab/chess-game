package com.adryslab.chess.validator.simple;

import org.junit.Assert;
import org.junit.Test;

public class ValidEnteredCharsValidatorTest {

    @Test
    public void expectNotValidWhenLengthOverTwo() {
        Assert.assertFalse(SimpleValidatorProvider.getEnteredPositionValidator().validate("H").isValid());
    }

    @Test
    public void expectNotValidWhenLengthBelowTwo() {
        Assert.assertFalse(SimpleValidatorProvider.getEnteredPositionValidator().validate("A123908").isValid());
    }

    @Test
    public void expectNotValidWhenLetterOverflow() {
        Assert.assertFalse(SimpleValidatorProvider.getEnteredPositionValidator().validate("I1").isValid());
    }

    @Test
    public void expectNotValidWhenLetterOverflowMore() {
        Assert.assertFalse(SimpleValidatorProvider.getEnteredPositionValidator().validate("P1").isValid());
    }

    @Test
    public void expectNotValidWhenNumberOverflow() {
        Assert.assertFalse(SimpleValidatorProvider.getEnteredPositionValidator().validate("A9").isValid());
    }

    @Test
    public void expectNotValidWhenNumberUnder() {
        Assert.assertFalse(SimpleValidatorProvider.getEnteredPositionValidator().validate("A0").isValid());
    }

}
