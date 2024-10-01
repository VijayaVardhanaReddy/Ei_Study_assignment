package Ei_Study.Ex1.creational_design;

// Notification Interface
interface Notification {
    void sendNotification();
}

// Concrete Notification (Email)
class EmailNotification implements Notification {
    @Override
    public void sendNotification() {
        System.out.println("Sending Email Notification");
    }
}

// Concrete Notification (SMS)
class SMSNotification implements Notification {
    @Override
    public void sendNotification() {
        System.out.println("Sending SMS Notification");
    }
}

// Notification
class NotificationFactory {
    public static Notification createNotification(String channel) {
        if (channel.equalsIgnoreCase("email")) {
            return new EmailNotification();
        } else if (channel.equalsIgnoreCase("sms")) {
            return new SMSNotification();
        }
        throw new IllegalArgumentException("Unknown channel: " + channel);
    }
}


public class FactoryPatternTest {
    public static void main(String[] args) {
        Notification emailNotification = NotificationFactory.createNotification("email");
        emailNotification.sendNotification();

        Notification smsNotification = NotificationFactory.createNotification("sms");
        smsNotification.sendNotification();
    }
}
