package org.example;

import org.example.GUI.UI;

import javax.swing.*;

public class Main {
    public static void main(String args[]) {
        JFrame frame = new UI("GridBagLayoutDemo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 200);
        frame.setVisible(true);
    }
}


