package app.visitor;

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

import java.util.List;
import java.util.HashSet;
import java.util.Set;

import com.oozinoz.process.*;

/**
 * This class uses the visitor mechanics of the process hierarchy to add a
 * behavior that pretty-prints a process composite. The prettiness is basically
 * indentation, although alternations are prefixed with a question mark to note
 * that they're alternations.
 * 
 * This visitor takes care not to print any component twice, if the original
 * component is cyclic. On encountering a node a second time, this visitor will
 * print an ellipsis (...).
 */
// TODO: 1/27/2024 Visitor Design Pattern -sample 3- VISITOR Cycles - PrettyVisitor
//Unlike machine composites, it is natural for process
//flows to contain cycles, and visitors must take care not to cause
//infinite loops while traversing process composites
public class PrettyVisitor implements ProcessVisitor {
    public static final String INDENT_STRING = "    ";

    private StringBuilder sb;
    private int depth;
    private Set<ProcessComponent> visited;

    /**
     * @param pc the process component to portray
     * @return a pretty (indented) description of the supplied process component.
     */
    //This class uses a getPretty() method to initialize an instance’s variables
    //and to kick off the visitor algorithm.
    public StringBuilder getPretty(ProcessComponent pc) {
        sb = new StringBuilder();
        visited = new HashSet<>();
        depth = 0;
        pc.accept(this);
        return sb;
    }

    //The printIndentedString() method handles the indentation of steps as the algorithm
    //goes deeper and deeper into a composite.
    protected void printIndentedString(String s) {
        for (int i = 0; i < depth; i++) 
            sb.append(INDENT_STRING);
        sb.append(s);
        sb.append("\n");
    }

    /**
     * Add a step to the output buffer.
     * @param s the step
     */
    //When visiting a ProcessStep object, the code simply prints the step’s name:
    public void visit(ProcessStep s) {
        printIndentedString(s.getName());
    }

    /**
     * Add an alternation to the output buffer.
     * @param a the alternation
     */
    public void visit(ProcessAlternation a) {
        visitComposite("?", a);
    }

    /**
     * Add a sequence to the output buffer.
     * @param s the sequence
     */
    public void visit(ProcessSequence s) {
        visitComposite("", s);
    }

    /**
     * Print the prefix and name of this composite, and print its children. If
     * we've printed this element before, just print an ellipsis instead of
     * printing the children again.
     * 
     * @param prefix a possible prefix
     * @param c the composite to display
     */
    //These developers are well
    //aware of the need to avoid infinite loops while traversing process
    //flows. As the PrettyVisitor class shows, the developers of the visitor
    //also have to be aware of the potential for cycles in process components.
    //It might help prevent errors if the ProcessComponent
    //developers could provide some degree of cycle-management support
    //as part of their support of VISITOR.

    //Now visitor developers must create classes with visit() methods that
    //accept the visited set. This is a significant hint that using the set is a
    //good idea, although the visitor developer retains the responsibility for
    //populating the set.
    protected void visitComposite(String prefix, ProcessComposite c) {
        if (visited.contains(c)) {
            printIndentedString(prefix + c.getName() + "...");
        } else {
            visited.add(c);
            printIndentedString(prefix + c.getName());
            depth++;

            List<ProcessComponent> children = c.getChildren();

            for (ProcessComponent child : children) {
                child.accept(this);
            }

            depth--;
        }
    }
}