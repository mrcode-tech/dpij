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

import java.util.Iterator;
import java.util.List;
import java.util.Set;
// TODO: 1/26/2024 Iterator Design Pattern - sample Iterating over a Composite - Adding Depth to a Composite Enumerator - Enumerating Leaves
public class CompositeIterator<E extends AcycliclyIterable<E>> extends ComponentIterator<E> {
    protected E peek;
    protected Iterator<E> children;
    protected ComponentIterator<E> subiterator;

    //The CompositeEnumerator class constructor
    //can initialize the child enumerator as follows:
    public CompositeIterator(E head, List<E> components, Set<E> visited) {
        super(head, visited);
        children = components.iterator();
    }

    //The code for CompositeIterator.getDepth() is:
    public int getDepth() {
        if (subiterator != null) 
            return subiterator.getDepth() + 1;
        return 0;
    }

    public boolean hasNext() {
        if (peek == null) 
            peek = next();
        return peek != null;
    }

    //When we begin an enumeration of a composite, we know that the
    //first node to return is the head node. Thus,
    //the code for the next() method of a CompositeIterator class looks
    //like this

    //The next() method uses the visited set to record whether the enumerator
    //has already returned the head node. If the enumerator has
    //already returned the head of a composite node, the nextDescendant()
    //method must find the next node.
    public E next() {
        if (peek != null) {
            E result = peek;
            peek = null;
            return result;
        }
        
        if (!visited.contains(head)) {
            visited.add(head);
            if (shouldShowInterior()) return head;
        }
        
        return nextDescendant();
    }

    /*
     * Usually just return the subiterator's next element. But if the
     * subiterator doesn't exist or doesn't have a next element, create an
     * iterator for the next child. (If there is no next child, just return
     * null.) Create an iterator for this child and return this iterator's next
     * element.
     */


    //At any given time, the subiterator variable may be part-way
    //through an enumeration of a child that is itself a composite node.
    //If this enumerator is active, the next() method of the Composite-
    //Iterator class can “move” the subiterator. If the subiterator cannot
    //move, the code must move to the next element in the children list,
    //get a new subenumerator for it, and move that enumerator. The code
    //for the nextDescendant() method shows this logic:

    //In the nextDescendant() method of the CompositeIterator class,
    //we’ll need to pass returnInterior attribute down when we create a new enumeration
    //for a composite node’s child:
    protected E nextDescendant() {
        while (true) {
            if (subiterator != null) {
                if (subiterator.hasNext()) return subiterator.next();
            }

            if (!children.hasNext()) return null;

            E pc = children.next();
            if (!visited.contains(pc)) {
                subiterator = pc.iterator(visited);
                subiterator.setShowInterior(shouldShowInterior());
            }
        }
    }
}
