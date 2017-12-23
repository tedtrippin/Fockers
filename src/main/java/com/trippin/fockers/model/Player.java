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

    public Player(int x, int y, int speed, int direction, BufferedImage image) {
        super(x, y, speed, direction);
        this.image = image;
        xMiddle = (image.getHeight() / 2);
        yMiddle = (image.getWidth() / 2);
    }

    @Override
    public void click(Engine engine) {

        // Upwards deceleration
        if (ySpeed > 0) { // Heading down
            if (yModifier < 3)
                yModifier += 0.1;

        } else if (Math.abs(xSpeed) < -2) { // Heading up'ish or not going fast enough
            if (yModifier < 3)
                yModifier += Math.abs(ySpeed) / 30;

        } else if (yModifier > 0) {
            yModifier -= 0.1;
        }

        posX += xSpeed;
        posY += ySpeed + yModifier;

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
//        g2d.drawString("X:" + (int)posX, (int)posX, (int)posY + 52);
//        g2d.drawString("Y:" + (int)posY, (int)posX, (int)posY + 65);
//        g2d.drawString("ySpeed:" + (int)ySpeed, (int)posX, (int)posY + 78);
//        g2d.drawString("YModifier:" + (int)yModifier, (int)posX, (int)posY + 91);
    }
}
