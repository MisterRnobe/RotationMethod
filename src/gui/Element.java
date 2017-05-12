package gui;

import javax.swing.*;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;
import java.awt.*;

/**
 * Created by Dell on 02.05.2017.
 */
public class Element extends JPanel {
    private int number;
    JTextField input;
    private JLabel info;
    public Element(int i)
    {
        super(new BorderLayout());
        this.number = i;
        this.info = new JLabel("X"+toLowCase(Integer.toString(i))+" + ");
        info.setHorizontalTextPosition(JLabel.LEFT);
        this.add(info,BorderLayout.EAST);


        this.input = new JTextField(2);

        this.input.setDocument(new PlainDocument(){
                @Override
                public void insertString(int offset, String str, AttributeSet attr) throws BadLocationException {
                    if (str == null)
                        return;
                    if (canPutString(getText(0,offset), str)) {
                        super.insertString(offset, str, attr);
                    }
                }
            });
        this.input.setText("0");
        this.add(input,BorderLayout.WEST);
    }
    public String getText()
    {
        return input.getText();
    }
    public void setLast()
    {
        this.info.setText("");
    }
    public void setBeforeLast()
    {
        this.info.setText("X"+toLowCase(Integer.toString(number))+" = ");
    }
    public void setNormal()
    {
        this.info.setText("X"+toLowCase(Integer.toString(number))+" + ");
    }
    public static String toLowCase(String s)
    {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
             builder.append((char)(s.charAt(i)-'0'+8320));
        }
        return builder.toString();
    }

    private static boolean canPutString(String str, String inserted)
    {
        if (str.length()!= 0) {
            int maxLength = 5;
            if (str.charAt(0) == '-')
                maxLength++;
            if (str.contains("."))
                maxLength++;
            if (str.length() + inserted.length() > maxLength)
                return false;
            boolean can = true;
            try {
                Float.parseFloat(str + inserted);
            } catch (NumberFormatException e) {
                can = false;
            }
            return can;
        }
        return  (inserted.charAt(0) >= '0' && inserted.charAt(0) <='9' || inserted.charAt(0) == '.' || inserted.charAt(0) == '-');
    }
}
