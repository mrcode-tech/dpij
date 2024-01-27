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

import com.oozinoz.machine.*;
import java.util.*;

/**
 * This class uses the visitor mechanics of the machine hierarchy to add a
 * behavior that finds a particular machine within a machine composite.
 * 
 * @author Steven J. Metsker
 * @see app.visitor.ShowFindVisitor
 */
// TODO: 1/27/2024 Visitor Design Pattern -sample 1- Find MachineComponent Visitor
//The FindVisitor class effectively adds a find() operation to the
//MachineComponent hierarchy.
public class FindVisitor implements MachineVisitor {
    private int soughtId;

    private MachineComponent found;

    /**
     * @param mc the composite to look within
     * @param id the id of the machine to find
     * @return a machine with the given id, within the given machine composite
     */
    public MachineComponent find(MachineComponent mc, int id) {
        found = null;
        soughtId = id;
        mc.accept(this);
        return found;
    }

    /**
     * Check a particular machine to see if it's the one that is sought.
     */
    public void visit(Machine m) {
        if (found == null && m.getId() == soughtId) 
            found = m;
    }

    /**
     * Check if the provided composite is the sought machine component. If not,
     * check this composite's children.
     */
    //When the visit(:MachineComposite) method executes, it invokes
    //the accept() operation on each of the composite’s children. A child
    //responds by invoking a visit() operation on the Visitor object.
    //the short trip from the Visitor object to the
    //object that receives the accept() invocation and back again picks up
    //the type of the receiving object. This technique, known as double
    //dispatch, ensures that the right visit() method of the Visitor class
    //executes.

    //The double dispatching in VISITOR lets you create visitor classes with
    //methods that are specific to the various types in the visited hierarchy.
    public void visit(MachineComposite mc) {
        if (found == null && mc.getId() == soughtId) {
            found = mc;
            return;
        }
        Iterator<MachineComponent> iter = mc.getComponents().iterator();
        while (found == null && iter.hasNext()) 
            iter.next().accept(this);
    }
}