package app.iterator.concurrent;

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

/**
 * Show that modifying a list while an iterator iterates over it causes an
 * exception.
 */
// TODO: 1/26/2024 Iterator Design Pattern - sample 2 - excedption checkForComodification and crash - iterator
public class ShowConcurrentIterator implements Runnable {
    private List<String> list;

    protected static List<String> upMachineNames() {
        return new ArrayList<>(Arrays.asList("Mixer1201",
                "ShellAssembler1301", "StarPress1401", "UnloadBuffer1501"));
    }

    public static void main(String[] args) {
        new ShowConcurrentIterator().go();
    }

    protected void go() {
        //The collection classes in the java.util.Collections offer a measure
        //of thread safety by providing a synchronized() method. By returning,
        //essentially, a version of the underlying collection, this method
        //will prevent two threads from changing the collection at the same time.

        //A collection and its iterator cooperate to detect whether a list
        //changes during iteration, that is, whether the list is synchronized.
        list = Collections.synchronizedList(upMachineNames());
        // Collections.synchronizedList returns a new list that is synchronized. This means that all the methods of the returned list are synchronized,
        // ensuring that multiple threads can safely access and modify the list without causing data corruption or inconsistencies.
        Iterator<String> iter = list.iterator();
        int i = 0;
        while (iter.hasNext()) {
            i++;
            if (i == 2) { // simulate wake-up - run method call
                new Thread(this).start();
                try { Thread.sleep(100); } catch (InterruptedException ignored) {}
            }
            System.out.println(iter.next());
        }
    }

    /**
     * Insert an element in the list, in a separate thread.
     */
    public void run() {
        //This code simulates the condition that another machine comes up
        //while this method iterates over the list. The run() method modifies
        //the list, running in a separate thread.

        list.add(0, "Fuser1101");
        //The program crashes because the list and iterator objects detect that
        //the list has changed during the iteration
        //You can create a program that
        //crashes simply by altering a collection from within an iteration loop.

    }
}