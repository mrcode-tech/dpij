package app.factoryMethod;

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
 * Show the (normally irrelevant) type of an iterator.
 * 
 * @author Steven J. Metsker
 */

// TODO: 1/17/2024 Factory Method design pattern -- ITERATOR
//A Classic Example: Iterators
//The ITERATOR pattern provides a way to access the elements of a collection
//sequentially. (See Chapter 28, ITERATOR.) But the way iterators are
//created often uses a FACTORY METHOD. Java JDK version 1.2 introduced
//a Collection interface that includes an iterator() method; all collections
//implement this operation. The iterator() isolates its caller
//from knowing which class to instantiate.
//An iterator() method creates an object that returns a sequence of
//the elements of a collection
public class ShowIterator {
    public static void main(String[] args) {
        List<String> list = Arrays.asList("fountain", "rocket", "sparkler");
        
        Iterator<String> iter = list.iterator();
        
        while (iter.hasNext()) {
            System.out.println(iter.next());
        }
        
        // Uncomment the next line to see the iterator's actual class:
         System.out.println(iter.getClass().getName());
    }
}