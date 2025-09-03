package de.acranum.Util;

import javax.swing.*;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionListener;

public class frameBuilder {

    public frameBuilder() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception var3) {
            var3.printStackTrace();
        }
    }

    public static JFrame frame(int x, int y, String title, boolean resizable) {
        JFrame frame = new JFrame();
        frame.setSize(x, y);
        frame.setTitle(title);
        frame.setLayout(null);
        frame.setResizable(resizable);
        return frame;
    }


    public static JButton button(int x, int y, int width, int height, JFrame frame, String txt, ActionListener ac) {
        JButton button = new JButton(txt);
        button.setBounds(x, y, width, height);
        button.addActionListener(ac);
        frame.add(button);
        return button;
    }

    public static JLabel label(int x, int y, int width, int height, JFrame frame, String txt, Font font, Color color, boolean visibility) {
        JLabel label = new JLabel(txt);
        if (font != null) label.setFont(font);
        if (color != null) label.setForeground(color);
        label.setBounds(x, y, width, height);
        frame.add(label);
        label.setVisible(visibility);
        return label;
    }

    public static JTextField textField(int x, int y, int width, int height, JFrame frame, String txt, boolean visibility) {
        JTextField textField = new JTextField(txt);
        textField.setBounds(x, y, width, height);
        textField.setVisible(visibility);
        frame.add(textField);

        return textField;
    }
    public static JSpinner numberField(int x, int y, int width, int height, JFrame frame, int startValue, int min, int max, ChangeListener ch, boolean visibility) {
        SpinnerNumberModel model = new SpinnerNumberModel(startValue, min, max, 1);
        JSpinner numberField = new JSpinner(model);
        numberField.addChangeListener(ch);
        numberField.setBounds(x, y, width, height);
        numberField.setVisible(visibility);
        frame.add(numberField);

        return numberField;
    }

    public static JPasswordField passwordField(int x, int y, int width, int height, JFrame frame, String txt, boolean visibility) {
        JPasswordField textField = new JPasswordField(txt);
        textField.setBounds(x, y, width, height);
        textField.setVisible(visibility);
        frame.add(textField);
        textField.setVisible(true);
        return textField;
    }

    public static JCheckBox checkbox(int x, int y, int width, int height, JFrame frame, String txt, ActionListener ac) {
        JCheckBox checkBox = new JCheckBox(txt);
        checkBox.setBounds(x, y, width, height);
        checkBox.addActionListener(ac);

        frame.add(checkBox);
        checkBox.setVisible(true);
        return checkBox;
    }

    public static JTextArea textArea(int x, int y, int width, int height, JFrame frame, String txt, Font font) {
        JTextArea text = new JTextArea(txt);
        text.setLineWrap(true);
        text.setWrapStyleWord(true);
        text.setFont(font);
        text.setBounds(x, y, width, height);
        frame.add(text);
        return text;
    }

    public static JTextArea textAreaScroll(int x, int y, int width, int height, JFrame frame, String txt, Font font) {
        JTextArea text = new JTextArea(txt);
        text.setLineWrap(true);
        text.setWrapStyleWord(true);
        //text.setBounds(x, y, width, height);
        text.setFont(font);
        text.setEditable(true);

        JScrollPane scroll = new JScrollPane(text);
        scroll.setBounds(x,y,width,height);
        scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);


        frame.add(scroll);
        text.setVisible(true);
        scroll.setVisible(true);
        return text;
    }

    public static JComboBox comboBox(int x, int y, int width, int height, JFrame frame, String[] objects, ActionListener ac, Font font) {
        JComboBox box = new JComboBox(objects);
        box.addActionListener(ac);
        box.setFont(font);
        box.setBounds(x, y, width, height);
        box.setSelectedItem(objects[0]);
        frame.add(box);
        return box;
    }



}
