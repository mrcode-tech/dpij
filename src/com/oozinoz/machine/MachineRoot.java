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
 * Represent a component that has no parent, typically a factory.
 * @author Steven J. Metsker
 */
// TODO: 1/7/2024  Chain of Responsibility design pattern - after refactoring
public class MachineRoot extends MachineComposite {
    /**
     * Create a root component with the given responsible engineer.
     * @param id the identity of this component
     * @param responsible the person with overall responsibility for the machines
     *            in this composite
     */
    public MachineRoot(int id, Engineer responsible) {
        super(id, null, responsible);
    }
}