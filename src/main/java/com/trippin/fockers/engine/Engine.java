package com.trippin.fockers.engine;

import java.awt.Dimension;
import java.util.Collection;
import java.util.List;

import com.trippin.fockers.model.ArenaThing;
import com.trippin.fockers.model.Player;

public class Engine {

    private static int GROUND_LEVEL = 50;

    private boolean running = true;;
    private long speed = 100; // The delay between game clicks (smaller means faster)
    private final List<Player> players;
    private ArenaMask mask;
    private int arenaWidth;
    private int arenaHeight;

    public Engine(ArenaMask mask, long speed, List<Player> players) {
        this.mask = mask;
        this.speed = speed;
        this.players = players;
        arenaWidth = mask.getArenaWidth();
        arenaHeight = mask.getArenaHeight();
    }

    public void click() {
        players.forEach(t -> t.click(this));
    }

    public int getGroundLevel() {
        return arenaHeight - GROUND_LEVEL;
    }

    public Dimension getSize() {
        return new Dimension(arenaWidth, arenaHeight);
    }

    public Collection<? extends ArenaThing> getThings() {
        return players;
    }

    public int getArenaWidth() {
        return arenaWidth;
    }

    public int getArenaHeight() {
        return arenaHeight;
    }

    public boolean isRunning() {
        return running;
    }

    public void stop() {
        running = false;
    }

    public long getSpeed() {
        return speed;
    }

    public ArenaMask getArenaMask() {
        return mask;
    }
}
