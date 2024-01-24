package app.decorator;

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
*  Show that the idea of composing streams from streams
*  occurs in the Java class libraries.
*/
// TODO: 1/24/2024 DECORATOR Design Pattern - show FileWriter and BufferWriter
//A stream is a serial collection of bytes or characters, such as those that appear in a document.
//In Java, Writer classes are one approach to supporting streams.
//Some writer classes have constructors that accept another
//writer, so that you create a writer from a writer. This sort of slim composition
//is the typical structure of DECORATOR. The DECORATOR pattern
//is at work in Java writers. But,
public class ShowDecorator
{
  public static void main(String[] args) throws IOException
  {
      //The program uses a FileWriter object
      //to create a new file. The program wraps this object inside a Buffered-
      //Writer object. The main point to note in the program is that we can
      //compose one stream from another: The code composes a Buffered-
      //Writer object from a FileWriter object.
      FileWriter file = new FileWriter("sample.txt");
      BufferedWriter writer = new BufferedWriter(file);
      writer.write("a small amount of sample text");
      writer.newLine();
      writer.close();
  }
}