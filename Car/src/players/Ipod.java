package players;

import interfaces.Player;

/**
 * Created by forest on 14.12.2016.
 */
public class Ipod implements Player {
    private String name;

    public Ipod(String name) {
        this.name = name;
    }

    @Override
    public void playMusic() {
        System.out.println("Plaing music from iPod");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
