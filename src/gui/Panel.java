package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.LinkedList;

import static gui.Frame.MAX_SIZE;

/**
 * Created by Dell on 30.04.2017.
 */
public class Panel extends JPanel {
    TablePanel panel;
    public Panel()
    {
        super(new BorderLayout());

        panel = new TablePanel(2);
        this.add(panel, BorderLayout.CENTER);
        ControlPanel controlPanel = new ControlPanel();
        this.add(controlPanel,BorderLayout.NORTH);
        this.add(new AnswerPanel(), BorderLayout.EAST);
    }

}
