/**
 * Created by forest on 09.12.2016.
 */
import elements.*;
import engine.Audi_A6Turbo;
import engine.Toyota_4CF;
import players.Ipod;
import wheels.Nexen;
import wheels.Rosava;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Car> cars = new ArrayList<>();

        Nexen nexen = new Nexen("Nexen");
        Rosava rosava = new Rosava("Rosava");
        Winch winch = new Winch("Forte FPA 500", 250,12);
        Audi_A6Turbo audi_a6Turbo = new Audi_A6Turbo();
        Toyota_4CF toyota_4CF = new Toyota_4CF();
        Ipod ipod = new Ipod("Ipod Audi");


        cars.add(new LightCar("Audi R8", 320, nexen, audi_a6Turbo, ipod));
        cars.add(new Geep("Toyota Land Cruiser", 270, rosava, toyota_4CF, winch));


        for(Car car : cars){
            car.run();
        }
    }
}
