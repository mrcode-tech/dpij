package com.oozinoz.robotInterpreter;

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

import java.util.ArrayList;
import java.util.List;
// TODO: 1/22/2024 Interpreter Design Pattern
//The intent of the INTERPRETER pattern is to let you compose
//executable objects from a hierarchy of classes that provide various
//interpretations of a common operation. The intent of COMMAND is
//merely to encapsulate a request in an object.
//Can an interpreter object function as a command? Sure! The question
//of which pattern applies depends on your intent. Are you creating a
//toolkit for composing executable objects, or are you encapsulating a
//request in an object?
/**
*  This class contains a sequence of other commands.
*/
public class CommandSequence extends Command {
    protected List<Command> commands = new ArrayList<>();

    /**
    *  Add a command to the sequence of commands to which this
    *  object will cascade an Execute() command.
    *  @param c a command to add
    */
    public void add(Command c) {
        commands.add(c);
    }

    /**
    *  @return a string description of this command sequence.
    */
    public String toString() {
        StringBuilder sb = new StringBuilder();
        boolean needLine = false;

        for (Command command : commands) {
            if (needLine)
                sb.append("\n");

            sb.append(command);
            needLine = true;
        }

        return sb.toString();
    }

    /**
    *  Ask each command in the sequence to execute.
    */
    //A CommandSequence object interprets the execute() operation by forwarding
    //the call to each subcommand
    public void execute() {
        for (Command command : commands) {
            command.execute();
        }
    }
}