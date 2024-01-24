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

import java.io.Writer;

/**
*  This "filter" directs its characters to the console.
*/
// TODO: 1/24/2024 DECORATOR Design Pattern - OozinozFilter
public class ConsoleWriter extends Writer {
    //The other methods in the Oozinoz-
    //Filter class supply the behavior that subclasses will usually want.
    //The class simply leaves close() and flush() calls to its parent (FilterWriter).
    public void close() {}
    public void flush() {}
    
    public void write(char[] buffer, int offset, int length) {
        for (int i = 0; i < length; i++) 
            System.out.print(buffer[offset + i]);
    }
}