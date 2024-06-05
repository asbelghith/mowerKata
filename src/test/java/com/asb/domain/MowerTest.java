package com.asb.domain;

import com.asb.domain.entity.Mower;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MowerTest {

    @Test
    public void testMowerConstructorWithInstructions() {
        Mower mower = new Mower(1, 2, 'N', "GAGAGAGAA");
        assertEquals(1, mower.getX());
        assertEquals(2, mower.getY());
        assertEquals('N', mower.getOrientation());
        assertEquals("GAGAGAGAA", mower.getInstructions());
    }

    @Test
    public void testMowerConstructorWithoutInstructions() {
        Mower mower = new Mower(3, 4, 'S');
        assertEquals(3, mower.getX());
        assertEquals(4, mower.getY());
        assertEquals('S', mower.getOrientation());
        assertEquals(null, mower.getInstructions());
    }
}

