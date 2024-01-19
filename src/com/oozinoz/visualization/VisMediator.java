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

import java.awt.Component;
import java.awt.Cursor;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;

import javax.swing.JFileChooser;

/** 
 * This class handles the UI events for the Visualization class
 */

// TODO: 1/19/2024 Memento Design Pattern

//The VisMediator class translates GUI events
//into appropriate changes in the factory model. When the model
//changes, the GUI may need to update.
public class VisMediator {
    public static final Point DEFAULT_LOCATION = new Point(10, 10);

    protected int initX;

    protected int initY;

    protected Point initLocation;

    Cursor originalCursor;

    protected boolean isMouseDown = false;

    protected FactoryModel factoryModel;

    public VisMediator(FactoryModel m) {
        factoryModel = m;
    }

    public ActionListener addAction() {
        return new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                VisMediator.this.add(e);
            }
        };
    }

    public ActionListener undoAction() {
        return new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                VisMediator.this.undo(e);
            }
        };
    }

    public MouseListener mouseDownAction() {
        return new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                VisMediator.this.mouseDown(e);
            }

            public void mouseReleased(MouseEvent e) {
                VisMediator.this.mouseUp(e);
            }
        };
    }

    public MouseMotionListener mouseMotionAction() {
        return new MouseMotionListener() {
            public void mouseDragged(MouseEvent e) {
                VisMediator.this.mouseMove(e);
            }

            public void mouseMoved(MouseEvent e) {
            }
        };
    }

    private void add(ActionEvent e) {
        factoryModel.add(DEFAULT_LOCATION);
    }

    private void undo(ActionEvent e) {
        factoryModel.undo();
    }

    // A click on a picture box
    private void mouseDown(MouseEvent e) {
        if (e.getButton() != MouseEvent.BUTTON1)
            return;

        Component source = (Component) e.getSource();

        Component parent = source.getParent();
        originalCursor = parent.getCursor();
        parent.setCursor(new Cursor(Cursor.MOVE_CURSOR));

        initLocation = source.getLocation();
        initX = e.getX();
        initY = e.getY();

        isMouseDown = true;
    }

    // A drag while a picture box is clicked
    private void mouseMove(MouseEvent e) {
        if (!isMouseDown)
            return;
    }

    // Release picture box. Let the factory model know about this change.
    private void mouseUp(MouseEvent e) {
        if (e.getButton() != MouseEvent.BUTTON1)
            return;

        Component parent = ((Component) e.getSource()).getParent();
        parent.setCursor(originalCursor);

        isMouseDown = false;
        factoryModel.drag(initLocation, new Point(initLocation.x + e.getX()
                - initX, initLocation.y + e.getY() - initY));
    }

    // User clicked "Save As..." menu item
    public void save(Component source) throws Exception {
        JFileChooser dialog = new JFileChooser();
        dialog.showSaveDialog(source);

        if (dialog.getSelectedFile() == null)
            return;

        FileOutputStream out = null;
        ObjectOutputStream s = null;
        try {
            out = new FileOutputStream(dialog.getSelectedFile());
            s = new ObjectOutputStream(out);
            //An easy way to store an object, such as the factory model’s topmost configuration, is to serialize it.
            //write object to the file -> s.writeObject
            //we used Java serialization to write to a file in binary format
            s.writeObject(factoryModel.getLocations());
        } finally {
            if (s != null)
                s.close();
        }
    }

    // User clicked "Restore from..." menu item
    public void restore(Component source) throws Exception {
        JFileChooser dialog = new JFileChooser();
        dialog.showOpenDialog(source);

        if (dialog.getSelectedFile() == null)
            return;

        FileInputStream out = null;
        ObjectInputStream s = null;
        try {
            out = new FileInputStream(dialog.getSelectedFile());
            s = new ObjectInputStream(out);
            //read object from file -> s.readObject()
            @SuppressWarnings("unchecked") List<Point> list = (List<Point>) s.readObject();
            factoryModel.setLocations(list);
        } finally {
            if (s != null)
                s.close();
        }
    }
}