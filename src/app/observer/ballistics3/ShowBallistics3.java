package app.observer.ballistics3;

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

import java.awt.Dimension;
import javax.swing.*;

import com.oozinoz.ballistics.Ballistics;
import com.oozinoz.ui.SwingFacade;
import javax.swing.event.*;
import java.awt.BorderLayout;
import java.awt.GridLayout;

/**
 * Show the standard burn rate and thrust equations. This class is refactored
 * from ShowBallistics2. The changes involve pushing the value of tPeak down
 * into a business layer and using a BallisticsLabel that knows about tPeak.
 * 
 * @author Steven J. Metsker
 */
// TODO: 1/6/2024 observer design pattern - use mvc To consolidate this duplicated code in stateChanged method in solution 2
public class ShowBallistics3 {
    /**
     * Show the standard burn rate and thrust equations.
     */
    public static void main(String[] args) {
        SwingFacade.launch(new ShowBallistics3().mainPanel(),
                "Effects of tPeak");
    }

    protected BallisticsPanel burnPanel;

    protected JSlider slider;

    protected double sliderMax;

    protected double sliderMin;

    protected BallisticsPanel thrustPanel;

    protected JLabel valueLabel;

    protected Tpeak tPeak = new Tpeak(0);

    protected BallisticsPanel burnPanel() {
        if (burnPanel == null) {
            burnPanel = new BallisticsPanel(Ballistics.rate(), tPeak);
            burnPanel.setPreferredSize(new Dimension(300, 200));
        }
        return burnPanel;
    }

    /*
     * A panel to contain the two plots.
     */
    protected JPanel curvePanel() {
        JPanel p = new JPanel();
        p.setLayout(new GridLayout(1, 2));
        p.add(SwingFacade.createTitledPanel("Burn Rate", burnPanel()));
        p.add(SwingFacade.createTitledPanel("Thrust", thrustPanel()));
        return p;
    }

    /*
     * The main panel -- the one that actually gets displayed.
     */
    protected JPanel mainPanel() {
        JPanel p = new JPanel();
        p.setLayout(new BorderLayout());
        p.add(curvePanel(), "Center");
        p.add(sliderBox(), "South");
        return p;
    }

    /*
     * Now the slider just tells the business domain tPeak object that the
     * slider moved.
     */
    //When the slider
    //moves, the application sets a new value in the Tpeak object. The panels
    //and the text box listen to the Tpeak object and update themselves
    //when the value changes. The BurnRate and Thrust classes use the
    //Tpeak object to calculate their functions, but they don’t need to listen
    //to—that is, register for—events.
    protected JSlider slider() {
        if (slider == null) {
            slider = new JSlider();
            sliderMax = slider.getMaximum();
            sliderMin = slider.getMinimum();

            //You also need to arrange for a Tpeak object
            //to change when the application’s slider changes. You can achieve this
            //by instantiating an anonymous subclass of ChangeListener.
            slider.addChangeListener(new ChangeListener() {
                public void stateChanged(ChangeEvent e) {
                    if (sliderMax == sliderMin) return;
                    
                    tPeak.setValue((slider.getValue() - sliderMin)
                            / (sliderMax - sliderMin));
                }
            });
            slider.setValue(slider.getMinimum());
        }
        return slider;
    }

    /*
     * The box that holds the slider plus a textual label and a changing label
     * that shows the value of the slider.
     */
    protected Box sliderBox() {
        Box b = Box.createHorizontalBox();
        JLabel label = new JLabel("tPeak");
        label.setFont(SwingFacade.getStandardFont());
        label.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));
        label.setForeground(java.awt.Color.black);
        b.add(label);
        b.add(valueLabel());
        b.add(slider());
        return b;
    }

    protected BallisticsPanel thrustPanel() {
        if (thrustPanel == null) {
            thrustPanel = new BallisticsPanel(Ballistics.thrust(), tPeak);
            thrustPanel.setPreferredSize(new Dimension(300, 200));
        }
        return thrustPanel;
    }

    protected JLabel valueLabel() {
        if (valueLabel == null) {
            valueLabel = new BallisticsLabel(tPeak);
            valueLabel.setFont(SwingFacade.getStandardFont());
            valueLabel.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));
            valueLabel.setForeground(java.awt.Color.black);
        }
        return valueLabel;
    }
}