package gui;

import javax.swing.*;

/**
 * Created by Dell on 30.04.2017.
 */
public class Frame extends JFrame {
    public static final int MAX_SIZE = 10;
    public Frame()
    {
        super();
        this.setContentPane(new Panel());
        this.setSize(700,700);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setVisible(true);
    }
}
