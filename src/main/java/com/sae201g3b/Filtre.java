package com.sae201g3b;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Objects;

public class Filtre {
    private static ArrayList<Seisme> CSV;
    private static ArrayList<Seisme> CSVold;
    private String filtre;

    public static void filtrer(String filtre) {
        CSV = ImportationCSV.ImportCSV();
        CSVold = CSV;
        CSV = getFiltre(filtre);
        System.out.println(CSV);
    }
    public static ArrayList<Seisme> getFiltre(String filtre){
        ArrayList<Seisme> cpCSV = CSV;
        Iterator itr = cpCSV.iterator();
        while (itr.hasNext()) {
            Seisme var = (Seisme) itr.next();
            if (!Objects.equals(var.getRegion(), filtre)) {
                itr.remove();
            }
        }
        CSV = cpCSV;
        return CSV;
    }

    public ArrayList<Seisme> resetFiltre(){
        CSV = CSVold;
        return CSV;
    }
}
