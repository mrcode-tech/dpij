package aster2;

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

// TODO: 1/22/2024 Command Design pattern - sample COMMAND Hooks - with command - without template method
//A class can provide a hook—a way to insert custom code—by invoking
//a supplied command at a specific point in a procedure.
public interface Hook {
    void execute(AsterStarPress p);
}
