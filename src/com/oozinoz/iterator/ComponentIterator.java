package com.oozinoz.iterator;

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

import java.util.*;

/**
 * This is the abstract superclass of enumerators that can walk across leaf nodes
 * and composite nodes in a composite structure.
 */
// TODO: 1/26/2024 Iterator Design Pattern - sample Iterating over a Composite - Adding Depth to a Composite Enumerator - Enumerating Leaves
public abstract class ComponentIterator<E> implements Iterator<E> {
    protected E head;

    //The design also uses a visited variable to keep track of nodes that we have already
    //enumerated. This will keep us from stepping into an infinite loop
    //when a composite has cycles.
    protected Set<E> visited;

    //Suppose that we want to allow an enumeration to return only leaves.
    //This can be useful if we are concerned with attributes that apply only
    //to leaves, such as the time that a process step takes. We can add a
    //returnInterior field to the ComponentIterator class to record
    //whether interior (nonleaf) nodes should be returned from the
    //enumeration
    protected boolean returnInterior = true;

    /**
     * Create an enumerator over a node in a composite.
     * @param head the node to iterate over
     * @param visited a set to track visited nodes
     */
    public ComponentIterator(E head, Set<E> visited) {
        this.head = head;
        this.visited = visited;
    }

    public boolean shouldShowInterior() {
        return returnInterior;
    }

    public void setShowInterior(boolean value) {
        returnInterior = value;
    }

    /**
     * @return the current depth of the iteration (number of nodes above the
     *         current node)
     */
    //The output of this program might be more clear if we indented each
    //process step in accordance with its depth in the model. We can define
    //the depth of a leaf enumerator to be 0 and note that the current
    //depth of a composite enumerator is 1 plus the depth of its subiterator.
    //We can make the getDepth() abstract in the ComponentIterator
    //superclass as follows:
    public abstract int getDepth();

    public void remove() {
        throw new UnsupportedOperationException("ComponentIterator.Remove");
    }
}