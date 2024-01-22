package app.interpreter;

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

import com.oozinoz.machine.*;
import com.oozinoz.robotInterpreter2.*;

/**
*  Show the construction and use of a (tiny) interpreter that shuts
*  down all the machines at a particular plant.
*/
// TODO: 1/22/2024 Interpreter Design Pattern - ForCommand
//For example, here is a program
//that composes an interpreter object that shuts down all the
//machines in a factory
class ShowDown {
    public static void main(String[] args) {
        MachineComposite dublin = OozinozFactory.dublin();
        Variable v = new Variable("machine");
        Command c = new ForCommand(dublin, v, new ShutDownCommand(v));
        c.execute();
    }
}