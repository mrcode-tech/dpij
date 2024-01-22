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

//Note that the code doesn’t bother checking whether moldIncompleteHook
//is null, as it is always set to a real Hook object. (Initially, it
//is set to a do-nothing NullHook object, but a user can install a different
//hook.)

//This example demonstrates another pattern, NULL OBJECT [Woolf
//1998], that is only slightly less well known than those in Design Patterns.
//This pattern lets us avoid a null-pointer check by introducing a
//default object that has no effect. (See Refactoring [Fowler et al. 1999]
public class NullHook implements Hook {
    public void execute(AsterStarPress p) {}
}
