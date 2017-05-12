package gui;

import core.Matrix;
import gui.files.File;
import gui.files.FilePanel;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import static gui.Frame.MAX_SIZE;

/**
 * Created by Dell on 07.05.2017.
 */
public class ControlPanel extends JPanel {
    private JButton solve, load, save, reset, random;
    private JComboBox box;
    private FilePanel filePanel;
    private Matrix m;
    private double[] sol;
    ControlPanel()
    {
        super();
        
        Integer[] arr = new Integer[MAX_SIZE-1];
        for (int i = 2; i < arr.length+2; i++) {
            arr[i-2] = i;
        }

        this.box = new JComboBox<>(arr);
        box.addItemListener((e -> {
            if (e.getStateChange() == ItemEvent.SELECTED)
                TablePanel.getInstance().update((Integer)e.getItem());
        }));
        box.setFont(new Font("Times New Roman",Font.PLAIN, 14));
        this.add(box);
        
        solve = new JButton("Решить");
        solve.addActionListener(e -> {
            sol = TablePanel.getInstance().solve();
        });
        this.add(solve);

        reset = new JButton("Сбросить");
        reset.addActionListener(e -> TablePanel.getInstance().reset());
        this.add(reset);

        load = new JButton("Загрузить");
        load.addActionListener(e -> {
            try{
                String s = createFileOpen();
                m = File.loadMatrix(s);
                System.out.println(m == null);
                if (m.getArray().length <= 10) {
                    TablePanel.getInstance().set(m);
                    box.setSelectedItem(new Integer(m.getArray().length));
                }
                else
                    disableButtons();
                filePanel.enablePanel(s);
            }
            catch (Exception exception)
            {
                System.out.println(exception.getMessage());
            }

        });
        this.add(load);

        save = new JButton("Сохранить");
        save.addActionListener(e ->
        {
            System.out.println(m == null);
            if (sol == null)
                if (m == null || m.getArray().length<=10)
                    sol = TablePanel.getInstance().solve();
                else
                    sol = m.solve();
            try{
                String s = saveFile();
                File.saveSolutions(sol, s);
                filePanel.disablePanel();
                enableButtons();

            }
            catch (Exception ex)
            {
                System.out.println(ex.getMessage());
            }
            sol = null;
            m = null;
        });
        this.add(save);

        filePanel = new FilePanel(this);
        filePanel.setVisible(false);
        this.add(filePanel);
    }
    private String createFileOpen()
    {
        JFileChooser chooser = new JFileChooser();
        chooser.setFileFilter(new FileNameExtensionFilter("XLSX Таблица","xlsx"));
        int ret = chooser.showOpenDialog(null);
        if (ret == JFileChooser.APPROVE_OPTION)
            return chooser.getSelectedFile().getAbsolutePath();
        else
            return null;
    }
    private String saveFile()
    {
        JFileChooser chooser = new JFileChooser();
        chooser.setFileFilter(new FileNameExtensionFilter("XLSX Таблица","xlsx"));
        int ret = chooser.showSaveDialog(null);
        if (ret == JFileChooser.APPROVE_OPTION)
            return chooser.getSelectedFile().getAbsolutePath();
        else
            return null;
    }
    private void disableButtons()
    {
        box.setEnabled(false);
        reset.setEnabled(false);
        solve.setEnabled(false);
    }
    private void enableButtons()
    {
        box.setEnabled(true);
        reset.setEnabled(true);
        solve.setEnabled(true);
    }
    public void reset()
    {
        enableButtons();
        filePanel.disablePanel();
        this.m = null;
        this.sol = null;
    }
}
