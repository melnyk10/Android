package elements;

import interfaces.Run;
import interfaces.Wheel;

/**
 * Created by forest on 09.12.2016.
 */
public abstract class BaseCar implements Run{
    private String name;
    private int maxSpeed;
    private Wheel wheel;

    public BaseCar(String name, int maxSpeed, Wheel wheel) {
        this.name = name;
        this.maxSpeed = maxSpeed;
        this.wheel = wheel;
    }

    @Override
    public void run() {
        System.out.println("I'm driving in "+getName());
    }

    public void checkSpeed() {
        System.out.println("My max speed is: "+ getMaxSpeed()+"km/h");
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
}
