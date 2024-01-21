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
 * This class is the top of a hierarchy of door state classes.
 */
// TODO: 1/20/2024 State Design Pattern - solution 1
//If your states will be used by different threads, make sure that your
//state-transition methods are synchronized to ensure that there are no
//conflicts when two threads try to change state at the same time.
//The power of the STATE pattern is that the logic for any given state is
//centralized in a single class.
public abstract class DoorState {
    protected Door2 door;

    /**
     * Subclasses must decide what to do when the user touches the physical
     * carousel button.
     */

    //The abstract DoorState class requires subclasses to implement touch().
    //The touch(), complete(), timeout(), and status() methods show
    //the role of polymorphism in this design
    public abstract void touch();

    /**
     * By default, discard notifications that the door finished opening or
     * closing.
     */
    public void complete() {
    }

    /**
     * By default, discard notifications that the door began closing after
     * having been open for a while.
     */
    public void timeout() {
    }

    /**
     * Return a textual desciption of this state.
     * 
     * @return a textual desciption of this state
     */

    //Note that the status() method works for all the states and is much
    //simpler than its predecessor before refactoring
    public String status() {
        String s = getClass().getName();
        return s.substring(s.lastIndexOf('.') + 1);
    }

    /**
     * Construct a state for the provided door.
     * 
     * @param door a door that needs a state model
     */
    public DoorState(Door2 door) {
        this.door = door;
    }
}