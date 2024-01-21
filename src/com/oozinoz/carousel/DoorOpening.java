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

/** Model the behavior of a carousel door when it's opening. */
// TODO: 1/20/2024 State Design Pattern - solution 1
//creates a special class for each
//state that the door might be in. Each of these classes contains the
//logic for responding to a touch of the one-touch button while the
//door is in a specific state.
public class DoorOpening extends DoorState {
    public DoorOpening(Door2 door) {
        super(door);
    }

    /**
     * Stop opening and start closing the door.
     */
    //The design at play here requires each state
    //object to hold a reference to a Door2 object so that the state object
    //can inform the door of state transitions
    public void touch() {
        door.setState(door.CLOSING);
    }

    /**
     * We're done opening so the door is now open.
     */
    public void complete() {
        door.setState(door.OPEN);
    }
}