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

import java.util.*;

import com.oozinoz.iterator.AcycliclyIterable;
import com.oozinoz.planning.BasicPlanner;
import com.oozinoz.planning.MachinePlanner;
import com.oozinoz.iterator.ComponentIterator;
import com.oozinoz.iterator.LeafIterator;
import com.oozinoz.utility.Queue;

/**
 * Represent a machine in an Oozinoz factory.
 * 
 * @author Steven J. Metsker
 *  
 */
// TODO: 1/7/2024  Mediator design pattern - Mediators of Relational Integrity
// TODO: 1/27/2024 Visitor Design Pattern
public abstract class Machine extends MachineComponent {

    protected Queue<Bin> bins = new Queue<>();

    protected MachinePlanner planner;

    protected boolean isUp = true;

    protected TubMediator mediator;

    /**
     * Create a machine with the given id and with access to the mediator that
     * will control bin/machine relations.
     * 
     * @param id
     *            the identity of this machine
     * @param mediator
     *            the mediator that controls this machine's relation to bins
     */
    protected Machine(int id, TubMediator mediator) {
        this(id, mediator, null);
    }

    /**
     * Create a machine with the given id, with access to the mediator that will
     * control bin/machine relations, and with the supplied parent machine.
     * 
     * @param id
     *            the identity of this machine
     * @param mediator
     *            the mediator that controls this machine's relation to bins
     * @param parent
     *            the composite this machine belongs to
     */
    public Machine(int id, TubMediator mediator, MachineComponent parent) {
        this(id, mediator, parent, null);
    }

    /**
     * Create a machine with the given id, with access to the mediator that will
     * control bin/machine relations, and with the supplied parent machine.
     * 
     * @param id
     *            the identity of this machine
     * @param mediator
     *            the mediator that controls this machine's relation to bins
     * @param parent
     *            the composite this machine belongs to
     * @param responsible
     *            the person with overall responsibility for the machine
     */
    public Machine(int id, TubMediator mediator, MachineComponent parent, Engineer responsible) {
        super(id, parent, responsible);
        this.mediator = mediator;
        initialize();
    }


    /**
     * Create a machine with the given id.
     * 
     * @param id
     *            the identity of this machine
     */
    public Machine(int id) {
        super(id);
    }

    /**
     * Create a machine with the given id and with the supplied parent machine.
     * 
     * @param id
     *            the identity of this machine
     * @param parent
     *            the composite this machine belongs to
     */
    public Machine(int id, MachineComponent parent) {
        super(id, parent);
        this.mediator = null; //TBD
        initialize();
    }

    public void initialize() {
        bins = new Queue<>();
    }

    /**
     * Queue up a bin for processing at this machine.
     * 
     * @param b
     *            the bin to add
     */
    public void load(Bin b) {
        bins.enqueue(b);
        System.out.println(toString() + " loading");
    }

    /**
     * Return an object that can plan for the operational behavior of this
     * machine.
     * 
     * @return a planner for this machine
     */
    // TODO: 1/18/2024  FACTORY METHOD Design Pattern
    //The Fuser and Mixer classes can rely on inheriting this method,
    //whereas the ShellAssembler and StarPress class will need to override it

    //These methods show the FACTORY METHOD pattern at work. When we
    //need a planner object, we call the createPlanner() method of the
    //machine we want to plan for. The specific planner that we receive
    //depends on the machine.
    public MachinePlanner createPlanner() {
        return new BasicPlanner(this);
    }
    /**
     * Return the number of machines in this machine, namely 1
     * 
     * @return one, since there's only one machine here
     */
    public int getMachineCount() {
        return 1;
    }

    /**
     * Return true if there are any bins on this machine
     * 
     * @return true if there are any bins on this machine
     */
    public boolean hasMaterial() {
        return !bins.isEmpty();
    }

    /**
     * Ask this machine to shutdown.
     */
    public void shutdown() {
        System.out.println(toString() + " shutting down");
    }

    /**
     * Ask this machine to start up.
     */
    public void startup() {
        System.out.println(toString() + " starting up");
    }

    /**
     * Remove a material bin from this machine.
     * 
     * @return the removed bin
     */
    public Bin unload() {
        if (bins.isEmpty()) {
            System.out.println(toString() + " already empty");
            return null;
        }
        Bin b = bins.dequeue();
        System.out.println(toString() + " unloading");
        return b;
    }

    /**
     * This hook lets visitors add behaviors to the machine composite. The point
     * here is to call back the visitor indicating the type of this node, namely
     * Machine.
     * 
     * @param v
     *            a visitor that will add some sort of behavior
     */
    // TODO: 1/27/2024 Visitor Design Pattern
    //The accept() method in the MachineComponent class is abstract
    //Both subclasses implement this method with exactly the same code:
    // The difference is in the type of the this object. The accept() method
    //calls the visit() method of a MachineVisitor object. The accept()
    //method in the Machine class will look up a visit() method with the
    //signature visit(:Machine), whereas the accept() method in the
    //MachineComposite class will look up a method with the signature
    //visit(:MachineComposite).

    public void accept(MachineVisitor v) {
        v.visit(this);
    }

    /**
     * Place a tub of chemicals at this machine.
     * 
     * @param t
     *            the tub to add
     */
    public void addTub(Tub t) {
        mediator.set(t, this);
    }

    /**
     * Return a planner for this machine.
     * 
     * @return a planner for this machine
     */
    public MachinePlanner getPlanner() {
        if (planner == null)
            planner = createPlanner();
        return planner;
    }

    /**
     * Return the chemical tubs that are at this machine.
     * 
     * @return the chemical tubs that are at this machine
     */
    public Set<Tub> getTubs() {
        return mediator.getTubs(this);
    }

    /**
     * @param visited ignored
     * @return True; individual machines are always "trees"
     * @see MachineComponent#isTree()
     */
    protected boolean isTree(Set<MachineComponent> visited) {
        visited.add(this);
        return true;
    }

    /**
     * @return true if this machine is up
     */
    public boolean isUp() {
        return isUp;
    }

    /**
     * @return an iterator that will "iterate over" this machine, returning it
     *         once.
     */
    public ComponentIterator<MachineComponent> iterator(Set<MachineComponent> visited) {
        return new LeafIterator<>(this, visited);
    }

    /**
     * Record whether or not this machine is up.
     * 
     * @param isUp
     *            whether or not this machine is up
     */
    public void setIsUp(boolean isUp) {
        this.isUp = isUp;
    }
}