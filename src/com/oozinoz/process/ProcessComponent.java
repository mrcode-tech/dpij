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

import java.util.HashSet;
import java.util.Set;

import com.oozinoz.iterator.AcycliclyIterable;
import com.oozinoz.iterator.ComponentIterator;

/**
 * Objects of this class represent either individual process steps or
 * compositions of process steps. A process is essentially a recipe for
 * manufacturing something, notably fireworks.
 */
// TODO: 1/26/2024 Iterator Design Pattern - sample Iterating over a Composite

// TODO: 1/27/2024 Visitor Design Pattern -sample 3- VISITOR Cycles - PrettyVisitor
public abstract class ProcessComponent implements AcycliclyIterable<ProcessComponent> {
    protected String name;

    /**
     * Create a process component with the given name.
     * 
     * @param name
     *            this process component's name
     */
    public ProcessComponent(String name) {
        this.name = name;
    }

    /**
     * Accept a "visitor".
     * 
     * @param v
     *            the visitor
     */
    //The developers of the ProcessComponent hierarchy built in support
    //for VISITOR by including accept() methods in the hierarchy and by
    //defining the ProcessVisitor interface

    //One solution is to add a Set argument to all the accept() and
    //visit() methods, so that the set of visited nodes gets passed around.
    //The ProcessComponent class should then have a concrete accept()
    //method that calls its abstract accept() method, passing it a new Set
    //object:
    public abstract void accept(ProcessVisitor v);

    /**
     * @return the component's name.
     */
    public String getName() {
        return name;
    }

    //To update the ProcessComponent hierarchy so that we can enumerate
    //it, we need to provide an iterator() method:
    public ComponentIterator<ProcessComponent> iterator() {
        return iterator(new HashSet<ProcessComponent>());
    }
    
    public abstract ComponentIterator<ProcessComponent> iterator(Set<ProcessComponent> visited);
    
    /**
     * @return the number of leaf node steps in this composite.
     */
    public int getStepCount() {
        return getStepCount(new HashSet<String>());
    }

    /**
     * @return the number of leaf node steps in this composite.
     * @param visited
     *            components already visited while traversing this component
     */
    public abstract int getStepCount(Set<String> visited);

    /**
     * @return a textual representation of this component.
     */
    public String toString() {
        return name;
    }
}