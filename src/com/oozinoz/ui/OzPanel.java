package com.oozinoz.ui;

/*
 * Copyright (c) 2001, 2005. Steven J. Metsker. Steve Metsker makes no
 * representations or warranties about the fitness of this software for any
 * particular purpose, including the implied warranty of merchantability. Please
 * use this software as you wish with the sole restriction that you may not
 * claim that you wrote it.
 */

import javax.swing.JPanel;

public class OzPanel extends JPanel implements Cloneable {

    public OzPanel copy() {
        return (OzPanel) this.clone();
    }

    //Note that to use clone(), you must declare that your class implements Cloneable.
    // This marker interface has no methods but serves as a flag
    //to indicate that you’re intentionally supporting clone().
    public Object clone() {
        try {
            //The problem with this code is
            //that the clone() method will create copies of all the attributes of a
            //JPanel object, regardless of whether you understand the function of
            //those attributes.

            return super.clone();
        } catch (CloneNotSupportedException ignored) {
            throw new InternalError("OzPanel.clone() failed");
        }
    }

    public OzPanel copy2() {

        //OzPanel.copy2() method that copies a panel without
        //relying on clone()
        OzPanel result = new OzPanel();
        result.setBackground(this.getBackground());
        result.setForeground(this.getForeground());
        result.setFont(this.getFont());
        return result;
    }
}