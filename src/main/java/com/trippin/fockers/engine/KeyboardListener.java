package com.trippin.fockers.engine;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Timer;
import java.util.TimerTask;

import com.trippin.fockers.model.Player;

public class KeyboardListener implements KeyListener {

    private final long INTERVAL = 100;

    private int KEY_LEFT = KeyEvent.VK_A;
    private int KEY_RIGHT = KeyEvent.VK_D;
    private int KEY_SHOOT1 = KeyEvent.VK_SPACE;

    private Player player;
    private Timer turnLeftTimer;
    private Timer turnRightTimer;

    public KeyboardListener(Player player) {
        this.player = player;
    }

    @Override
    public void keyPressed(KeyEvent e) {

        if (e.getKeyCode() == KEY_LEFT) {
            if (turnLeftTimer != null)
                turnLeftTimer.cancel();

            turnLeftTimer = new Timer();
            turnLeftTimer.scheduleAtFixedRate(new TurnTask(-0.1), 0L, INTERVAL);

        } else if (e.getKeyCode() == KEY_RIGHT) {
            if (turnRightTimer != null)
                turnRightTimer.cancel();

            turnRightTimer = new Timer();
            turnRightTimer.scheduleAtFixedRate(new TurnTask(0.1), 0L, INTERVAL);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

        if (e.getKeyCode() == KEY_LEFT) {
            turnLeftTimer.cancel();
            turnLeftTimer = null;

        } else if (e.getKeyCode() == KEY_RIGHT) {
            turnRightTimer.cancel();
            turnRightTimer = null;
        }
    }


    @Override
    public void keyTyped(KeyEvent e) {
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
