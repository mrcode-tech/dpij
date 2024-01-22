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

// TODO: 1/22/2024 Interpreter Design Pattern - ForCommand
//The Term hierarchy is similar to the Command hierarchy in that a common
//operation (eval()) appears throughout the hierarchy
import com.oozinoz.machine.Machine;

/**
 * A term is usually either a machine, or a variable that refers to a machine.
 */
//Users of the Command and Term hierarchies can compose arbitrarily
//rich, complex “programs” of execution. For example, it is not too difficult
//to create an object that, when it executes, unloads all the material
//from all the machines, except unload buffers, in a factory
public abstract class Term {
    /**
     * @return The result of evaluating this term
     */
    public abstract Machine eval();
}