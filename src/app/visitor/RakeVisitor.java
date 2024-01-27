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
 * behavior that finds all the leaf-node machines in a composite.
 * 
 * @author Steven J. Metsker
 * @see app.visitor.ShowRakeVisitor
 */
// TODO: 1/27/2024 Visitor Design Pattern -sample 2- finds all the machines—the leaf nodes—in a machine component Visitor
public class RakeVisitor implements MachineVisitor {
    private Set<MachineComponent> leaves;

    /**
     * Find all the leaf-node machines in a composite.
     */
    public Set<MachineComponent> getLeaves(MachineComponent mc) {
        leaves = new HashSet<>();
        mc.accept(this);
        return leaves;
    }

    /**
     * Add this machine to the set of leaf nodes.
     */
    public void visit(Machine m) {
        leaves.add(m);
    }

    /**
     * Visit all the children of the provided composite to see if they are leaf
     * nodes.
     */
    public void visit(MachineComposite mc) {
        for (MachineComponent machineComponent : mc.getComponents()) {
            machineComponent.accept(this);
        }
    }
}