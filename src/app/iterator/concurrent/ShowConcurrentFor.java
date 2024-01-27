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
 * Show that a "synchronized" list does not ensure that iteration over the list
 * with a "for" loop is thread safe.
 */
// TODO: 1/26/2024 Iterator Design Pattern - sample 3 - bug - classic For instead of iterator
//We have looked at two versions of the program: one that crashes and one that produces incorrect output
public class ShowConcurrentFor implements Runnable {
    private List<String> list;

    protected static List<String> upMachineNames() {
        return new ArrayList<>(Arrays.asList(
            "Mixer1201", "ShellAssembler1301", 
            "StarPress1401", "UnloadBuffer1501"));
    }

    public static void main(String[] args) {
        new ShowConcurrentFor().go();
    }

    protected void go() {
        System.out.println("This version lets new things be added in concurrently:");
        list = Collections.synchronizedList(upMachineNames());
        // Collections.synchronizedList returns a new list that is synchronized. This means that all the methods of the returned list are synchronized,
        // ensuring that multiple threads can safely access and modify the list without causing data corruption or inconsistencies.

        // the code you provided is not synchronized for concurrent modification because the Collections.synchronizedList wrapper ensures
        // that individual method calls on the list are synchronized,
        // but it does not guarantee synchronization for compound operations, such as iterating over the list using a "for" loop.
        //  synchronized list is created using Collections.synchronizedList, but this only synchronizes the individual methods (like add, get, etc.)
        //  of the list. It doesn't provide synchronization for the entire iteration process
        display();
    }

    private void display() {
        for (int i = 0; i < list.size(); i++) {
            if (i == 1) { // simulate wake-up - run method call
                new Thread(this).start();
                try {Thread.sleep(100); } catch (InterruptedException ignored) {}
            }
            System.out.println(list.get(i));
        }
    }
    
    /**
     * Insert an element in the list, in a separate thread.
     */
    public void run() {
        list.add(0, "Fuser1101");
    }
}