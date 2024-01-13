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

/**
*  Part of the Flyweight chapter, this interface is related to 
*  restricting the ability to create flyweights.
*/
// TODO: 1/13/2024 Flyweight design pattern - solution 2 - using inner class for chemical class
//You can simplify the use of a nested class by making Chemical an
//interface and making ChemicalImpl the name of the class. The
//Chemical interface can specify three accessor methods, as follows:
public interface Chemical
{
    String getName();
    String getSymbol();
    double getAtomicWeight();
}