package com.oozinoz.controller2;

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

/** This class is part of the Bridge chapter. 
 */
// TODO: 1/5/2024 4 - bridge design pattern - second solution - use driver
public class HskMachineManager2 extends MachineManager2 {
    public HskMachineManager2(MachineDriver driver) {
        super(driver);
    }
    
    public void setTimeout(Double d) { 
    }
}
