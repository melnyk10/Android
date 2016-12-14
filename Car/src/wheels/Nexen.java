package wheels;

import interfaces.Wheel;

/**
 * Created by forest on 14.12.2016.
 */
public class Nexen implements Wheel {
    private String name;

    public Nexen(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
