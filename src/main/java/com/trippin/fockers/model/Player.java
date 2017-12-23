package com.trippin.fockers.model;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

import com.trippin.fockers.engine.Engine;

public class Player extends AbstractArenaThing {

    private BufferedImage image;
    private int xMiddle;
    private int yMiddle;
    private double yModifier; // Like gravity, the more you fly up the larger it gets
    private int maxSpeed = 5;

    public Player(int x, int y, int speed, int direction, BufferedImage image) {
        super(x, y, speed, direction);
        this.image = image;
        xMiddle = (image.getHeight() / 2);
        yMiddle = (image.getWidth() / 2);
    }

    @Override
    public void click(Engine engine) {

        // Lift
        double absXSpeed = Math.abs(xSpeed);
        if (absXSpeed < 3) {
            if (yModifier < 10)
                yModifier += (3 - absXSpeed) / 10;
        } else if (yModifier > 0) {
            yModifier -= (absXSpeed - 3) / 20;
        }

        // Increase/reduce speed based on throttle
        int targetSpeed = throttle / 20;
        if (Math.abs(speed - targetSpeed) > 0.1) {
            if (speed > targetSpeed)
                setSpeed(speed - 0.1);
            else
                setSpeed(speed + 0.1);
        }

        posX += xSpeed;
        posY += ySpeed + yModifier;
        if (posY > engine.getGroundLevel())
            posY = engine.getGroundLevel();

        if (posX < 0)
            posX = engine.getArenaWidth();
        else if (posX > engine.getArenaWidth())
            posX = 0;
    }

    @Override
    public void paint(Graphics g, Engine engine) {

        AffineTransform tx = new AffineTransform();
        tx.setToTranslation(posX, posY);
        tx.rotate(radiansTable[direction], xMiddle, yMiddle);

        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(image, tx, null);
//        g2d.drawString("speed:" + speed, (int)posX, (int)posY + 52);
        g2d.drawString("xSpeed:" + xSpeed, (int)posX, (int)posY + 78);
//        g2d.drawString("X:" + (int)posX, (int)posX, (int)posY + 52);
//        g2d.drawString("Y:" + (int)posY, (int)posX, (int)posY + 65);
        g2d.drawString("YModifier:" + (int)yModifier, (int)posX, (int)posY + 91);
    }
}
