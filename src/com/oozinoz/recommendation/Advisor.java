package com.oozinoz.recommendation;

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

import com.oozinoz.firework.Firework;

/**
 * Defines a standard service for recommending a purchasable item to a customer.
 */

// TODO: 1/21/2024 STRATEGY Design Pattern - using startegy

// The getRecommended() method presents several problems. First, it’s
//long—so long that comments have to explain its various parts. Short
//methods are easy to understand, seldom need explanation, and are
//usually preferable to long methods. In addition, the getRecommended()
//method both chooses a strategy and executes it; these are
//two different and separable functions. You can clean up this code by
//applying STRATEGY. To do so, let us
//• Create an interface that defines the strategic operation
//• Implement the interface with classes that represent each strategy
//• Refactor the code to select and use an instance of the right strategy
//class
public interface Advisor {
    Firework recommend(Customer c);
}