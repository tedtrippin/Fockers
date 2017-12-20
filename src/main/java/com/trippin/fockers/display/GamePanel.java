package com.trippin.fockers.display;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

import com.trippin.fockers.engine.Engine;
import com.trippin.fockers.model.Player;

public class GamePanel extends JPanel {

    private static final long serialVersionUID = 1L;

    private Engine engine;
    private Thread engineThread;
    private ArenaPanel arena;

    public GamePanel(JFrame parent, Engine engine, Player player) {

        this.engine = engine;

        setLayout(new BorderLayout());

        // Add the control panel to the bottom
        ControlsPanel controlsPanel = new ControlsPanel(parent, engine);
        add(controlsPanel, BorderLayout.SOUTH);

        // Add the main game panel
        arena = new ArenaPanel(engine, player);
        add(arena, BorderLayout.CENTER);

        // TODO - Should wait for panel to finish drawing. For now we'll just wait.
        synchronized (this) {
            try {
                wait(100);
            } catch (InterruptedException ex) {
            }
        }

        engineThread = new Thread(new EngineTask());
        engineThread.start();
    }

    private class EngineTask implements Runnable {

        @Override
        public synchronized void run() {

            try {
                while (engine.isRunning()) {
                    engine.click();
                    arena.repaint();
                    this.wait(engine.getSpeed());
                }
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }
}
