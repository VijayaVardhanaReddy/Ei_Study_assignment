package Ei_Study.Ex2;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;


// DeviceObserver Interface
interface DeviceObserver {
    void update(String message);
}

// SmartHomeHub Interface
interface SmartHomeHub {
    void registerDevice(DeviceObserver observer);
    void removeDevice(DeviceObserver observer);
    void notifyDevices(String message);
}

// SmartDevice Abstract Class
abstract class SmartDevice implements DeviceObserver {
    protected int id;
    protected String type;

    public SmartDevice(int id, String type) {
        this.id = id;
        this.type = type;
    }

    public abstract void turnOn();
    public abstract void turnOff();
    public abstract String getStatus();

    @Override
    public void update(String message) {
        if (message.equals("turnOn")) {
            turnOn();
        } else if (message.equals("turnOff")) {
            turnOff();
        }
    }
}

// Light Device
class Light extends SmartDevice {
    private boolean isOn;

    public Light(int id) {
        super(id, "light");
        this.isOn = false;
    }

    @Override
    public void turnOn() {
        isOn = true;
        System.out.println("Light " + id + " is turned ON.");
    }

    @Override
    public void turnOff() {
        isOn = false;
        System.out.println("Light " + id + " is turned OFF.");
    }

    @Override
    public String getStatus() {
        return "Light " + id + " is " + (isOn ? "On" : "Off");
    }
}

// Thermostat Device
class Thermostat extends SmartDevice {
    private int temperature;

    public Thermostat(int id) {
        super(id, "thermostat");
        this.temperature = 70; // Default temperature
    }

    public void setTemperature(int temperature) {
        this.temperature = temperature;
        System.out.println("Thermostat " + id + " is set to " + temperature + " degrees.");
    }

    @Override
    public void turnOn() {
        System.out.println("Thermostat " + id + " is ON.");
    }

    @Override
    public void turnOff() {
        System.out.println("Thermostat " + id + " is OFF.");
    }

    @Override
    public String getStatus() {
        return "Thermostat " + id + " is set to " + temperature + " degrees.";
    }
}

// DoorLock Device
class DoorLock extends SmartDevice {
    private boolean isLocked;

    public DoorLock(int id) {
        super(id, "door");
        this.isLocked = true;
    }

    @Override
    public void turnOn() {
        isLocked = false;
        System.out.println("Door " + id + " is Unlocked.");
    }

    @Override
    public void turnOff() {
        isLocked = true;
        System.out.println("Door " + id + " is Locked.");
    }

    @Override
    public String getStatus() {
        return "Door " + id + " is " + (isLocked ? "Locked" : "Unlocked");
    }
}

// DeviceFactory to create devices
class DeviceFactory {
    public static SmartDevice createDevice(int id, String type) {
        switch (type.toLowerCase()) {
            case "light":
                return new Light(id);
            case "thermostat":
                return new Thermostat(id);
            case "door":
                return new DoorLock(id);
            default:
                throw new IllegalArgumentException("Unknown device type.");
        }
    }
}

// Proxy for controlling access to devices
class DeviceProxy {
    private SmartDevice device;

    public DeviceProxy(SmartDevice device) {
        this.device = device;
    }

    public void turnOn() {
        // Control access here
        System.out.println("Accessing device " + device.id);
        device.turnOn();
    }

    public void turnOff() {
        // Control access here
        System.out.println("Accessing device " + device.id);
        device.turnOff();
    }

    public String getStatus() {
        return device.getStatus();
    }

    public void setTemperature(int temperature) {
        if (device instanceof Thermostat) {
            ((Thermostat) device).setTemperature(temperature);
        } else {
            System.out.println("Temperature control is not available for this device.");
        }
    }
}

// SmartHomeHub Implementation
class SmartHomeHubImpl implements SmartHomeHub {
    private List<DeviceObserver> devices = new ArrayList<>();

    @Override
    public void registerDevice(DeviceObserver observer) {
        devices.add(observer);
    }

    @Override
    public void removeDevice(DeviceObserver observer) {
        devices.remove(observer);
    }

    @Override
    public void notifyDevices(String message) {
        for (DeviceObserver device : devices) {
            device.update(message);
        }
    }

    public void executeCommand(String command) {
        notifyDevices(command);
    }
}

// Scheduler Class for scheduled tasks
class Scheduler {
    private List<Schedule> scheduleList = new ArrayList<>();

    public void addSchedule(int deviceId, String time, String command) {
        scheduleList.add(new Schedule(deviceId, time, command));
        System.out.println("Scheduled task added for device " + deviceId + " at " + time + " to " + command + ".");
    }

    public List<Schedule> getSchedules() {
        return scheduleList;
    }
}

// Schedule Class
class Schedule {
    private int deviceId;
    private String time;  // in format "HH:mm"
    private String command;

    public Schedule(int deviceId, String time, String command) {
        this.deviceId = deviceId;
        this.time = time;
        this.command = command;
    }

    public String toString() {
        return "Schedule for Device " + deviceId + " at " + time + " to " + command;
    }

    public int getDeviceId() {
        return deviceId;
    }

    public String getTime() {
        return time;
    }

    public String getCommand() {
        return command;
    }
}

// Trigger Manager to handle automated triggers
class TriggerManager {
    private List<Trigger> triggerList = new ArrayList<>();

    public void addTrigger(String condition, String action) {
        triggerList.add(new Trigger(condition, action));
        System.out.println("Trigger added: When " + condition + ", do " + action + ".");
    }

