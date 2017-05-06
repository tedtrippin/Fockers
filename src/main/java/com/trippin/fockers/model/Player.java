package com.trippin.fockers.model;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

import com.trippin.fockers.engine.Engine;

public class Player extends AbstractArenaThing{

    private BufferedImage image;
    private int xMod;
    private int yMod;

    public Player(int x, int y, int speed, double direction, BufferedImage image) {
        super(x, y, speed, direction);
        this.image = image;
        xMod = (image.getHeight() / 2);
        yMod = (image.getWidth() / 2);
    }

    @Override
    public void click(Engine engine) {
        posX += xSpeed;
        posY += ySpeed;
    }

    @Override
    public void paint(Graphics g, Engine engine) {

        AffineTransform tx = new AffineTransform();
        tx.setToTranslation(posX, posY);
        tx.rotate(direction, xMod, yMod);

        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(image, tx, null);
    }
}
