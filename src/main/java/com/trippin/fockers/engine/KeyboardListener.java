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

    private final long INTERVAL = 20;

    private int KEY_LEFT = KeyEvent.VK_A;
    private int KEY_RIGHT = KeyEvent.VK_D;
    private int KEY_UP = KeyEvent.VK_W;
    private int KEY_DOWN = KeyEvent.VK_S;
    private int KEY_SHOOT1 = KeyEvent.VK_SPACE;

    private final Player player;
    private final JComponent component;
    private final Engine engine;
    private Timer turnTimer;
    private Timer throttleTimer;

    public KeyboardListener(JComponent component, Player player, Engine engine) {

        this.component = component;
        this.player = player;
        this.engine = engine;

        bindKeys();
    }

    /**
     * Binds key strokes to the player controls.
     */
    private void bindKeys() {

        // Note to self - KeyStroke.getKeyStroke('a') is same as "typed a" which is
        // same as a keyPressed/keyReleased. This means you can't register a keyReleased
        // action cus the released event is swallowed.

        addCommand(KEY_LEFT, () -> startTurning(-2), () -> stopTurning());
        addCommand(KEY_RIGHT, () -> startTurning(2), () -> stopTurning());
        addCommand(KEY_UP, () -> startThrottle(true), () -> stopThrottle());
        addCommand(KEY_DOWN, () -> startThrottle(false), () -> stopThrottle());
    }

    private void addCommand(int key, DoAction onPress, DoAction onRelease) {

        component.getInputMap().put(KeyStroke.getKeyStroke(key, 0), "PRESS_" + key);
        component.getActionMap().put("PRESS_" + key, new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onPress.doSomething();
            }
        });
        component.getInputMap().put(KeyStroke.getKeyStroke(key, 0, true), "RELEASE_" + key);
        component.getActionMap().put("RELEASE_" + key, new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onRelease.doSomething();
            }
        });
    }

    private void startTurning(int direction) {

        if (turnTimer != null)
            return;

        // Can't turn till got up speed and rising
        if (player.getSpeed() < 2 && player.getPosY() >= engine.getGroundLevel())
            return;

        turnTimer = new Timer();
        turnTimer.scheduleAtFixedRate(new TurnTask(direction), 0L, INTERVAL);
    }

    private void stopTurning() {

        if (turnTimer != null)
            turnTimer.cancel();

        turnTimer = null;
    }

    private void startThrottle(boolean accelerate) {

        if (throttleTimer != null)
            return;

        throttleTimer = new Timer();
        throttleTimer.scheduleAtFixedRate(new ThrottleTask(accelerate), 0L, INTERVAL);
    }

    private void stopThrottle() {
        throttleTimer.cancel();
        throttleTimer = null;
    }

    class TurnTask extends TimerTask {

        private int step;

        public TurnTask(int step) {
            this.step = step;
        }

        @Override
        public void run() {
            player.setDirection(player.getDirection() + step);
        }
    }

    class ThrottleTask extends TimerTask {

        private boolean accelerate;

        public ThrottleTask(boolean accelerate) {
            this.accelerate = accelerate;
        }

        @Override
        public void run() {

            if (accelerate)
                player.accelerate();
            else
                player.decelerate();
        }
    }

    static interface DoAction {
        void doSomething();
    }
}
