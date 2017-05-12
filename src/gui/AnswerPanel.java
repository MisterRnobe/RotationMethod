package gui;

import javax.swing.*;

import java.awt.*;

import static gui.Frame.MAX_SIZE;

/**
 * Created by Dell on 08.05.2017.
 */
public class AnswerPanel extends JPanel {
    JLabel[] answers;
    static AnswerPanel instance;

    public static AnswerPanel getInstance() {
        return instance;
    }

    AnswerPanel()
    {
        super();
        instance = this;
        this.add(new JLabel("Решения: "));
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        answers = new JLabel[MAX_SIZE];
        for (int i = 0; i < MAX_SIZE; i++) {
            answers[i] = new JLabel();
            answers[i].setVisible(false);
            this.add(answers[i]);
        }
    }
    public void setAnswers(double[] d)
    {
        for (int i = 0; i < answers.length; i++) {
            answers[i].setText("");
            answers[i].setVisible(false);
        }
        for (int i = 0; i < d.length; i++) {
            answers[i].setText(String.format("X%s = %.2f",Element.toLowCase(Integer.toString(i+1)), d[i]));//"X"+Element.toLowCase(Integer.toString(i+1))+" = "+d[i]);
            answers[i].setVisible(true);
        }
    }

    public Dimension getPreferredSize()
    {
        Dimension dim = super.getPreferredSize();
        dim.width += 20;
        return dim;
    }

}
