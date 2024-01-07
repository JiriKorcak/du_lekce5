package com.engeto.plants;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static java.util.Comparator.comparing;

public class ListOfPlants {

    private List<Plant> listOfPlants = new ArrayList<>();

    public List<Plant> getListOfPlants() {
        return listOfPlants;
    }

    public void addPlant(Plant plant) {
        listOfPlants.add(plant);
    }

    public void removePlant (int index) {
        listOfPlants.remove(index);
    }
    public void setListOfPlants(List<Plant> listOfPlants) {
        this.listOfPlants = listOfPlants;
    }

    public Plant getPlant(int index) {
        return listOfPlants.get(index);
    }

    public static ListOfPlants loadFromFile(String filename) throws PlantException {
        ListOfPlants result = new ListOfPlants();

        try (Scanner scanner = new Scanner (new BufferedReader(new FileReader(filename)))) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                parseLine(line, result);
            }
        } catch (FileNotFoundException e) {
            throw new PlantException ("Nepodařilo se nalézt soubor " + filename + ": " + e.getLocalizedMessage());
        }
        return result;
    }

    private static void parseLine(String line, ListOfPlants listOfPlants) throws PlantException {
        String[] blocks = line.split("\t");
        int numOfBlocks = blocks.length;
        if (numOfBlocks != 5) {
            throw new PlantException(
                    "Nesprávný počet položek na řádku: " + line + "! Počet položek: " + numOfBlocks + ".");
        }
        String name = blocks[0].trim();
        String notes = blocks[1].trim();
        int frequencyOfWatering = Integer.parseInt(blocks[2].trim());
        LocalDate watering = LocalDate.parse(blocks[3].trim());
        LocalDate planted = LocalDate.parse(blocks[4].trim());

        Plant newPlant = new Plant(name, notes, planted, watering, frequencyOfWatering);
        listOfPlants.addPlant(newPlant);
    }

    public static void saveToFile(String filename, ListOfPlants listOfPlants) throws PlantException {
        try (PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(filename)))) {
            for (Plant plant : listOfPlants.getListOfPlants()) {
                writer.println(plant.getName() + Settings.getFilePlantDelimiter() +
                        plant.getNotes() + Settings.getFilePlantDelimiter() +
                        plant.getFrequencyOfWatering() + Settings.getFilePlantDelimiter() +
                        plant.getWatering() + Settings.getFilePlantDelimiter() +
                        plant.getPlanted());
            }
        } catch (IOException e) {
            throw new PlantException("Chyba při zápisu do souboru '" + filename + "' : " + e.getLocalizedMessage());
        }
    }

    public void sortByName() {
        listOfPlants.sort(comparing(Plant::getName));
    }


    public void sortByWatering() {
        listOfPlants.sort(comparing(Plant::getWatering));
    }
}
