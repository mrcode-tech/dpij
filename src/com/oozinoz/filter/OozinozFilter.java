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
 * This class channels all versions of the write() method to go through the
 * write() method that takes a single character.
 * 
 * @author Steven J. Metsker
 */
// TODO: 1/24/2024 DECORATOR Design Pattern - OozinozFilter
//The OozinozFilter class will be the parent for classes that decorate output character streams

//This code is all we need to start putting DECORATOR to work. Subclasses
//of OozinozFilter can supply new implementations of write(:int)
//that modify a character before passing it on to the underlying
//stream’s write(:int) method.
public abstract class OozinozFilter extends FilterWriter {

    //To create a toolkit of composable output streams, the next step is to
    //introduce a filter superclass that has several critical attributes. The filter class will
    //• Accept in its constructor a Writer object
    //• Act as the superclass of a filter hierarchy
    //• Provide default implementations of all Writer methods except write(:int)
    protected OozinozFilter(Writer out) {
        super(out);
    }

    /**
     * Write a portion of an array of characters.
     * 
     * @param cbuf
     *            Buffer of characters to be written
     * @param offset
     *            Offset from which to start reading characters
     * @param length
     *            Number of characters to be written
     * 
     * @throws IOException
     *             if an I/O error occurs
     */
    public void write(char cbuf[], int offset, int length) throws IOException {
        for (int i = 0; i < length; i++) 
            write(cbuf[offset + i]);
    }

    /**
     * Write a single character.
     * 
     * @throws IOException if an I/O error occurs
     */
    //We will define a filter class that accepts a writer in its constructor and
    //that mixes in new behaviors in its write() methods.
    public abstract void write(int c) throws IOException;

    /**
     * Write a portion of a string.
     * 
     * @param s
     *            String to be written
     * @param offset
     *            Offset from which to start reading characters
     * @param length
     *            Number of characters to be written
     * 
     * @throws IOException
     *             if an I/O error occurs
     */
    public void write(String s, int offset, int length) throws IOException {
        write(s.toCharArray(), offset, length);
    }
}