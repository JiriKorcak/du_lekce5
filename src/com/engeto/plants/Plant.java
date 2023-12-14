package com.engeto.plants;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

public class Plant {

    private String name;
    private String notes;
    private LocalDate planted;
    private LocalDate watering;
    private int frequencyOfWatering;

    public Plant(String name, String notes, LocalDate planted, LocalDate watering, int frequencyOfWatering) {
        this.name = name;
        this.notes = notes;
        this.planted = planted;
        this.watering = watering;
        this.frequencyOfWatering = frequencyOfWatering;
    }
    public Plant(String name, LocalDate planted, int frequencyOfWatering) {
        this(name, " ", planted, LocalDate.now(), frequencyOfWatering);
    }
    public Plant(String name) {
        this(name," ", LocalDate.now(), LocalDate.now(), 7);
    }

    public Plant(String name, LocalDate planted, LocalDate watering, int frequencyOfWatering) {
        this(name, " ", planted, watering, frequencyOfWatering);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public LocalDate getPlanted() {
        return planted;
    }
    public String getPlantedCz() {
        return planted.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM));
    }

    public void setPlanted(LocalDate planted) {
        this.planted = planted;
    }

    public LocalDate getWatering() {
        return watering;
    }

    public String getWateringCz() {
        return watering.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM));
    }

    public void setWatering(LocalDate watering) throws PlantException {
        if (planted.isAfter(watering)) {
            throw new PlantException("Datum zálivky musí být starší než datum zasazení (" +
                    planted + ") - zadáno: " + watering + ".");
        }
        this.watering = watering;
    }

    public int getFrequencyOfWatering() {
        return frequencyOfWatering;
    }

    public void setFrequencyOfWatering(int frequencyOfWatering) throws PlantException {
        if (frequencyOfWatering <= 0) {
            throw new PlantException("Frekvence zálivky musí být větší než 0 - zadáno: " + frequencyOfWatering + ".");
        }
        this.frequencyOfWatering = frequencyOfWatering;
    }

    public String getNextWateringCz() {
        LocalDate nextWatering = watering.plusDays(frequencyOfWatering);
        return nextWatering.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM));
    }
    public String getWateringInfo() {
        return "Květina: " + name +
                ", poslední zálivka: " + getWateringCz() +
                ", doporučená další zálivka: " + getNextWateringCz();
    }

    @Override
    public String toString() {
        return name +", " + notes + " -" +
                " zasazena: " + getPlantedCz() +
                ", naposledy zalita: " + getWateringCz() +
                ", zalévat po " + frequencyOfWatering +
                " dnech. \n";
    }
}