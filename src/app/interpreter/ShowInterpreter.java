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
import com.oozinoz.robotInterpreter.*;

/**
 * Demonstrate an initial carry command.
 */
// TODO: 1/22/2024 Interpreter Design Pattern
public class ShowInterpreter {
    public static void main(String[] args) {
        MachineComposite dublin = OozinozFactory.dublin();
        ShellAssembler assembler = (ShellAssembler) dublin.find("ShellAssembler:3302");
        StarPress press = (StarPress) dublin.find("StarPress:3404");
        Fuser fuser = (Fuser) dublin.find("Fuser:3102");

        assembler.load(new Bin(11011));
        press.load(new Bin(11015));

        CarryCommand carry1 = new CarryCommand(assembler, fuser);
        CarryCommand carry2 = new CarryCommand(press, fuser);

        CommandSequence seq = new CommandSequence();
        seq.add(carry1);
        seq.add(carry2);

        //this hierarchy’s design requires that Command subclasses
        //reinterpret the meaning of the execute() operation
        //This is the intent of INTERPRETER: to allow you to compose executable objects.

        //A typical INTERPRETER hierarchy will include more than two subclasses,
        //and we will extend the Command hierarchy shortly

        //A CommandSequence object interprets the execute() operation by forwarding
        //the call to each subcommand

        seq.execute();
//        or with out Ineterpreter and using only command pattern
//        carry1.execute();
//        carry2.execute();
    }
}