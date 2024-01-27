package app.iterator.process;

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

import com.oozinoz.iterator.ComponentIterator;
import com.oozinoz.process.ProcessComponent;
import com.oozinoz.process.ShellProcess;

// TODO: 1/26/2024 Iterator Design Pattern - sample Iterating over a Composite
public class ShowProcessIteration {
    public static void main(String[] args) {
        ProcessComponent pc = ShellProcess.make();
        ComponentIterator<ProcessComponent> iter = pc.iterator();
        while (iter.hasNext())
            System.out.println(iter.next());
    }
}
