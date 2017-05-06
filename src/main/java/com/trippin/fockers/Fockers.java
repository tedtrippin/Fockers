package com.trippin.fockers;

import javax.swing.JFrame;

import com.trippin.fockers.display.MainMenu;

public class Fockers extends JFrame {

    private static final long serialVersionUID = 1L;

    public static void main(String args[]) {

        Fockers fockers = new Fockers();
        fockers.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fockers.setSize(500, 500);
        fockers.setVisible(true);
    }

    public Fockers() {
        add(new MainMenu(this));
    }
}
