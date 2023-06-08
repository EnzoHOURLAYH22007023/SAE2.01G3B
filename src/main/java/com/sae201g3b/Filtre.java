package com.sae201g3b;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Objects;

public class Filtre {
    private static ArrayList<Seisme> CSV;
    private static ArrayList<Seisme> CSVold;

    public static void filtrer(String date1,String date2) {
        CSV = ImportationCSV.ImportCSV();
        CSVold = CSV;
        CSV = IntervalleDate(date1,date2);
        System.out.println(CSV);
    }
    public static ArrayList<Seisme> getFiltre(String filtre,String idfiltre){
        ArrayList<Seisme> cpCSV = CSV;
        Iterator itr = cpCSV.iterator();
        while (itr.hasNext()) {
            Seisme var = (Seisme) itr.next();
            if(idfiltre == "Identifiant") {
                if (!Objects.equals(var.getIdentifiant(), filtre))
                    itr.remove();
            }
            if(idfiltre == "Heure") {
                if (!Objects.equals(var.getHeure(), filtre))
                    itr.remove();
            }
            if(idfiltre == "Nom") {
                if (!Objects.equals(var.getNom(), filtre))
                    itr.remove();
            }
            if(idfiltre == "Region") {
                if (!Objects.equals(var.getRegion(), filtre))
                    itr.remove();
            }
            if(idfiltre == "Choc") {
                if (!Objects.equals(var.getChoc(), filtre))
                    itr.remove();
            }
            if(idfiltre == "X") {
                if (!Objects.equals(var.getX(), filtre))
                    itr.remove();
            }
            if(idfiltre == "Y") {
                if (!Objects.equals(var.getY(), filtre))
                    itr.remove();
            }
            if(idfiltre == "Latitude") {
                if (!Objects.equals(var.getLatitude(), filtre))
                    itr.remove();
            }
            if(idfiltre == "Longitude") {
                if (!Objects.equals(var.getLongitude(), filtre))
                    itr.remove();
            }
            if(idfiltre == "Intensite") {
                if (!Objects.equals(var.getIntensite(), filtre))
                    itr.remove();
            }
            if(idfiltre == "Qualite") {
                if (!Objects.equals(var.getQualite(), filtre))
                    itr.remove();
            }
        }
        CSV = cpCSV;
        return CSV;
    }

    public static ArrayList<Seisme> IntervalleDate (String dateDepart,String dateArrive){
        ArrayList<Seisme> cpCSV = CSV;
        Iterator itr = cpCSV.iterator();
        while (itr.hasNext()) {
            Seisme var = (Seisme) itr.next();
            if(isAvantApres(var.getDate(),dateDepart) == "avant"){
                itr.remove();
            }
            if (isAvantApres(var.getDate(),dateArrive) == "apres"){
                itr.remove();
            }
        }
        CSV = cpCSV;
        return CSV;
    }

    public static String isAvantApres(String date1, String date2){
        String[] d1 = date1.split("/");
        String[] d2 = date2.split("/");
        int AnDate1 = Integer.parseInt(d1[0]),AnDate2 = Integer.parseInt(d2[0]);

        if (AnDate1 < AnDate2){
            return "avant";
        } else if (AnDate1 > AnDate2) {
            return "apres";
        } else {
            if (MDate1 < MDate2){
                return "avant";
            } else if (MDate1 > MDate2) {
                return "apres";
            } else {
                if (JDate1 < JDate2){
                    return "avant";
                } else if (JDate1 > JDate2) {
                    return "apres";
                } else {
                    return "egaux";
                }
            }
        }
    }
    public ArrayList<Seisme> resetFiltre(){
        CSV = CSVold;
        return CSV;
    }
}
