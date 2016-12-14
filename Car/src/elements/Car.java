package elements;

import interfaces.Engine;
import interfaces.Player;
import interfaces.Wheel;

/**
 * Created by forest on 09.12.2016.
 */
public class Car implements Engine {
    protected String name;
    protected int maxSpeed;
    protected Wheel wheel;
    protected Engine engine;


    public Car(String name, int maxSpeed, Wheel wheel, Engine engine) {
        this.name = name;
        this.maxSpeed = maxSpeed;
        this.wheel = wheel;
        this.engine = engine;
    }


    public void run() {
        System.out.println("I'm driving in "+getName());
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMaxSpeed() {
        return maxSpeed;
    }

    public void setMaxSpeed(int maxSpeed) {
        this.maxSpeed = maxSpeed;
    }

    public Wheel getWheel() {
        return wheel;
    }

    public void setWheel(Wheel wheel) {
        this.wheel = wheel;
    }

    public Engine getEngine() {
        return engine;
    }

    public void setEngine(Engine engine) {
        this.engine = engine;
    }
}
