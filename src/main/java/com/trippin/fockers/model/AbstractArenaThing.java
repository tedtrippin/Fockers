package com.trippin.fockers.model;


public abstract class AbstractArenaThing implements ArenaThing {

    private static double[] sinTable = new double[360];
    private static double[] cosTable = new double[360];
    static double[] radiansTable = new double[360];

    static {
        double oneDegreeInRadians = Math.PI / 180;
        for (int i = 0; i < 360; i++) {
            double radians = i * oneDegreeInRadians;
            sinTable[i] = Math.sin(radians);
            cosTable[i] = Math.cos(radians);
            radiansTable[i] = radians;
        }
    }

    protected double posX; // Bottom of thing
    protected double posY; // Middle of thing
    protected int direction;
    protected double xSpeed;
    protected double ySpeed;

    private int speed;

    public AbstractArenaThing(int x, int y, int speed, int direction) {
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
    public int getDirection() {
        return direction;
    }

    @Override
    public void setDirection(int direction) {

        if (direction < 0)
            direction += 360;
        else if (direction > 359)
            direction -= 360;

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
        xSpeed = cosTable[direction] * speed;
        ySpeed = sinTable[direction] * speed;
    }
}
