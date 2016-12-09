package Object;

/**
 * Created by forest on 09.12.2016.
 */
public class Ford extends Base_Car {
    public Ford(String name, int speed) {
        super(name, speed);
    }

    @Override
    public void playMusic() {
        System.out.println("Playing music from cassette");
    }
}
