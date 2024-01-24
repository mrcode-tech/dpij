package app.decorator;

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

import app.decorator.brightness.FunPanel;

import com.oozinoz.function.*;
import com.oozinoz.ui.SwingFacade;

// TODO: 1/24/2024 DECORATOR Design Pattern - Function Wrappers - sample 1
public class ShowFun {
    public static void main(String[] args) {
        //The T class returns the passed-in value of t. This behavior is useful if
        //you want a variable to vary linearly with time. For example, the following
        //expression creates a Function object whose f() value will vary
        //from 0 to 2π as time varies from 0 to 1
        //new Arithmetic('*', new T(), new Constant(2 * Math.PI))

        Function theta = new Arithmetic('*', new T(), new Constant(2 * Math.PI));
        Function theta2 = new Arithmetic('*', new T(), new Constant(2 * Math.PI * 5));
        Function x = new Arithmetic('+', new Cos(theta), new Cos(theta2));
        Function y = new Arithmetic('+', new Sin(theta), new Sin(theta2));
    
        FunPanel panel = new FunPanel(1000);
        panel.setPreferredSize(new java.awt.Dimension(200, 200));

        //T().f(t) values set in function paintComponent in FunPanel class
        panel.setXY(x, y);
        SwingFacade.launch(panel, "Chrysanthemum");    
    }
}