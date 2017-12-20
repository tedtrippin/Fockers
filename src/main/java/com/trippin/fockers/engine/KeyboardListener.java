package com.trippin.fockers.engine;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.AbstractAction;
import javax.swing.JComponent;
import javax.swing.KeyStroke;

import com.trippin.fockers.model.Player;

public class KeyboardListener {

    private final long INTERVAL = 50;

    private int KEY_LEFT = KeyEvent.VK_A;
    private int KEY_RIGHT = KeyEvent.VK_D;
    private int KEY_SHOOT1 = KeyEvent.VK_SPACE;

    private final Player player;
    private final JComponent component;
    private Timer turnLeftTimer;
    private Timer turnRightTimer;

    public KeyboardListener(JComponent component, Player player) {

        this.component = component;
        this.player = player;

        bindKeys();
    }

    /**
     * Binds key strokes to the player controls.
     */
    private void bindKeys() {

        // Note to self - KeyStroke.getKeyStroke('a') is same as "typed a" which is
        // same as a keyPressed/keyReleased. This means you can't register a keyReleased
        // action cus the released event is swallowed.

        component.getInputMap().put(KeyStroke.getKeyStroke(KEY_LEFT, 0), "ANTI-CLOCKWISE");
        component.getActionMap().put("ANTI-CLOCKWISE", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                antiClockwiseStart();
            }
        });

        component.getInputMap().put(KeyStroke.getKeyStroke(KEY_RIGHT, 0), "CLOCKWISE");
        component.getActionMap().put("CLOCKWISE", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clockwiseStart();
            }
        });

        component.getInputMap().put(KeyStroke.getKeyStroke(KEY_LEFT, 0, true), "STOP-ANTI-CLOCKWISE");
        component.getActionMap().put("STOP-ANTI-CLOCKWISE", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                antiClockwiseStop();
            }
        });

        component.getInputMap().put(KeyStroke.getKeyStroke(KEY_RIGHT, 0, true), "STOP-CLOCKWISE");
        component.getActionMap().put("STOP-CLOCKWISE", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clockwiseStop();
            }
        });
    }


    private void antiClockwiseStart() {

        if (turnLeftTimer != null)
            return;

        turnLeftTimer = new Timer();
        turnLeftTimer.scheduleAtFixedRate(new TurnTask(-0.1), 0L, INTERVAL);
    }

    private void clockwiseStart() {

        if (turnRightTimer != null)
            return;

        turnRightTimer = new Timer();
        turnRightTimer.scheduleAtFixedRate(new TurnTask(0.1), 0L, INTERVAL);
    }

    private void antiClockwiseStop() {
        turnLeftTimer.cancel();
        turnLeftTimer = null;
    }

    private void clockwiseStop() {
        turnRightTimer.cancel();
        turnRightTimer = null;
    }

    class TurnTask extends TimerTask {

        private double step;

        public TurnTask(double step) {
            this.step = step;
        }

        @Override
        public void run() {
            player.setDirection(player.getDirection() + step);
        }
    }
}
