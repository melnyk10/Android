package Object;

/**
 * Created by forest on 09.12.2016.
 */
public class Chevrolet extends Base_Car {
    private int nitro;

    public Chevrolet(String name, int speed) {
        super(name, speed);
    }


    public Chevrolet(String name, int speed, int nitro) {
        super(name, speed);
        this.nitro = nitro;
    }


    public void nitro_button(){
        if(nitro>0) {
            System.out.println("Nitro will work 5 seconds");
        }
        else System.out.println("U dont have a nitro");
    }

    @Override
    public void playMusic() {
        System.out.println("Playing music from cassette");
    }

}
