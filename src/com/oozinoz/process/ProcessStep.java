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

import java.util.Set;

import com.oozinoz.iterator.AcycliclyIterable;
import com.oozinoz.iterator.ComponentIterator;
import com.oozinoz.iterator.LeafIterator;

/**
 * Represent an individual process step.
 */
// TODO: 1/26/2024 Iterator Design Pattern - sample Iterating over a Composite
public class ProcessStep extends ProcessComponent {
    /**
     * Create a step with the given name.
     * 
     * @param name
     *            the name of this process step
     */
    public ProcessStep(String name) {
        super(name);
    }

    /**
     * This hook lets visitors add behaviors to the process composite. The point
     * here is to call back the visitor indicating the type of this node, namely
     * ProcessStep.
     */
    // TODO: 1/27/2024 Visitor Design Pattern -sample 3- VISITOR Cycles - PrettyVisitor
    public void accept(ProcessVisitor v) {
        v.visit(this);
    }

    //The ProcessStep class implementation of iterator() will be:
    public ComponentIterator<ProcessComponent> iterator(Set<ProcessComponent> visited) {
        return new LeafIterator<>(this, visited);
    }
    
    /**
     * @return the number of steps in this step, namely 1.
     * @param visited
     *            components already visited while traversing this component
     */
    public int getStepCount(Set<String> visited) {
        visited.add(name);
        return 1;
    }
}