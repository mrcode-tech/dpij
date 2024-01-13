package com.oozinoz.chemical;

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
 * This class creates and returns Chemical objects. We will refactor this class
 * to make Chemical an interface.
 */
// TODO: 1/13/2024 Flyweight design pattern - The code for ChemicalFactory can use a static initializer to store Chemical objects in a Hashtable
public class ChemicalFactory {
    private static Map<String, Chemical> chemicals = new HashMap<>();

    static {
        chemicals.put("carbon", new Chemical("Carbon", "C", 12));
        chemicals.put("sulfur", new Chemical("Sulfur", "S", 32));
        chemicals.put("saltpeter", new Chemical("Saltpeter", "KN03", 101));
        //...
    }

    /**
     * @param name
     *            the name of the interesting chemical
     * @return the Chemical object for the given name.
     */
    public static Chemical getChemical(String name) {
        return chemicals.get(name.toLowerCase());
    }
}