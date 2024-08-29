package de.acranum.launcher;

import de.acranum.Util.frameBuilder;
import de.acranum.decrypt.decryptC;
import de.acranum.decrypt.hashing;
import de.acranum.encrypt.encryptC;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.NoSuchAlgorithmException;

public class Frame extends JFrame implements ActionListener {
    JCheckBox checkBox;
    JPasswordField passField;
    JTextField passField2;
    JTextField Int;
    JTextArea Data;
    JTextArea output;
    JButton run;
    JComboBox ComboBox;
    boolean showPass = false;

    public Frame() {
        JFrame frame = new frameBuilder().frame(500, 400, "vin.jar", false);
        new frameBuilder().label(10, 10, 50, 25, frame, "Key: ", true);
        passField = new frameBuilder().passwordField(70, 10, 100, 25, frame,"", true);
        passField2 = new frameBuilder().textField(70, 10, 100, 25, frame,"", true);
        checkBox = new frameBuilder().checkbox(180, 10, 100, 25, frame, "show key", this);

        new frameBuilder().label(300, 10, 50, 25, frame, "Int: ", true);
        Int = new frameBuilder().textField(360, 10, 25, 25, frame, "0", true);

        new frameBuilder().label(10, 45, 50, 25, frame, "Data: ", true);
        Data = new frameBuilder().textAreaScroll(60, 45, 300, 200, frame, "", new Font("Arial", Font.PLAIN, 15));

        String[] objs = {"encrypt", "decrypt"};
        ComboBox = new frameBuilder().ComboBox(60, 280, 200, 50, frame, objs,this, new Font("Arial", Font.PLAIN, 15));
        run = new frameBuilder().button(380, 290, 80, 35, frame, "Run >>", this);

        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == checkBox) {
            if (checkBox.isSelected()) {
                passField.setVisible(false);
                passField2.setVisible(true);
                passField2.setText(passField.getText());
                showPass = true;
            } else {
                passField.setVisible(true);
                passField2.setVisible(false);
                passField.setText(passField2.getText());
                showPass = false;
            }
        }
        if (e.getSource() == run) {
            int i;
                try {
                    i = Integer.parseInt(Int.getText());
                } catch (Exception ex) {
                    i = 0;
                    Int.setText("0");
                }

                encryptC encrypt = new encryptC();
                decryptC decrypt = new decryptC();
                String key = null;
                String finalKey;
                if (showPass) {
                    key = passField2.getText();
                } else {
                    key = passField.getText();
                }
                hashing hashing = new hashing();
                try {
                    finalKey = hashing.md5(key);
                } catch (NoSuchAlgorithmException exx) {
                    throw new RuntimeException(exx);
                }


            if (ComboBox.getSelectedItem() == "encrypt") {
                        String Round = "";
                        Round = encrypt.encrypt(Data.getText(), finalKey);
                        for (int ii = 0; ii < i; ii++) {
                            Round = encrypt.encrypt(Round, finalKey);
                        }
                        Ergebnis(Round);
                    } else {
                        String Round = "";
                        Round = decrypt.decrypt(Data.getText(), finalKey);
                        for (int ii = 0; ii < i; ii++) { //FIXME: add int support for decryption
                            Round.replace("\\s", "");
                            Round = decrypt.decrypt(Round, finalKey);
                        }
                        Round.replace("\\s", "");
                        Ergebnis(Round);
                    }
        }
    }

    public void Ergebnis(String Ergebnis) {
        JFrame frame = new frameBuilder().frame(300, 300, "result.jar", false);
        output = new frameBuilder().textAreaScroll(10, 10, 250, 230, frame, "", new Font("Arial", Font.PLAIN, 15));
        output.setText(Ergebnis);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }
}
