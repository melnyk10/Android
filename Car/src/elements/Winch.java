package elements;

/**
 * Created by forest on 14.12.2016.
 */
public class Winch {
    private String name;
    private int power;
    private int lengthOfCable;

    public Winch(String name, int power, int lengthOfCable) {
        this.name = name;
        this.power = power;
        this.lengthOfCable = lengthOfCable;
    }


    public void turnOn(){

    }

    public void turnOff(){

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public int getLengthOfCable() {
        return lengthOfCable;
    }

    public void setLengthOfCable(int lengthOfCable) {
        this.lengthOfCable = lengthOfCable;
    }
}
