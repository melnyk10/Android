/**
 * Created by forest on 09.12.2016.
 */
import Object.*;
public class Main {
    public static void main(String[] args) {
        Audi audi = new Audi("R8", 425);
        audi.drive();
//        audi.on();
//        audi.off();
        audi.playMusic();

        Ford ford = new Ford("SS", 320);
        ford.drive();
        ford.checkSpeed();
        ford.playMusic();
    }
}
