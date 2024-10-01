package Ei_Study.Ex1.behavioural_design;

import java.util.ArrayList;
import java.util.List;

// Observer Interface
interface Observer {
    void update(float temperature, float humidity);
}

// Subject Interface
interface Subject {
    void registerObserver(Observer o);
    void removeObserver(Observer o);
    void notifyObservers();
}

// Concrete Subject (Weather Station)
class WeatherStation implements Subject {
    private List<Observer> observers;
    private float temperature;
    private float humidity;

    public WeatherStation() {
        observers = new ArrayList<>();
    }

    @Override
    public void registerObserver(Observer o) {
        observers.add(o);
    }

    @Override
    public void removeObserver(Observer o) {
        observers.remove(o);
    }

    @Override
    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.update(temperature, humidity);
        }
    }

    public void setWeatherData(float temperature, float humidity) {
        this.temperature = temperature;
        this.humidity = humidity;
        notifyObservers();
    }
}

// Concrete Observer (Display)
class WeatherDisplay implements Observer {
    private String name;

    public WeatherDisplay(String name) {
        this.name = name;
    }

    @Override
    public void update(float temperature, float humidity) {
        System.out.println("Display " + name + ": Temperature = " + temperature + ", Humidity = " + humidity);
    }
}

// Test
public class ObserverPatternTest {
    public static void main(String[] args) {
        WeatherStation station = new WeatherStation();
        WeatherDisplay display1 = new WeatherDisplay("Display1");
        WeatherDisplay display2 = new WeatherDisplay("Display2");

        station.registerObserver(display1);
        station.registerObserver(display2);

        station.setWeatherData(25.3f, 65.4f);
        station.setWeatherData(30.5f, 70.0f);
    }
}
