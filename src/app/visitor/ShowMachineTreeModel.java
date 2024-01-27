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

import javax.swing.JScrollPane;
import javax.swing.JTree;

import com.oozinoz.machine.OozinozFactory;
import com.oozinoz.ui.SwingFacade;

// TODO: 1/27/2024 Visitor Design Pattern
//Displaying the factory’s machines requires building an instance of
//MachineTreeModel from the factory composite and wrapping this
//model in Swing components

//Many useful behaviors for a machine composite are possible. For
//example, suppose that you need to find a particular machine within
//the factory model. To add this ability without modifying the
//MachineComponent hierarchy, you can create a FindVisitor class
public class ShowMachineTreeModel {

    public ShowMachineTreeModel() {
        MachineTreeModel model = new MachineTreeModel(OozinozFactory.dublin());
        JTree tree = new JTree(model);
        tree.setFont(SwingFacade.getStandardFont());
        SwingFacade.launch(new JScrollPane(tree), " A New Oozinoz Factory");
    }

    public static void main(String[] args) {
        new ShowMachineTreeModel();
    }
}