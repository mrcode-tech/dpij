package com.oozinoz.ui;

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

import java.awt.*;
import javax.swing.*;

/**
 * This class shows the idea of establishing user interface kits with
 * prototypical components.
 */

// TODO: 1/19/2024 PROTOTYPE Design Pattern
public class UIKit {
    protected OzButton button = new OzButton();

    protected OzTextArea textArea = new OzTextArea();

    /**
     * @return a (copy of a prototypical) button
     */
    //The create-() methods will return copies of these prototypical controls.
    public JButton createButton(String text) {
        JButton b = new JButton(text);
        b.setFont(button.getFont());
        return b;
    }

    /**
     * @return a (copy of a prototypical) text area
     */

    //The create-() methods will return copies of these prototypical controls.

    //To use PROTOTYPE, you’ll need to master the mechanics of copying objects in Java
    //The intent of the PROTOTYPE pattern is to provide new objects by copying an example
    public OzTextArea createTextArea() {
        return (OzTextArea) textArea.clone();
    }

    /**
    * @return a kit for full-screen user interfaces
     */

    //• Drop the subclasses of UIKit.
    //• Let each instance of UIKit become a user interface factory that
    //works by handing out copies of prototypical controls.
    //• Place the code that creates new UIKit objects in static methods
    //on the UIKit class

    //The code that creates a new UIKit object will set the values
    //of these prototypical controls to produce the desired look-and-feel
    public static UIKit fullScreen() {
        Font font = new Font("Dialog", Font.ITALIC, 18);
        UIKit kit = new UIKit();
        kit.button.setFont(font);
        kit.textArea.setFont(font);
        return kit;
    }

    /**
     * @return a kit for handheld device user interfaces
     */

    //For example, we can create a static handheldUI() method on the UI
    //class. This method will instantiate UIKit, set this object’s instance
    //variables to values that are appropriate for a handheld display, and
    //return the object for use as a GUI kit.
    public static UIKit handheld() {
        UIKit kit = new UIKit();
        Font font = new Font("Dialog", Font.PLAIN, 8);
        kit.button.setFont(font);
        kit.textArea = new OzTextArea();
        kit.textArea.setFont(font);
        Cursor cursor = new Cursor(Cursor.HAND_CURSOR);
        kit.textArea.setCursor(cursor);
        return kit;
    }
}