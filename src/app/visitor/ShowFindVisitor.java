package app.visitor;

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

import com.oozinoz.machine.MachineComponent;
import com.oozinoz.machine.OozinozFactory;

/**
 * Show the use of the FindVisitor class.
 * 
 * @author Steven J. Metsker
 * @see app.visitor.FindVisitor
 */
// TODO: 1/27/2024 Visitor Design Pattern -sample 1- Find MachineComponent Visitor
//The VISITOR pattern lets you define a new operation for a hierarchy
//without changing the hierarchy classes. The mechanics for VISITOR
//include defining an interface for visitors and adding accept() methods
//in the hierarchy that a visitor will call. The accept() methods
//dispatch their calls back to the visitor in a double-dispatching
//scheme. This scheme arranges for the execution of a visit() method
//that applies to the specific type of object from the hierarchy.
//A visitor developer must be aware of some, if not all, of the subtleties
//in the design of the visited hierarchy. In particular, visitors need to
//beware of cycles that may occur in the visited object model. This type
//of difficulty leads some developers to eschew VISITOR, regularly applying
//alternatives instead. Whether you use VISITOR should probably be
//a decision that depends on your design philosophy, your team, and
//the specifics of your application.
public class ShowFindVisitor {
    public static void main(String[] args) {
        //Write a program that finds and prints out the StarPress:3404
        //object within the instance of MachineComponent that
        //OozinozFactory.dublin() returns.
        MachineComponent factory = OozinozFactory.dublin();
        MachineComponent machine = new FindVisitor().find(factory, 3404);
        System.out.println(machine != null ? machine.toString() : "Not found");
    }
}