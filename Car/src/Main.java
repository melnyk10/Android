/**
 * Created by forest on 09.12.2016.
 */
import Object.*;
public class Main {
    public static void main(String[] args) {


        Audi audi = new Audi("R8", 425, "Audi CC v2.0");
        audi.drive();
//        audi.on();
//        audi.off();
        audi.playMusic();

        System.out.println("-----------------------");

        Chevrolet chevrolet = new Chevrolet("Impala", 220);
        chevrolet.drive();
        chevrolet.checkSpeed();
        chevrolet.nitro_button();

        System.out.println("-----------------------");

        Chevrolet chevrolet1 = new Chevrolet("SS", 320, 1000);
        chevrolet1.drive();
        chevrolet1.playMusic();
        chevrolet1.nitro_button();

        
    }
}
