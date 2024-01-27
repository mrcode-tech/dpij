package com.oozinoz.process;

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

/**
*  Represent a "sequence" (a series) of process steps.
*/
public class ProcessSequence extends ProcessComposite {
    /**
    *  Create a sequence with the given name.
    * @param name the name of this process sequence
    */
    public ProcessSequence(String name)
    {
        super(name);
    }
    /**
    *  Create a sequence with the given name and containing 
    *  the given subprocesses.
    *  @param name the name of this sequence
    *  @param subprocesses the children of this sequence
    */
    public ProcessSequence(String name, ProcessComponent[] subprocesses) {
            super(name, subprocesses);
    }
    /**
    *  Create a sequence with the given name and containing 
    *  the given subprocesses.
    *  @param name the name of this sequence
    *  @param subprocesses the children of this sequence
    */
    public ProcessSequence(String name, List<ProcessComponent> subprocesses)
    {
        super(name, subprocesses);
    }
    /**
    *  This hook lets visitors add behaviors to the process
    *  composite. The point here is to call back the visitor
    *  indicating the type of this node, namely 
    *  ProcessSequence.
    * @param v the visitor
    */
    // TODO: 1/27/2024 Visitor Design Pattern -sample 3- VISITOR Cycles - PrettyVisitor
    public void accept(ProcessVisitor v)
    {
        v.visit(this);
    }
}