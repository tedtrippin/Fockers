package com.trippin.fockers.display;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import com.trippin.fockers.engine.ArenaMask;
import com.trippin.fockers.engine.Engine;
import com.trippin.fockers.model.Player;

public class MainMenu
    extends JPanel
    implements ActionListener {

    private static final long serialVersionUID = 1L;

    private final JFrame parent;
    private final JButton startButton;

    public MainMenu(JFrame parent) {

        this.parent = parent;

        startButton = new JButton("Start");
        startButton.addActionListener(this);
        add(startButton);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == startButton)
            start();
    }

    private void start() {

        // TODO - get real players
        List<Player> players = new ArrayList<>();

        URL planeUrl = ArenaMask.class.getClassLoader().getResource("red_plane.png");
        Player p1 = null;
        try {
            BufferedImage bi = ImageIO.read(planeUrl);
            p1 = new Player(100, 300, 4, 0, bi);
            players.add(p1);
        } catch (IOException ex) {
            ex.printStackTrace(System.err);
            System.exit(-1);
        }

        // TODO
        ArenaMask testArenaMask = new ArenaMask(2000, 1000);

        Engine engine = new Engine(testArenaMask, 40, players);

        // Create the game panel
        GamePanel gamePanel = new GamePanel(parent, engine, p1);
        Container parentContainer = parent.getContentPane();
        parentContainer.removeAll();
        parentContainer.add(gamePanel);
        parentContainer.validate();
    }
}
