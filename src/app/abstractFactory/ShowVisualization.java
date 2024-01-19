package app.abstractFactory;

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

import javax.swing.JPanel;

import com.oozinoz.ui.BetaUI;
import com.oozinoz.ui.SwingFacade;
import com.oozinoz.ui.UI;
import com.oozinoz.visualization.Visualization;

/** Show the use of an alternative GUI kit (an alternative
 *  abstract factory).
*/
// TODO: 1/18/2024  ABSTRACT FACTORY Design Pattern
//Suppose that we release a version of the Visualization class with
//new features, and while this code is in beta test, we want to change
//the user interface

// TODO: 1/19/2024 Memento Design Pattern
public class ShowVisualization {
    public static void main(String[] args) {
//        BetaUI betaUI = new BetaUI();
//        JPanel panel = new Visualization(betaUI);

        JPanel panel = new Visualization(UI.NORMAL);
        SwingFacade.launch(panel, "Operational Model");
    }
}
