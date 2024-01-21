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
 * This class provides an initial model of a carousel door
 * that manages its state without moving state-specific
 * logic out to state classes.
 */

// TODO: 1/20/2024 State Design Pattern - without state design pattern
// The code for Door is somewhat complex because the use of the state variable is spread throughout the class.
// To apply STATE in this example, make each state of the door a separate class
public class Door extends Observable {
    public final int CLOSED = -1;
    public final int OPENING = -2;
    public final int OPEN = -3;
    public final int CLOSING = -4;
    public final int STAYOPEN = -5;

    private int state = CLOSED;

    /**
     * The carousel user has touched the carousel button. This "one touch"
     * button elicits different behaviors, depending on the state of the door.
     */
    //The setState() method of the Door class notifies observers of the door’s change
    public void touch() {
        switch (state)
        {
            case OPENING:
            case STAYOPEN:
                setState(CLOSING);
                break;
            case CLOSING:
            case CLOSED:
                setState(OPENING);
                break;
            case OPEN:
                setState(STAYOPEN);
                break;
            default:
                throw new Error("can't happen");
        }
    }

    /** 
     * This is a notification from the mechanical carousel that 
     * the door finished opening or shutting.
     */
    public void complete() {
        if (state == OPENING)
            setState(OPEN);
        else if (state == CLOSING)
            setState(CLOSED);
    }

    /**
     * This is a notification from the mechanical carousel that the
     * door got tired of being open.
     */
        public void timeout() {
            setState(CLOSING);
        }
     
    /**
     * @return a textual description of the door's state
     */
    //(You might choose to use an enumerated type if you’re using Java 5.)
    public String status()
    {
        switch (state)
        {
            case OPENING:
                return "Opening";
            case OPEN:
                return "Open";
            case CLOSING:
                return "Closing";
            case STAYOPEN:
                return "StayOpen";
            default:
                return "Closed";
        }
    }

    //The setState() method of the Door class notifies observers of the door’s change
    private void setState(int state)
    {
        this.state = state;
        setChanged();
        notifyObservers();
    }
}