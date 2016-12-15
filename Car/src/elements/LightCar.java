package elements;

import interfaces.Engine;
import interfaces.Player;
import interfaces.Wheel;

/**
 * Created by forest on 14.12.2016.
 */
public class LightCar extends Car {
    private Player player;

    public LightCar(String name, int maxSpeed, Wheel wheel, Engine engine, Player player) {
        super(name, maxSpeed, wheel, engine);
        this.player = player;
    }

    public void playMusic(){
        System.out.println("Playing music from iPod");
    }

    @Override
    public void run() {
        super.run();
        playMusic();
    }
}
