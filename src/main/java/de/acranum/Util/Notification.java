package de.acranum.Util;

import java.awt.*;

public enum Notification {
    NO_KEY("️❌ No Key is specified", NotificationType.ERROR),
    NO_MESSAGE("❌ No Message is specified", NotificationType.ERROR),
    HIGH_MULTIPLYER("⚠️ Too high multiplier can lead to performance issues", NotificationType.WARNING),
    SHOW_KEY("🔧 Key is visible", NotificationType.NOTIFICATION);

    private final String notification;
    private final NotificationType type;

    Notification(String notification, NotificationType type) {
        this.notification = notification;
        this.type = type;
    }

    public String getNotification() {
        return notification;
    }
    public NotificationType getType() {
        return type;
    }

    public enum NotificationType {
        NOTIFICATION(Color.GRAY),
        WARNING(new Color(0xFF8C00)),
        ERROR(Color.RED);

        private final Color color;

        NotificationType(Color color) {
            this.color = color;
        }

        public Color getColor() {
            return color;
        }
    }
}
