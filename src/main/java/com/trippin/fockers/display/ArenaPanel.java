package com.trippin.fockers.display;

import java.awt.Graphics;
import java.awt.Image;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import com.trippin.fockers.engine.ArenaMask;
import com.trippin.fockers.engine.Engine;
import com.trippin.fockers.engine.KeyboardListener;
import com.trippin.fockers.model.Player;

public class ArenaPanel
    extends JPanel {

    private static final long serialVersionUID = 1L;

    private final Engine engine;
    private final Player player;
    private Image background;
    private Image offscreenImage;
    private int halfWidth;
    private int halfHeight;
    private int xLimit;
    private int yLimit;
    private KeyboardListener keyboardListener;

    ArenaPanel (Engine engine, Player player) {

        this.engine = engine;
        this.player = player;

        setPreferredSize(engine.getSize());
        keyboardListener = new KeyboardListener(this, player);
        setFocusable(true);

        URL backgroundUrl = ArenaMask.class.getClassLoader().getResource("background.jpg");
        try {
            background = ImageIO.read(backgroundUrl);
            background = background.getScaledInstance(engine.getArenaWidth(), engine.getArenaHeight(), 0);
        } catch (IOException ex) {
            ex.printStackTrace(System.err);
        }
    }

    @Override
    public void paint(Graphics g) {

        super.paint(g);

        requestFocusInWindow();

        if (offscreenImage == null) {
            offscreenImage = createImage(engine.getArenaWidth(), engine.getArenaHeight());
            halfWidth = this.getWidth() / 2;
            halfHeight = this.getHeight() / 2;
            xLimit = engine.getArenaWidth() - getWidth();
            yLimit = engine.getArenaHeight() - getHeight();
        }

        offscreenImage.getGraphics().drawImage(background, 0, 0, null);

        engine.getThings().forEach(t -> t.paint(offscreenImage.getGraphics(), engine));

        int x = (int) (player.getPosX() - halfWidth);
        if (x < 0)
            x = 0;
        else if (x > xLimit)
            x = xLimit;

        int y = (int) (player.getPosY() - halfHeight);
        if (y < 0)
            y = 0;
        else if (y > yLimit)
            y = yLimit;

        g.drawImage(offscreenImage, 0, 0, getWidth(), getHeight(), x, y, x + getWidth(), y + getHeight(), null);
        g.drawImage(offscreenImage, 0, 0, null);
    }
}