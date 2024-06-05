package com.asb.domain;

import com.asb.domain.entity.Lawn;
import com.asb.domain.entity.Mower;
import com.asb.domain.service.MowItNowService;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MowItNowServiceTest {

    @Test
    public void testProcessInstructions() {

        Lawn lawn = new Lawn(5,5);

        Mower mower1 = new Mower(1, 2, 'N');
        mower1.setInstructions("GAGAGAGAA");
        lawn.addMower(mower1);

        Mower mower2 = new Mower(3, 3, 'E');
        mower2.setInstructions("AADAADADDA");
        lawn.addMower(mower2);

        List<Mower> result = MowItNowService.processInstructions(lawn);
        // Assertions
        assertEquals(2, result.size());

        assertEquals("Mower(x=1, y=3, orientation=N, instructions=GAGAGAGAA)", result.get(0).toString());
        assertEquals("Mower(x=5, y=1, orientation=E, instructions=AADAADADDA)", result.get(1).toString());
    }

    @Test
    public void testTurnLeft() {
        Mower mower = new Mower(1, 1, 'N');
        MowItNowService.turnLeft(mower);
        assertEquals('W', mower.getOrientation());
    }

    @Test
    public void testTurnRight() {
        Mower mower = new Mower(1, 1, 'N');
        MowItNowService.turnRight(mower);
        assertEquals('E', mower.getOrientation());
    }
}
