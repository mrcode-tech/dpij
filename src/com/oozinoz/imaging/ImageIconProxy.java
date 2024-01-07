package com.oozinoz.imaging;

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
 * This class acts as a proxy for another ImageIcon. In "The Design Patterns
 * in Java" we wind up tearing out this class, preferring the techniques
 * used in the ImageIconLoader class.
 * @author Steven J. Metsker
 * @see LoadingImageIcon
 */
// TODO: 1/7/2024  Proxy design pattern - first approach
public class ImageIconProxy extends ImageIcon implements Runnable {
//    in ImageIcon classe transient means:
//    In Java, the transient keyword is used as a modifier for instance variables. When applied to a variable,
//    it indicates that the variable should not be included in the default serialization process.
    static final ImageIcon ABSENT = new ImageIcon(ClassLoader.getSystemResource("resources/images/absent.jpg"));
    static final ImageIcon LOADING = new ImageIcon(ClassLoader.getSystemResource("resources/images/loading.jpg"));
    ImageIcon current = ABSENT;
    protected String filename;
    protected JFrame callbackFrame;

    /**
     * Construct an ImageIconProxy that will (on demand) load the image in the
     * provided file.
     * @param filename the name of a file to load
     */
    public ImageIconProxy(String filename) {
        super(ABSENT.getImage());
        this.filename = filename;
    }

    /**
     * Load the desired image and call back the provided frame when done.
     * @param callbackFrame the frame to repaint when the image is loaded
     */
    public void load(JFrame callbackFrame) {
        this.callbackFrame = callbackFrame;
        current = LOADING;
        callbackFrame.repaint();
        new Thread(this).start();
    }

    /**
     * Load the desired image (presumably in a separate thread).
     */
    // in this class, new ImageIcon object used
    public void run() {
        current = new ImageIcon(ClassLoader.getSystemResource(filename));
        callbackFrame.pack();
    }
    
    /**
     * @return the height of the Icon
     */
    public int getIconHeight() {
        return current.getIconHeight();
    }

    /**
     * @return the width of the Icon
     */
    public int getIconWidth() {
        return current.getIconWidth();
    }

    /**
     * Paint the Icon
     */
    public synchronized void paintIcon(Component c, Graphics g, int x, int y) {
        current.paintIcon(c, g, x, y);
    }
}