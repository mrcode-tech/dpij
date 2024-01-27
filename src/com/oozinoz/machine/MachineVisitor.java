package com.oozinoz.machine;

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

/**
 * This interface defines the type of object that machines and machine
 * composites will accept. The Machine hierarchy classes call back a visiting
 * object's visit() methods; In so doing they identify their type. Implementors
 * of this interface can create algorithms that operate differently on different
 * type of machine components.
 * 
 * @author Steven J. Metsker
 *  
 */
// TODO: 1/27/2024 Visitor Design Pattern
public interface MachineVisitor {
    /**
     * @param m the machine to visit
     */
    //As we shall see, though, it is important for each class that implements
    //accept() to appear as an argument in a visit() method declared in
    //the visitor interface.
    //The accept() methods in the MachineComponent, together with the
    //MachineVisitor interface, invite developers to provide new operations
    //to the hierarchy.
    void visit(Machine m);

    /**
     * @param mc the machine composite to visit
     */
    void visit(MachineComposite mc);
}