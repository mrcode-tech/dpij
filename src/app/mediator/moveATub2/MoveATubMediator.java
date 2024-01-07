package app.mediator.moveATub2;

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

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JList;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
// TODO: 1/7/2024  Mediator design pattern
public class MoveATubMediator implements ListSelectionListener, ActionListener {
    MoveATub2 gui;
    NameBase data;
    
    private String selectedMachine;
    private String selectedTub;
    
    public MoveATubMediator(MoveATub2 gui, NameBase data) {
        this.gui = gui;
        this.data = data;
    }
    
    public void actionPerformed(ActionEvent e) {
        if (selectedMachine == null || selectedTub == null) return;
        String fromMachineName = data.getMachineContaining(selectedTub);
        data.put(selectedTub, selectedMachine);
        updateTubList(fromMachineName);
        gui.assignButton().setEnabled(false);
    }


    public void valueChanged(ListSelectionEvent e) {
        @SuppressWarnings("unchecked") JList<String> sender = (JList<String>) e.getSource();

        if (! sender.isSelectionEmpty()) {
            String selection = sender.getSelectedValue();
            
            if (sender.equals(gui.boxList())) 
                this.updateTubList(selection);
            else if (sender.equals(gui.machineList())) 
                selectedMachine = selection;
            else if (sender.equals(gui.tubList()))
                selectedTub = selection;
        }
        
        gui.assignButton().setEnabled( 
            ! gui.tubList().isSelectionEmpty() && ! gui.machineList().isSelectionEmpty());
    }
    
    public void updateTubList(String machineName)
    {
        gui.tubList().setListData(data.tubNames(machineName));
    }
}
