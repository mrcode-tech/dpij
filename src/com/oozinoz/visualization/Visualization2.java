package com.oozinoz.visualization;

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

import java.awt.event.*;
import javax.swing.*;
import com.oozinoz.ui.*;

/**
 * This version of the visualization adds a menu that
 * provides for saving and restoring mementos from a file.
 */

// TODO: 1/19/2024 Persisting Mementos Across Sessions
// TODO: 1/22/2024 Command Design pattern - A Classic Example: Menu Commands
//A classic example of the usefulness of COMMAND comes with menus. Menu
//items know when to execute an action but don’t know which
//method to call. COMMAND lets you parameterize a menu with the
//method calls that correspond to menu labels.
public class Visualization2 extends Visualization {
    public static void main(String[] args) {
        Visualization2 panel = new Visualization2(UI.NORMAL);
        JFrame frame = SwingFacade.launch(panel, "Operational Model");
        frame.setJMenuBar(panel.menus());
        frame.setVisible(true);
    }

    public Visualization2(UI ui) {
        super(ui);
    }

    public JMenuBar menus() {
        JMenuBar menuBar = new JMenuBar();

        JMenu menu = new JMenu("File");
        menuBar.add(menu);

        JMenuItem menuItem = new JMenuItem("Save As...");

        //Instead, if you have many menu items that take independent actions, it may be better
        //to apply COMMAND.
        
        //When you create the menu item, you can attach an Action-
        //Listener to it, with an actionPerformed() method specific to the
        //behavior of the particular command. Rather than define a new class
        //to implement this one little behavior, it is common to use an anonymous class.

        //The code that creates those menu items registers listeners that wait
        //for the user to choose them. The listeners implement the action-
        //Performed() method by calling the save() and load() methods of
        //the Visualization2 class
        menuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                save();
            }
        });

        menu.add(menuItem);

        menuItem = new JMenuItem("Restore From...");
        menuItem.addActionListener(new ActionListener() {
            //Although the actionPerformed() method requires an ActionEvent
            //argument, you can safely ignore it. The menus() method registers a
            //single instance of an anonymous class with the Save menu item and a
            //single instance of another anonymous class with the Load menu
            //item. When these methods are called, there is no doubt about the
            //source of the event.
            public void actionPerformed(ActionEvent e) {
                restore();
            }

        });
        menu.add(menuItem);

        return menuBar;
    }

    //An easy way to store an object, such as the factory model’s topmost configuration, is to serialize it.
    public void save() {
        try {
            mediator.save(this);
        } catch (Exception ex) {
            System.out.println("Failed save: " + ex.getMessage());
        }
    }

    public void restore() {
        try {
            mediator.restore(this);
        } catch (Exception ex) {
            System.out.println("Failed restore: " + ex.getMessage());
        }
    }
}