package com.trippin.fockers;

import javax.swing.JFrame;

import com.trippin.fockers.display.MainMenu;

public class Fockers extends JFrame {

    private static final long serialVersionUID = 1L;

    public static void main(String args[]) {

        Fockers fockers = new Fockers();
        fockers.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fockers.setSize(1200, 800);
        fockers.setVisible(true);
        fockers.setLocation(300, 100);
    }

    public Fockers() {
        add(new MainMenu(this));
    }
}
