package com.sae201g3b;

import java.util.ArrayList;

public class Filtre {
    private ArrayList<Seisme> CSV;
    private String filtre;

    public void main(String... args) {
        CSV = ImportationCSV.getCSV();
        CSV = getFiltre(filtre);
    }
    public ArrayList<Seisme> getFiltre(String filtre){

    }
}
