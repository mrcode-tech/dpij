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

/**
 * This class represents a type of chemical.
 */
// TODO: 1/13/2024 Flyweight design pattern - The code for ChemicalFactory can use a static initializer to store Chemical objects in a Hashtable
public class Chemical {
    private String name;

    private String symbol;

    private double atomicWeight;

    /**
     * Model a chemical such as saltpeter.
     * 
     * @param name
     *            The name of this chemical, such as "Saltpeter."
     * @param symbol
     *            The chemical symbol for this substance, such as "KNO3."
     * @param atomicWeight
     *            The atomic weight of this substance (101 for saltpeter).
     */

    //One way that won’t work is to make the Chemical constructor private.
    //That would prevent the ChemicalFactory class from instantiating
    //the Chemical class.

    //To help prevent developers from instantiating the Chemical class
    //themselves, you could place Chemical and ChemicalFactory classes
    //in the same package and give the Chemical class’s constructor default
    //(“package”) access.
    Chemical(String name, String symbol, double atomicWeight) {
        this.name = name;
        this.symbol = symbol;
        this.atomicWeight = atomicWeight;
    }

    /**
     * @return The name of this chemical, such as "Saltpeter."
     */
    public String getName() {
        return name;
    }

    /**
     * @return The symbol for this chemical, such as "KNO3."
     */
    public String getSymbol() {
        return symbol;
    }

    /**
     * @return The atomic weight of this chemical (e.g. 101 for saltpeter).
     */
    public double getAtomicWeight() {
        return atomicWeight;
    }

    /**
     * @return Textual description of this chemical.
     */
    public String toString() {
        return name + "(" + symbol + ")[" + atomicWeight + "]";
    }
}