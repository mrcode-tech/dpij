package com.oozinoz.filter;

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

import java.io.*;

/**
 * Make characters upper case or lower case at random.
 * @author Steven J. Metsker
 */
// TODO: 1/24/2024 DECORATOR Design Pattern - OozinozFilter
//The theme of these filters is the same: The development task consists
//of overriding the appropriate write() methods. The write() methods
//decorate the received stream of text and pass the modified text on
//to a subordinate stream.
public class RandomCaseFilter extends OozinozFilter {
    /**
     * Construct a filter that pass randomly-cased characters to the supplied
     * stream.
     * @param out a writer to which to pass down writes
     */
    public RandomCaseFilter(Writer out) {
        super(out);
    }

    /**
     * Pass a randomly-cased version of the supplied character to the underlying
     * stream.
     * @param c the character to write
     * @throws IOException if an I/O error occurs
     */
    public void write(int c) throws IOException {
        out.write(Math.random() < .5 ? Character.toLowerCase((char) c)
                : Character.toUpperCase((char) c));
    }
}