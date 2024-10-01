// Singleton Logger Class
package Ei_Study.Ex1.creational_design;

class Logger {
    private static Logger instance;
    
    private Logger() { }
    
    public static Logger getInstance() {
        if (instance == null) {
            instance = new Logger();
        }
        return instance;
    }
    
    public void log(String message) {
        System.out.println("Log message: " + message);
    }
}


public class SingletonPatternTest {
    public static void main(String[] args) {
        Logger logger1 = Logger.getInstance();
        logger1.log("This is the first log message");

        Logger logger2 = Logger.getInstance();
        logger2.log("This is the second log message");

        System.out.println(logger1 == logger2);
    }
}
