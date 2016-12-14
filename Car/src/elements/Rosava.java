package elements;

import interfaces.Wheel;

/**
 * Created by forest on 14.12.2016.
 */
public class Rosava implements Wheel {
    private String name;

    public Rosava(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
