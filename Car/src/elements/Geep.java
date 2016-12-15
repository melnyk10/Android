package elements;

import interfaces.Engine;
import interfaces.Wheel;

/**
 * Created by forest on 14.12.2016.
 */
public class Geep extends Car {
    private Winch winch;
    public Geep(String name, int maxSpeed, Wheel wheel, Engine engine, Winch winch) {
        super(name, maxSpeed, wheel, engine);
        this.winch = winch;
    }

    @Override
    public void run() {
        System.out.println("I'm driving in "+getName());

        winch.turnOn();


    }
}
