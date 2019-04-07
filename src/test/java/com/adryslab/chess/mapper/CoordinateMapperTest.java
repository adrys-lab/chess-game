package com.adryslab.chess.mapper;

import com.adryslab.chess.model.Position;
import org.junit.Assert;
import org.junit.Test;

public class CoordinateMapperTest {

    @Test
    public void assertA1() {
        Assert.assertEquals(Position.of(7, 0), CoordinateMapper.map("A1"));
    }

    @Test
    public void assertB2() {
        Assert.assertEquals(Position.of(6, 1), CoordinateMapper.map("B2"));
    }

    @Test
    public void assertH8() {
        Assert.assertEquals(Position.of(0, 7), CoordinateMapper.map("H8"));
    }

    @Test
    public void assertE5() {
        Assert.assertEquals(Position.of(3, 4), CoordinateMapper.map("E5"));
    }

}
