package app.adapter;

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

import javax.swing.table.*;

import com.oozinoz.firework.Rocket;

/**
 * Adapt a collection of rockets for display in a JTable.
 * @author Steven J. Metsker
 */

// TODO: 1/3/2024 3- object adapter sample - JTable fueled with rocket data
public class RocketTableModel extends AbstractTableModel {
    protected Rocket[] rockets;
    protected String[] columnNames = new String[] { "Name", "Price", "Apogee" };

    /**
     * Construct a rocket table from an array of rockets.
     * @param rockets an array of rockets
     */
    public RocketTableModel(Rocket[] rockets) {
        this.rockets = rockets;
    }

    /**
      * @return the number of columns in this table.
     */
    public int getColumnCount() {
        return columnNames.length;
    }

    /**
     * @param index which column is interesting
     * @return the name of the indicated column
     */
    public String getColumnName(int index) {
        return columnNames[index];
    }

    /**
     * @return the number of rows in this table.
     */
    public int getRowCount() {
        return rockets.length;
    }

    /**
     * @param row which row is interesting
     * @param col which column is interesting
     * @return the value at the indicated row and column.
     */
    public Object getValueAt(int row, int col) {
        switch (col) {
        case 0:
            return rockets[row].getName();
        case 1:
            return rockets[row].getPrice();
        case 2:
            return rockets[row].getApogee();
        default:
            return null;
        }
    }
}