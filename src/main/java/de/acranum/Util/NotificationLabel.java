package de.acranum.Util;

import javax.swing.*;
import javax.swing.event.ChangeListener;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;

public class NotificationLabel extends JLabel {


    private final ArrayList<JLabel> notifications;
    private static NotificationLabel instance = new NotificationLabel();

    public NotificationLabel() {
        notifications = new ArrayList<>();
    }

    public static NotificationLabel getInstance() {
        return instance;
    }

    public void addNotification(JFrame frame, Notification notification) {
        for (JLabel label : notifications) {
            if (label.getText().equals(notification.getNotification())
                    && label.getForeground().equals(notification.getType().getColor())) {
                return;
            }
        }
        JLabel label = frameBuilder.label(60, 250 + (notifications.size() * 15), 400, 10, frame, notification.getNotification(), null, notification.getType().getColor(),true);
        notifications.add(label);
        repositionNotifications(frame);
        frame.repaint();
    }

    public void removeNotification(JFrame frame, Notification notification) {
        for (JLabel label : new ArrayList<>(notifications)) {
            if (label.getText().equals(notification.getNotification()) && label.getForeground().equals(notification.getType().getColor())) {
                frame.remove(label);
                notifications.remove(label);
                frame.repaint();
                repositionNotifications(frame);
            }
        }
    }
    private void repositionNotifications(JFrame frame) {
        notifications.sort((a, b) -> {
            if (a.getText().contains("❌") && !b.getText().contains("❌")) return -1;
            if (!a.getText().contains("❌") && b.getText().contains("❌")) return 1;

            if (a.getText().contains("⚠️") && !b.getText().contains("⚠️")) return -1;
            if (!a.getText().contains("⚠️") && b.getText().contains("⚠️")) return 1;

            if (a.getText().contains("🔧") && !b.getText().contains("🔧")) return -1;
            if (!a.getText().contains("🔧") && b.getText().contains("🔧")) return 1;

            return 0;
        });
        for (int i = 0; i < notifications.size(); i++) {
            JLabel label = notifications.get(i);
            label.setBounds(60, 250 + (i * 15), 400, 15);
            if (i >= 2) label.setVisible(false);
            else label.setVisible(true);
        }
        frame.repaint();
    }

    public void clearMessages(JFrame frame) {
        for (JLabel label : notifications) {
            frame.remove(label);
        }
        notifications.clear();
        frame.repaint();
    }
}
