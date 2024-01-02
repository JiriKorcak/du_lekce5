package com.engeto.plants;

import java.util.Comparator;

public class CustomNameComparator implements Comparator<Plant> {
    @Override
    public int compare(Plant first, Plant second) {
        return first.getName().compareTo(second.getName());
    }


}
