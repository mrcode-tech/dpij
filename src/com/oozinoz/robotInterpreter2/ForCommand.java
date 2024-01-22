package com.oozinoz.robotInterpreter2;

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

import java.util.List;

import com.oozinoz.machine.Machine;
import com.oozinoz.machine.MachineComponent;
import com.oozinoz.machine.MachineComposite;

/**
 * This class represents a "for" loop that will execute its body for each
 * machine in a provided composite, assigning a variable to a different machine
 * in each pass through the loop.
 */

// TODO: 1/22/2024 Interpreter Design Pattern - ForCommand
//It would also be
//useful to have a command for a ForCommand class that executes a command
//across a given collection of machines. Figure 25.2 shows these
//extensions to the Command hierarchy

//The INTERPRETER pattern allows for many subclasses to reinterpret the
//meaning of a common operation.
public class ForCommand extends Command {
    protected MachineComponent root;
    protected Variable variable;
    protected Command body;

    /**
     * Construct a "for" interpreter that will execute the provided body,
     * looping through the machines in a context, assigning the provided
     * variable to each machine.
     * @param mc The machine component over which to iterate
     * @param v the variable to set for each loop
     * @param body the body of the for command
     */

    //Part of the design for a ForCommand class is immediately clear. The
    //constructor for this class would presumably accept a collection of
    //machines and a COMMAND object to execute as the body of a for loop.

    public ForCommand(MachineComponent mc, Variable v, Command body) {
        this.root = mc;
        this.variable = v;
        this.body = body;
    }

    /**
     * For each machine in the context, assign this object's variable to the
     * machine, and execute this object's body.
     */
    public void execute() {
        execute(root);
    }

    private void execute(MachineComponent mc) {
        //Once we design the Command hierarchy to work with terms, we can
        //write the ForCommand class so that it sets the value of a variable and
        //executes a body command in a loop:
        if (mc instanceof Machine) {
            Machine m = (Machine) mc;
            variable.assign(new Constant(m));
            body.execute();
            return;
        }

        //The execute() code in the ForCommand class uses casting to walk
        //through a machine component tree. Chapter 28, ITERATOR, reviews
        //faster and more elegant techniques for iterating over a composite. For
        //the INTERPRETER pattern, the important point is to properly interpret
        //the execute() request for each node in the tree.
        MachineComposite comp = (MachineComposite) mc;
        List<MachineComponent> children = comp.getComponents();
        for (MachineComponent child : children) {
            execute(child);
        }
    }
}