
import com.engeto.Plant;

import java.time.LocalDate;


public class Main {
    public static void main(String[] args) {

        Plant plant1 = new Plant("kaktus", "píchá",
                LocalDate.of(2023,11,1),
                LocalDate.of(2023,11,8), 7);
        System.out.println(plant1.getPlanted() + "\n" + plant1.getPlantedCz());
        System.out.println(plant1.getWatering() + "\n" + plant1.getWateringCz());
        plant1.getWateringInfo();
    }
}