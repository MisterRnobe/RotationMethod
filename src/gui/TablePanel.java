package gui;



import core.Matrix;

import javax.swing.*;
import java.awt.*;

import static gui.Frame.MAX_SIZE;

/**
 * Created by Dell on 30.04.2017.
 */
public class TablePanel extends JPanel {
    private Element[][] input;
    private int count;
    private JLabel answerLabel;

    public static TablePanel getInstance() {
        return instance;
    }

    private static TablePanel instance;
    public TablePanel(int count)
    {
        super();
        instance = this;
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.input = new Element[MAX_SIZE][MAX_SIZE + 1];
        for (int i = 0; i < MAX_SIZE; i++) {
            ElementPanel panel = new ElementPanel();
            for (int j = 0; j < MAX_SIZE + 1; j++) {
                input[i][j] = new Element(j+1);
                panel.add(input[i][j]);
                input[i][j].setVisible(false);
            }
            this.add(panel);
        }
        update(count);
        answerLabel = new JLabel("");
        this.add(answerLabel);


    }
    public void update(int count)
    {
        if (count < this.count) {
            for (int i = 0; i < this.count; i++) {
                for (int j = 0; j < this.count + 1; j++) {
                    if (i > count - 1 || j> count)
                        input[i][j].setVisible(false);
                }
            }
        }
        else
        {
            for (int i = 0; i < count; i++) {
                for (int j = 0; j < count+1; j++) {
                    if (i > this.count - 1 || j > this.count)
                        input[i][j].setVisible(true);
                }
            }
        }
        for (int i = 0; i < this.count; i++) {
            input[i][this.count].setNormal();
            input[i][this.count-1].setNormal();
        }
        this.count = count;
        for (int i = 0; i < count; i++) {
            input[i][count].setLast();
            input[i][count-1].setBeforeLast();
        }
    }
    void set(Matrix m)
    {
        double[][] arr = m.getArray();
        this.reset();
        this.update(arr.length);
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[0].length; j++) {
                input[i][j].input.setText(Double.toString(arr[i][j]));
            }
        }
    }
    public double[] solve()
    {
        Number[] list = new Double[count*(count+1)];
        for (int i = 0; i < count; i++) {
            for (int j = 0; j < count+1; j++) {
                try{
                    list[i*(count+1)+j] = new Double(input[i][j].getText());
                }
                catch (NumberFormatException e)
                {
                    list[i * (1+count)+j] = new Double(0);
                }

            }
        }
        Matrix matrix = new Matrix(count, list);

        double[] answ = matrix.solve();
        if (answ.length <= 10)
            AnswerPanel.getInstance().setAnswers(answ);
        return answ;
    }
    public void reset()
    {
        for (int i = 0; i < MAX_SIZE; i++) {
            for (int j = 0; j < MAX_SIZE+1; j++) {
                input[i][j].input.setText("0");
            }
        }
    }
    class ElementPanel extends JPanel
    {
        ElementPanel()
        {
            super();
        }
    }
}
