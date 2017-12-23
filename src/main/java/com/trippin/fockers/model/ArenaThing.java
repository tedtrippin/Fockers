package com.trippin.fockers.model;

import java.awt.Graphics;

import com.trippin.fockers.engine.Engine;

public interface ArenaThing {

    /**
     * Gets the x offset of the middle of this thing
     * relative to the left of the screen
     *
     * @return
     */
    public double getPosX();

    /**
     * Sets the x offset of this thing.
     *
     * @param x
     */
    public void setPosX(double x);

    /**
     * Gets the y offset of the bottom of this thing
     * relative to the top of the screen. Eg. a value
     * of zero would mean the bottom pixel of this thing
     * is on the top row of pixels on the arena.
     *
     * @return
     */
    public double getPosY();

    /**
     * Sets the y offset of this thing.
     *
     * @param y
     */
    public void setPosY(double y);

    /**
     * Gets the direction facing, in degrees. 0 is up.
     *
     * @return
     */
    public int getDirection();

    /**
     * Sets the facing direction. 0 is up.
     *
     * @param direction
     */
    public void setDirection(int direction);

    /**
     * Gets the speed of the thing. Same value
     * as velocity but always positive.
     *
     * @return
     */
    public double getSpeed();

    /**
     * Sets the speed.
     *
     * @param speed
     */
    public void setSpeed(double speed);

    /**
     * Gets the, per click, x position adjustment.
     *
     * @return
     */
    public double getXSpeed();

    /**
     * Gets the, per click, y position adjustment.
     *
     * @return
     */
    public double getYSpeed();

    /**
     * Increases throttle. Returns the throttle value.
     *
     * @return
     */
    public int accelerate();

    /**
     * Decreases throttle. Returns the throttle value.
     *
     * @return
     */
    public int decelerate();

    /**
     * Called by engine each click.
     */
    public void click(Engine engine);

    /**
     * Paint the thing.
     * @param g
     * @param engine
     */
    public void paint(Graphics g, Engine engine);
}
