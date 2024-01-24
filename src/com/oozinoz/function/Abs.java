package com.oozinoz.function;

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
 * Wrap the Math.Abs() function around a given source.
 */

// TODO: 1/24/2024 DECORATOR Design Pattern - Function Wrappers
//The Cos class constructor expects a Function argument and passes
//this argument up to the superclass constructor, where the argument is
//stored in the sources array. The Cos.f() method evaluates the source
//function at time t, passes this value to Math.Cos(), and returns the
//result.
public class Abs extends Function {
    /**
     * Construct an absolute value function that decorates the provided source
     * function.
     * 
     * @param f
     *            Another function wrapper
     */
    public Abs(Function f) {
        super(f);
    }

    /**
     * @return the absolute value of the source function value at time t
     * @param t
     *            time
     */
    public double f(double t) {
        return Math.abs(sources[0].f(t));
    }
}