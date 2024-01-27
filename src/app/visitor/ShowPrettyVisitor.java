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

import com.oozinoz.process.ProcessComponent;
import com.oozinoz.process.ShellProcess;

/**
 * Show the use of the PrettyVisitor class.
 */
// TODO: 1/27/2024 Visitor Design Pattern -sample 3- VISITOR Cycles - PrettyVisitor
public class ShowPrettyVisitor {

    public static void main(String[] args) {
        //This output is more informative that the printout we achieved by
        //simply iterating over the process model. The one question mark that
        //appears signals that this composite’s steps are alternatives. Also,
        //showing the Make step a second time, followed by an ellipsis, is more
        //clear than simply omitting a repeated step.
        ProcessComponent p = ShellProcess.make();
        PrettyVisitor v = new PrettyVisitor();
        System.out.println(v.getPretty(p));
    }
}