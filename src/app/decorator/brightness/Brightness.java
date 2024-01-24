package app.decorator.brightness;

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

import com.oozinoz.function.Function;

// TODO: 1/24/2024 DECORATOR Design Pattern - Function Wrappers - sample 3 - brightness function with decorator = exp(–4t) * sin(π*t)
//Write the code to define a Brightness object that represents the
//brightness function.


//The mechanics of DECORATOR include a common operation implemented
//across a hierarchy. In this regard, DECORATOR is similar to STATE,
//STRATEGY, and INTERPRETER. In DECORATOR, classes also usually have a
//constructor that requires another, subordinate decorator object. DECORATOR
//resembles COMPOSITE in this regard. DECORATOR also resembles
//PROXY, in that decorator classes typically implement the common
//operation by forwarding the call to the subordinate decorator object.
public class Brightness extends Function {
    public Brightness(Function f) {
        super(f);
    }

    public double f(double t) {
        return Math.exp(-4 * sources[0].f(t)) 
             * Math.sin(Math.PI * sources[0].f(t));
    }
}
