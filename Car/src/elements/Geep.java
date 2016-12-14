package elements;

import interfaces.Player;
import interfaces.Wheel;

/**
 * Created by forest on 14.12.2016.
 */
public class Geep extends BaseCar implements Player {

    public Geep(String name, int maxSpeed, Wheel wheel) {
        super(name, maxSpeed, wheel);
    }

    @Override
    public void playMusic() {

    }
}
