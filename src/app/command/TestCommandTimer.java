package app.command;

/*
 * Copyright (c) 2001, 2005. Steven J. Metsker.
 * 
 * Steve Metsker makes no representations or warranties about
 * the fitness of this software for any particular purpose, 
 * including the implied warranty of merchantability.
 *
 * Please use this software as you wish with the sole
 * restriction that you may not claim that you wrote it.
 */

import com.oozinoz.robotInterpreter.Command;
import com.oozinoz.utility.CommandTimer;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;
// TODO: 1/22/2024 Command Design pattern - Using COMMAND to Supply a Service
//The COMMAND pattern lets you encapsulate a request in an object,
//allowing you to manage method calls as objects, passing them and
//invoking them when the timing or conditions are right
public class TestCommandTimer {
    @Test
    public void testSleep() {

        //This abstract class represents a hierarchy of classes
        //that encapsulate commands. A command object is a request
        //that is dormant until a caller asks it to execute.
        Command doze = new Command() {
            public void execute() {
                try {
                    Thread.sleep(2000 + Math.round(10 * Math.random()));
                } catch (InterruptedException ignored) {
                }
            }
        };

        long actual = CommandTimer.time(doze);

        long expected = 2000;
        long delta = 10;

        assertTrue(
                expected - delta <= actual && actual <= expected + delta,
                "Should be " + expected + " +/- " + delta + " ms" + " but was " + actual + " ms");
    }
}