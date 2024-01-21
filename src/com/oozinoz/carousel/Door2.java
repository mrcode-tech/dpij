package com.oozinoz.carousel;

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

import java.util.Observable;

/**
 * This refactoring of the Door class moves state-specific logic to a separate
 * class hierarchy.
 */
// TODO: 1/20/2024 State Design Pattern - solution 1
//To apply STATE in this example, make each state of the door a separate class
public class Door2 extends Observable {
    //The design at play here requires each state
    //object to hold a reference to a Door2 object so that the state object
    //can inform the door of state transitions

    //This design approach
    //requires a state object to refer to a particular door, so a state object can
    //apply only to a single door.
    public final DoorState CLOSED = new DoorClosed(this);
    public final DoorState CLOSING = new DoorClosing(this);
    public final DoorState OPEN = new DoorOpen(this);
    public final DoorState OPENING = new DoorOpening(this);
    public final DoorState STAYOPEN = new DoorStayOpen(this);

    //The new design leads to much simpler code, but you might feel a bit
    //dissatisfied that the “constants” that the Door class uses are in fact
    //local variables.
    private DoorState state = CLOSED;

    /**
     * The carousel user has touched the carousel button. This "one touch"
     * button elicits different behaviors, depending on the state of the door.
     */
    public void touch() {
        state.touch();
    }

    /**
     * This is a notification from the mechanical carousel that the door
     * finished opening or shutting.
     */
    public void complete() {
        state.complete();
    }

    /**
     * This is a notification from the mechanical carousel that the door got
     * tired of being open.
     */
    public void timeout() {
        state.timeout();
    }

    /**
     * @return a textual description of the door's state
     */
    public String status() {
        return state.status();
    }

    protected void setState(DoorState state) {
        this.state = state;
        setChanged();
        notifyObservers();
    }
}