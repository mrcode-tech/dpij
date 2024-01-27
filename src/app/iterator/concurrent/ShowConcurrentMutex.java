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
 * Show a "synchronized" list with iteration over the list.
 */
// TODO: 1/26/2024 Iterator Design Pattern - sample 4 - best solution -  using mutex or clone / copy the list
// There are two common approaches to providing safe iteration over a
//collection in a multithreaded application. Both approaches involve the
//use of an object, sometimes called a mutex, that is shared by threads
//that vie for control of the object’s lock.

//In one approach, your design
//can require that all threads gain control of the mutex lock before
//accessing the collection.
public class ShowConcurrentMutex implements Runnable {
    private List<String> list;

    protected static List<String> upMachineNames() {
        return new ArrayList<>(Arrays.asList("Mixer1201",
                "ShellAssembler1301", "StarPress1401", "UnloadBuffer1501"));
    }

    public static void main(String[] args) {
        new ShowConcurrentMutex().go();
    }

    protected void go() {
        System.out.println("This version synchronizes properly:");
        list = Collections.synchronizedList(upMachineNames());
        //The program outputs a consistent result, with no duplicates,
        //because the program logic requires the run() method to wait
        //for the iteration in the display() method to complete.

        //Although the output is correct, the design may be impractical: You may not be able
        //to afford to have other threads block while one thread iterates over a collection.
        synchronized (list) {
            display();
        }
        //An alternative approach is to clone the collection in a mutex operation
        //and then work on the clone. The advantage of cloning the list
        //before traversing it is speed. Cloning a collection is often much faster
        //than waiting for another method to finish operating on the collection’s
        //contents. However, cloning a collection and iterating over the
        //clone may cause problems.
        //The clone() method for ArrayList produces a shallow copy: a new
        //collection that refers to the same objects as the original. Relying on a
        //clone will fail if other threads can change the underlying objects in a
        //way that interferes with your method.
    }

    private void display() {
        for (int i = 0; i < list.size(); i++) {
            if (i == 1) { // simulate wake-up
                new Thread(this).start();
                try {
                    Thread.sleep(100);
                } catch (InterruptedException ignored) {
                }
            }
            System.out.println(list.get(i));
        }
        //An argument against the use of synchronized() methods is: The synchronized()
        //methods either misfire completely—if you iterate with a
        //for loop—or crash the program, unless you build in the logic to catch
        //the exception InvalidOperationException that gets thrown.

        //An argument against a locking-based approach is: Designs that provide
        //thread-safe iteration rely on cooperation between threads that may
        //access the collection. The whole point of the synchronized() methods
        //is to catch the case in which threads aren’t cooperating.
    }

    /**
     * * Insert an element in the list, in a separate thread.
     */
    public void run() {
        synchronized (list) {
            list.add(0, "Fuser1101");
        }
    }
}