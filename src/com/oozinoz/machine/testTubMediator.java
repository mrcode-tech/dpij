package com.oozinoz.machine;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

// TODO: 1/7/2024  Mediator design pattern - Mediators of Relational Integrity
public class testTubMediator {

    @Test
    public void testLocationChange() {
        TubMediator mediator = new TubMediator();
        Tub t = new Tub("T403", mediator);
        Machine m1 = new Fuser(1001, mediator);
        Machine m2 = new Fuser(1002, mediator);
        t.setLocation(m1);
        assertTrue(m1.getTubs().contains(t));
        assertTrue(!m2.getTubs().contains(t));
        t.setLocation(m2);
        assertFalse(m1.getTubs().contains(t));
        assertTrue(m2.getTubs().contains(t));
    }
}
