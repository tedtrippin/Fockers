package com.trippin.fockers.display;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.JViewport;

import com.trippin.fockers.engine.ArenaMask;
import com.trippin.fockers.engine.Engine;
import com.trippin.fockers.engine.KeyboardListener;
import com.trippin.fockers.model.Player;

public class ArenaPanel
    extends JPanel {

    private static final long serialVersionUID = 1L;

    private final JViewport parent;
    private final Engine engine;
    private final Player player;
    private Image background;

    ArenaPanel (JViewport parent, Engine engine, Player player) {

        this.parent = parent;
        this.engine = engine;
        this.player = player;

        setPreferredSize(engine.getSize());
        setAutoscrolls(true);

        addKeyListener(new KeyboardListener(player));

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

        grabFocus();

//        engine.getArenaMask().prePaint();
//        g.drawImage(engine.getArenaMask().getMap(), 0, 0, null);

        g.drawImage(background, 0, 0, null);

        engine.getThings().forEach(t -> t.paint(g, engine));

        Rectangle view = parent.getViewRect();
        view.x = (int)(player.getPosX() - (view.getWidth()/2));
        view.y = (int)(player.getPosY() - (view.getHeight()/2));
        scrollRectToVisible(view);
    }
}