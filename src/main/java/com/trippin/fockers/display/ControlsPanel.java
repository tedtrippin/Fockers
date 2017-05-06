package com.trippin.fockers.display;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import com.trippin.fockers.engine.Engine;

public class ControlsPanel
    extends JPanel
    implements ActionListener {

    private final JFrame parent;
    private final Engine engine;
    private final JButton stopButton;

    private static final long serialVersionUID = 1L;

    ControlsPanel(JFrame parent, Engine engine) {

        this.parent = parent;
        this.engine = engine;

        setMinimumSize(new Dimension(100, 20));

        stopButton = new JButton("stop");
        stopButton.addActionListener(this);
        add(stopButton);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == stopButton) {
            stop();
        }
    }

    private void stop() {

        engine.stop();

        Container parentContainer = parent.getContentPane();
        parentContainer.removeAll();
        parentContainer.add(new MainMenu(parent));
        parentContainer.validate();
    }
}
