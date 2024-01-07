package com.oozinoz.machine;

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
 * This class manages the relation of tubs to machines.
 * 
 * @author Steven J. Metsker
 *  
 */
// TODO: 1/7/2024  Mediator design pattern - Mediators of Relational Integrity
public class TubMediator {
    protected Map<Tub, Machine> tubToMachine = new HashMap<>();

    public Machine getMachine(Tub t) {
        return tubToMachine.get(t);
    }

    public Set<Tub> getTubs(Machine m) {
        Set<Tub> set = new HashSet<>();
        for (Map.Entry<Tub, Machine> e : tubToMachine.entrySet()) {
            if (e.getValue().equals(m)) {
                set.add(e.getKey());
            }
        }
        return set;
    }

    public void set(Tub t, Machine m) {
        tubToMachine.put(t, m);
    }
}