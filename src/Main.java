
import com.engeto.plants.ListOfPlants;
import com.engeto.plants.Plant;
import com.engeto.plants.PlantException;

import java.time.LocalDate;


public class Main {
    public static void main(String[] args) {

        Plant plant1 = new Plant("kaktus", "píchá",
                LocalDate.of(2023,11,1),
                LocalDate.of(2023,11,8), 7);

        try {
            plant1.setFrequencyOfWatering(-1);
        } catch (PlantException e) {
            System.err.println("Chyba při změně frekvence zálivky '" +
                    plant1.getName() + "' " + e.getLocalizedMessage());
        }

        try {
            plant1.setWatering(LocalDate.of(2023, 10,28));
        } catch (PlantException e) {
            System.err.println("Chyba při změně datumu poslední zálivky '" +
                    plant1.getName() + "' " + e.getLocalizedMessage());
        }

        System.out.println(plant1.getPlanted() + "\n" + plant1.getPlantedCz());
        System.out.println(plant1.getWatering() + "\n" + plant1.getWateringCz());
        plant1.getWateringInfo();

        ListOfPlants listOfPlants1 = new ListOfPlants();

        try {
            listOfPlants1 = ListOfPlants.loadFromFile("kvetiny.txt");
        } catch (PlantException e) {
            System.err.println("Chyba při čtení ze souboru: " + e.getLocalizedMessage());
        }


        System.out.println(listOfPlants1.getListOfPlants());


//        System.out.println(listOfPlants1.getPlant(2));



        for (Plant plant : listOfPlants1.getListOfPlants()) {
            System.out.println(plant.getWateringInfo());
        }
        listOfPlants1.removePlant(2);

        Plant plant2 = new Plant ("Bazalka v kuchyni",
                LocalDate.of(2021, 9,4),
                LocalDate.of(2021, 9,4), 3);

        listOfPlants1.addPlant(plant2);

        try {
            ListOfPlants.saveToFile("listOfPlants.txt", listOfPlants1);
        } catch (PlantException e) {
            System.err.println("Chyba při zápisu do souboru : " + e.getLocalizedMessage());
        }

////  -----  Znovu načtení vytvořeného souboru -----
//
//        ListOfPlants listOfPlants2 = new ListOfPlants();
//
//        try {
//            listOfPlants2 = ListOfPlants.loadFromFile("listOfPlants.txt");
//        } catch (PlantException e) {
//            System.err.println("Chyba při čtení ze souboru: " + e.getLocalizedMessage());
//        }
//
//        System.out.println(listOfPlants2.getListOfPlants());

        listOfPlants1.sortByName();
        System.out.println("\n Seřazení podle jména: \n" + listOfPlants1.getListOfPlants());

        listOfPlants1.sortByWatering();
        System.out.println("\n Seřazení podle datat poslední zálivky: \n" + listOfPlants1.getListOfPlants());

    }
}