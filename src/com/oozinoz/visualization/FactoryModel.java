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

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/** A model of a factory; at the moment this model just contains
 *  machine locations. However, the factory also provides support
 *  for undo by storing off mementos each time the factory
 *  configuration changes.
 */

// TODO: 1/19/2024 Memento Design Pattern
//The intent of the MEMENTO pattern is to provide storage and restoration
//of an object’s state.

//The FactoryModel class is at the center of our design. It is responsible
//for maintaining the current configuration of machines and for maintaining
//mementos of previous configurations.

//Each time a client asks the factory to add or move a machine, the factory
//will create a copy—a memento—of its current locations and
//push this onto its stack of mementos. In this example, we do not
//need a special Memento class. Each memento is merely a list of points:
//the list of machine locations at a particular time.
public class FactoryModel {
	private Stack<List<Point>> mementos;

	private List<ChangeListener> listeners = new ArrayList<>();

	public FactoryModel() {
		mementos = new Stack<>();
		mementos.push(new ArrayList<Point>()); // start empty
	}

	public void add(Point location) {
		//peek : Looks at the object at the top of this stack without removing it from the stack.
        List<Point> oldLocs = mementos.peek();
		List<Point> newLocs = new ArrayList<>(oldLocs);
		//Inserts the specified element at the specified position in this list
		//A subtlety here is that the code ensures that the new machine is first in
		//this list. This is a clue to the visualization that a picture of this
		//machine should appear in front of any other machines that the
		//picture may overlap.
		newLocs.add(0, location);

		mementos.push(newLocs);
		notifyListeners();
	}

	public void drag(Point oldLocation, Point newLocation) {
		List<Point> oldLocs = mementos.peek();
		List<Point> newLocs = new ArrayList<>(oldLocs);
		newLocs.remove(oldLocation);
		newLocs.add(0, newLocation);

		mementos.push(newLocs);
		notifyListeners();
	}

    public boolean canUndo() {
        return mementos.size() > 1;
    }

	public void undo() {
		//The top of the stack is always the current state, so the undo() code has only to pop the stack to
		//expose the previous memento.
		if (!canUndo()) return;

		mementos.pop();
		notifyListeners();
	}

	//The factory model’s latest configuration is always available from getLocations()
	public List<Point> getLocations() {
		return mementos.peek();
	}

    public void setLocations(List<Point> list) {
        mementos.push(list);
        notifyListeners();
    }

    public void addChangeListener(ChangeListener listener) {
		listeners.add(listener);
	}

	public void notifyListeners() {
		for (ChangeListener listener : listeners) {
			listener.stateChanged(new ChangeEvent(this));
		}
	}
}