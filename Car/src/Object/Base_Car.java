package Object;

import Interface.iDrive;
import Interface.iPlayer;

/**
 * Created by forest on 09.12.2016.
 */
public abstract class Base_Car implements iDrive, iPlayer {
    private String name;
    private int speed;

    public Base_Car(String name, int speed) {
        this.name = name;
        this.speed = speed;
    }

    @Override
    public void drive() {
        System.out.println("I'm driving in "+getClass().getSimpleName()+" - "+getName());
    }

    public void checkSpeed() {
        System.out.println("My speed is: "+getSpeed()+"km/h");
    }

    @Override
    public void playMusic() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }
}
