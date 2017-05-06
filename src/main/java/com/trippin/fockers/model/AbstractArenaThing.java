package com.trippin.fockers.model;


public abstract class AbstractArenaThing implements ArenaThing {

    protected double posX; // Bottom of thing
    protected double posY; // Middle of thing
    protected double direction;
    protected double xSpeed;
    protected double ySpeed;

    private int speed;

    public AbstractArenaThing(int x, int y, int speed, double direction) {
        posX = x;
        posY = y;
        this.speed = speed;
        this.direction = direction;
        updated();
    }

    @Override
    public double getPosX() {
        return posX;
    }

    @Override
    public void setPosX(double posX) {
        this.posX = posX;
    }

    @Override
    public double getPosY() {
        return posY;
    }

    @Override
    public void setPosY(double posY) {
        this.posY = posY;
    }

    @Override
    public double getDirection() {
        return direction;
    }

    @Override
    public void setDirection(double direction) {
        this.direction = direction;
        updated();
    }

    @Override
    public int getSpeed() {
        return speed;
    }

    @Override
    public void setSpeed(int speed) {
        this.speed = speed;
        updated();
    }

    @Override
    public double getXSpeed() {
        return xSpeed;
    }

    @Override
    public double getYSpeed() {
        return ySpeed;
    }

    private void updated() {
        xSpeed = Math.cos(direction) * speed;
        ySpeed = Math.sin(direction) * speed;
    }
}
