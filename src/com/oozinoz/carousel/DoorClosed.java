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

/**
 * Model the behavior of a carousel door when it's closed.
 */
// TODO: 1/20/2024 State Design Pattern - solution 1
//creates a special class for each
//state that the door might be in. Each of these classes contains the
//logic for responding to a touch of the one-touch button while the
//door is in a specific state.
public class DoorClosed extends DoorState {
    public DoorClosed(Door2 door) {
        super(door);
    }

    /**
     * Open the door.
     */
    public void touch() {
        door.setState(door.OPENING);
    }
}