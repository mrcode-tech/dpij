package com.oozinoz.chemical2;

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
 * This class creates and returns Chemical objects. This is a refactoring that
 * ensures that the factory class is the only class that can instantiate the
 * ChemicalImpl class.
 */
// TODO: 1/13/2024 Flyweight design pattern - solution 2 - using inner class for chemical class
public class ChemicalFactory2 {
    private static Map<String, Chemical> chemicals = new HashMap<>();

    //Access modifiers do not supply the complete control over instantiation
    //that you might want. You might like to ensure that
    //ChemicalFactory is absolutely the only class that can create new
    //Chemical instances. To achieve this level of control, you can apply an
    //inner class, defining the Chemical class within ChemicalFactory.
    private class ChemicalImpl implements Chemical {
        //The ChemicalImpl nested class should be private so that only
        //the ChemicalFactory2 class can use the class.
        // Note that the nested class’s access must be package or public so that the containing
        //class can instantiate the nested class. Even if you make
        //the constructor public, no other class can use the constructor if
        //the nested class itself is marked private.
        private String name;
        private String symbol;
        private double atomicWeight;

        ChemicalImpl(String name, String symbol, double atomicWeight) {
            this.name = name;
            this.symbol = symbol;
            this.atomicWeight = atomicWeight;
        }

        public String getName() {
            return name;
        }

        public String getSymbol() {
            return symbol;
        }

        public double getAtomicWeight() {
            return atomicWeight;
        }
        
        public String toString() {
            return name + "(" + symbol + ")[" + atomicWeight + "]";
        }
    }

    static {
        //The ChemicalFactory2 constructor uses a static initializer to
        //ensure that the class will build the list of chemicals exactly once.
        ChemicalFactory2 factory = new ChemicalFactory2();
        chemicals.put("carbon", factory.new ChemicalImpl("Carbon", "C", 12));
        chemicals.put("sulfur", factory.new ChemicalImpl("Sulfur", "S", 32));
        chemicals.put("saltpeter", factory.new ChemicalImpl("Saltpeter", "KN03", 101));
        //...
    }

    /**
     * @param name the name of the interesting chemical
     * @return the Chemical object for the given name.
     */
    //The getChemical() method should look up chemicals by name
    //in the class’s hash table. The example code stores and looks up
    //chemicals, using the lowercase version of the chemical name.
    public static Chemical getChemical(String name) {
        return chemicals.get(name.toLowerCase());
    }
}