    public List<Trigger> getTriggers() {
        return triggerList;
    }
}

// Trigger Class
class Trigger {
    private String condition;
    private String action;

    public Trigger(String condition, String action) {
        this.condition = condition;
        this.action = action;
    }

    @Override
    public String toString() {
        return "When " + condition + ", then " + action;
    }

    public String getCondition() {
        return condition;
    }

    public String getAction() {
        return action;
    }
}

// StatusReport for generating reports
class StatusReport {
    private Scheduler scheduler;
    private TriggerManager triggerManager;

    public StatusReport(Scheduler scheduler, TriggerManager triggerManager) {
        this.scheduler = scheduler;
        this.triggerManager = triggerManager;
    }

    public void report(DeviceProxy lightProxy, DeviceProxy thermostatProxy, DeviceProxy doorProxy) {
        // Status of all devices
        System.out.println("\nStatus Report:");
        System.out.println(lightProxy.getStatus());
        System.out.println(thermostatProxy.getStatus());
        System.out.println(doorProxy.getStatus());
    
        // List all scheduled tasks
        List<Schedule> schedules = scheduler.getSchedules();
        if (schedules.isEmpty()) {
            System.out.println("\nNo Scheduled Tasks.");
        } else {
            System.out.println("\nScheduled Tasks:");
            for (Schedule schedule : schedules) {
                System.out.println(schedule);
            }
        }
    
        // List all triggers
        List<Trigger> triggers = triggerManager.getTriggers();
        if (triggers.isEmpty()) {
            System.out.println("No Automated Triggers.");
        } else {
            System.out.println("Automated Triggers:");
            for (Trigger trigger : triggers) {
                System.out.println(trigger);
            }
        }
    }
    
}

// Main SmartHomeSystem Class
public class SmartHomeSystem {
    private SmartHomeHubImpl homeHub = new SmartHomeHubImpl();

    public void registerDevice(DeviceObserver device) {
        homeHub.registerDevice(device);
    }

    public static void main(String[] args) {
    SmartHomeSystem homeSystem = new SmartHomeSystem();

    // Create devices
    SmartDevice light = DeviceFactory.createDevice(1, "light");
    SmartDevice thermostat = DeviceFactory.createDevice(2, "thermostat");
    SmartDevice door = DeviceFactory.createDevice(3, "door");

    // Create device proxies
    DeviceProxy lightProxy = new DeviceProxy(light);
    DeviceProxy thermostatProxy = new DeviceProxy(thermostat);
    DeviceProxy doorProxy = new DeviceProxy(door);

    // Register devices in the home system
    homeSystem.registerDevice(light);
    homeSystem.registerDevice(thermostat);
    homeSystem.registerDevice(door);

    // Create scheduler and trigger manager
    Scheduler scheduler = new Scheduler();
    TriggerManager triggerManager = new TriggerManager();
    StatusReport statusReport = new StatusReport(scheduler, triggerManager);

    // Create a scanner for user input
    Scanner scanner = new Scanner(System.in);
    boolean running = true;

    while (running) {
        try {
            System.out.println("\nSmart Home System Menu:");
            System.out.println("1. Turn on the Light");
            System.out.println("2. Turn off the Light");
            System.out.println("3. Set Thermostat Temperature");
            System.out.println("4. Lock the Door");
            System.out.println("5. Unlock the Door");
            System.out.println("6. Set a Schedule");
            System.out.println("7. Add a Trigger");
            System.out.println("8. View Status of Devices");
            System.out.println("9. Exit");
            System.out.print("Enter your choice: ");
    
            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume the newline
    
            switch (choice) {
                case 1:
                    lightProxy.turnOn();
                    break;
                case 2:
                    lightProxy.turnOff();
                    break;
                case 3:
                    System.out.print("Enter the temperature to set the thermostat: ");
                    int temp = scanner.nextInt();
                    thermostatProxy.setTemperature(temp);
                    break;
                case 4:
                    doorProxy.turnOff(); // Lock the door
                    break;
                case 5:
                    doorProxy.turnOn(); // Unlock the door
                    break;
                case 6:
                    System.out.print("Enter device ID for scheduling (1: Light, 2: Thermostat, 3: Door): ");
                    int deviceId = scanner.nextInt();
                    scanner.nextLine();  // Consume the newline
                    System.out.print("Enter time (HH:mm format): ");
                    String time = scanner.nextLine();
                    System.out.print("Enter command (Turn On / Turn Off): ");
                    String command = scanner.nextLine();
                    if (!command.equalsIgnoreCase("Turn On") && !command.equalsIgnoreCase("Turn Off")) {
                        System.out.println("Invalid command. Please enter 'Turn On' or 'Turn Off'.");
                    } else {
                        scheduler.addSchedule(deviceId, time, command);
                    }
                    break;
                case 7:
                    System.out.print("Enter condition (e.g., temperature > 75): ");
                    String condition = scanner.nextLine();
                    System.out.print("Enter action (e.g., turnOff(1)): ");
                    String action = scanner.nextLine();
                    triggerManager.addTrigger(condition, action);
                    break;
                case 8:
                    // Show status of all devices, schedules, and triggers
                    statusReport.report(lightProxy, thermostatProxy, doorProxy);
                    break;
                case 9:
                    System.out.println("Exiting Smart Home System...");
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter a number.");
            scanner.next(); // Clear the invalid input
        }
    }

    scanner.close();
}
}
