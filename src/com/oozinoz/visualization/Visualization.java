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

import java.util.List;
import java.awt.*;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import com.oozinoz.ui.UI;

/** This class provides a visualization of a factory that contains
 *  machines and through which material flows. At present the only
 *  functionality is the ability to create and drag machines. In the
 *  future we'll add operational modeling functions.
 */

// TODO: 1/19/2024 Memento Design Pattern
//This design divides the application’s work into separate classes for
//modeling the factory, providing GUI elements, and handling a user’s clicks.

//The MVC design separates the tasks of translating user actions from the tasks of maintaining the GUI
//The Visualizationclass creates its GUI controls but passes off the handling of GUI events to a mediator

//The visualization changes factory events into GUI changes.
//The mediator translates GUI events into factory changes.

// TODO: 1/22/2024 Command Design pattern - Command + Mediator
public class Visualization extends JPanel implements ChangeListener {
	protected UI ui;

	protected JPanel machinePanel;

	protected JPanel buttonPanel;

	protected JButton addButton;

	protected JButton undoButton;

	protected Icon image = UI.getIcon("resources/images/machine.png");

	protected FactoryModel factoryModel = new FactoryModel();

	protected VisMediator mediator;

	public Visualization(UI ui) {
		super(new BorderLayout());
		this.ui = ui;
		mediator = new VisMediator(factoryModel);
		factoryModel.addChangeListener(this);
		add(machinePanel(), BorderLayout.CENTER);
		add(buttonPanel(), BorderLayout.SOUTH);
	}

	protected JPanel machinePanel() {
		if (machinePanel == null) {
			machinePanel = new JPanel(null);
			machinePanel.setBackground(Color.WHITE);
			machinePanel.setPreferredSize(new Dimension(600, 500));
		}
		return machinePanel;
	}

	protected JPanel buttonPanel() {
		if (buttonPanel == null) {
			buttonPanel = new JPanel();
			buttonPanel.add(addButton());
			buttonPanel.add(undoButton());
		}
		return buttonPanel;
	}

	protected JButton addButton() {
		if (addButton == null) {
			addButton = ui.createButtonOk();
			addButton.setText("Add");
			addButton.addActionListener(mediator.addAction());
		}
		return addButton;
	}

	protected JButton undoButton() {
		if (undoButton == null) {
			undoButton = ui.createButtonCancel();
			undoButton.setText("Undo");
			undoButton.setEnabled(false);
			//This code passes responsibility for handling a click to the mediator.
			//The mediator informs the factory model of any requested changes

			//This code applies COMMAND, packaging an undo() method in an
			//instance of the ActionListener class. This code also applies MEDIATOR,
			//letting a central object mediate events that pertain to an underlying
			//object model.
			undoButton.addActionListener(mediator.undoAction());
		}
		return undoButton;
	}

	// Create a standard picture of a machine
	protected Component createPictureBox(Point p) {
		Component result = new JButton("machine", image);
		result.setSize(128, 128);
		result.setLocation(p);

		result.addMouseListener(mediator.mouseDownAction());
		return result; 
	}

	//The stateChanged() method must clear all the picture
	//box controls from the machine panel, and re-add new picture
	//boxes from the current set of locations in the factory model. The
	//stateChanged() method must also disable the Undo button if the factory
	//has only a single memento left on its stack.
	public void stateChanged(ChangeEvent e) {
		machinePanel().removeAll();

		List<Point> locations = factoryModel.getLocations();

		for (Point p : locations) {
			machinePanel().add(createPictureBox(p));
		}

		undoButton().setEnabled(factoryModel.canUndo());

		repaint();
	}
}