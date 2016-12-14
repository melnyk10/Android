/**
 * Created by forest on 09.12.2016.
 */
import elements.*;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<BaseCar> baseCars = new ArrayList<>();
        Nexen nexen = new Nexen("Nexen");
        baseCars.add(new Geep("Toyota", 350, nexen));

        for(BaseCar baseCar: baseCars){
            baseCar.run();
        }
    }
}
