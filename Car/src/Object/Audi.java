package Object;

import Interface.iGPS;

/**
 * Created by forest on 09.12.2016.
 */
public class Audi extends Base_Car implements iGPS {
    public Audi(String name, int speed) {
        super(name, speed);
    }

    @Override
    public void on() {
        System.out.println("GPS is enable");
    }

    @Override
    public void off() {
        System.out.println("GPS is disabled");
    }

    @Override
    public void playMusic() {
        System.out.println("Playing music from iPod");
    }
}
