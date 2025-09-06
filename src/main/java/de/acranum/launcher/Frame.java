package de.acranum.launcher;

import de.acranum.Util.Notification;
import de.acranum.Util.NotificationLabel;
import de.acranum.Util.frameBuilder;
import de.acranum.decrypt.decryptC;
import de.acranum.decrypt.hashing;
import de.acranum.encrypt.encryptC;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.security.NoSuchAlgorithmException;

public class Frame extends JFrame implements ActionListener, ChangeListener, KeyListener {
    JCheckBox PasswordVisibilityBox;
    JPasswordField passField;
    JSpinner Multiply;
    JTextArea Data;
    JButton run;
    JComboBox<String> ComboBox;

    JFrame frame = frameBuilder.frame(500, 400, "vin.jar", false);
    public Frame() {
        frameBuilder.label(10, 10, 50, 25, frame, "Key: ", null, null,true);
        passField = frameBuilder.passwordField(70, 10, 100, 25, frame,"", this,true);
        PasswordVisibilityBox = frameBuilder.checkbox(180, 10, 100, 25, frame, "show key", this);

        frameBuilder.label(300, 10, 50, 25, frame, "Multiply: ", null, null,true);
        //Int = frameBuilder.textField(360, 10, 25, 25, frame, "0", true);
        Multiply = frameBuilder.numberField(360, 10, 50, 25, frame, 1, 1, 100, this, true);

        frameBuilder.label(10, 45, 50, 25, frame, "Data: ", null, null,true);
        Data = frameBuilder.textAreaScroll(60, 45, 300, 200, frame, "", new Font("Arial", Font.PLAIN, 15), this);

        String[] objs = {"encrypt", "decrypt"};
        ComboBox = frameBuilder.comboBox(60, 280, 200, 50, frame, objs,this, new Font("Arial", Font.PLAIN, 15));
        run = frameBuilder.button(380, 290, 80, 35, frame, "Run >>", this);

        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
    @Override
    public void stateChanged(ChangeEvent e) {
        if (e.getSource() == Multiply) {
            if ((int) Multiply.getValue() > 20) {
                NotificationLabel.getInstance().addNotification(frame, Notification.HIGH_MULTIPLYER);
            } else {
                NotificationLabel.getInstance().removeNotification(frame, Notification.HIGH_MULTIPLYER);
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (passField == null || Data == null) return;
        if (passField.getPassword().length == 0) {NotificationLabel.getInstance().addNotification(frame, Notification.NO_KEY);} else {NotificationLabel.getInstance().removeNotification(frame, Notification.NO_KEY);}
        if (Data.getText().isEmpty()) {NotificationLabel.getInstance().addNotification(frame, Notification.NO_MESSAGE);} else NotificationLabel.getInstance().removeNotification(frame, Notification.NO_MESSAGE);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == PasswordVisibilityBox) {
            passwordVisibility(PasswordVisibilityBox.isSelected());
            if (PasswordVisibilityBox.isSelected()) NotificationLabel.getInstance().addNotification(frame, Notification.SHOW_KEY); else NotificationLabel.getInstance().removeNotification(frame, Notification.SHOW_KEY);
        }
        if (passField.getPassword().length == 0) {NotificationLabel.getInstance().addNotification(frame, Notification.NO_KEY);} else {NotificationLabel.getInstance().removeNotification(frame, Notification.NO_KEY);}
        if (Data.getText().isEmpty()) {NotificationLabel.getInstance().addNotification(frame, Notification.NO_MESSAGE);} else NotificationLabel.getInstance().removeNotification(frame, Notification.NO_MESSAGE);

        if (e.getSource() == run) {
            if (passField.getPassword().length == 0) {
                return;
            }
            if (Data.getText().isEmpty()) {
                return;
            }
            try {
                Run(passField.getPassword());
            } catch (NoSuchAlgorithmException ex) {
                throw new RuntimeException(ex);
            }
        }
    }
    private void passwordVisibility(boolean visibility) {
        if (visibility) {
            passField.setEchoChar((char) 0);
        } else {
            passField.setEchoChar('•');
        }
    }
    private void Run(char[] password) throws NoSuchAlgorithmException {
        encryptC encrypt = new encryptC();
        decryptC decrypt = new decryptC();

        String key = new hashing().md5(password);
        boolean encryptMode = ComboBox.getSelectedItem() == "encrypt";
        String text = Data.getText();
        for (int i = 1; i <= (int) Multiply.getValue(); i++) {
            if (encryptMode) {
                text = encrypt.encrypt(text, key);
            } else {
                text = decrypt.decrypt(text, key);
            }
            ShowResult(text, i, (int) Multiply.getValue());
        }

    }

    JFrame ResultFrame = null;
    JTextArea output;
    public void ShowResult(String result, int current , int total) {
        if (ResultFrame == null) ResultFrame = frameBuilder.frame(300, 300, "result", false);
        if (output == null) output = frameBuilder.textAreaScroll(10, 10, 250, 230, ResultFrame, "", new Font("Arial", Font.PLAIN, 15), this);
        ResultFrame.setTitle("result" + " (" + current + "/" + total + ")");
        output.setText(result);
        ResultFrame.setVisible(true);
        ResultFrame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }
}
