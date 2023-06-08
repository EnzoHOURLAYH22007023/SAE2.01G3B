/**
 * ImportationCSV permet l'importation d'un .csv dans un ArrayList
 *
 * @version 1.0
 *
 * @author Alexandre Crespin
 */

package com.sae201g3b;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Objects;

public class ImportationCSV {
    //Un ArrayList qui permet de stocker toutes les données du CSV
    private static ArrayList<Seisme> data;

    //Un ArrayList copie de data permettant d'effectuer des modification sans changer notre csv de base
    private static ArrayList<Seisme> dataFiltrer = data;

    //------------PARTIE IMPORTATION DES DATA--------------
    public static ArrayList<Seisme> ImportCSV() {
        /**
         * ImportCSV permet l'importation du .csv
         *
         * @return      rend un ArrayList contenant des Seisme -> data
         *
         * @see         Seisme
         */
        data = new ArrayList<>();

        /*On donne le chemin vers le fichier (A modif selon Window ou Linux)*/
        String cheminFichier = "C:/Users/kunai/IdeaProjects/SAE2.01G3B/src/main/resources/com/sae201g3b/SisFrance_seismes_20230604151458.csv";

        try (BufferedReader br = new BufferedReader(new FileReader(cheminFichier))) {
            String ligne;

            br.readLine();          /*On lit la première ligne, car elle n'est pas composée de données, mais des titres des attributs*/

            while ((ligne = br.readLine()) != null) {
                /*On fait une boucle dans laquelle on va lire chaque ligne et extraire les données dans l'ArrayList data*/
                String[] valeurs = ligne.split(",");
                Seisme seisme = createSeisme(valeurs);
                data.add(seisme);

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }

    private static Seisme createSeisme(String[] valeurs) {
        /**
         * createSeisme permet de recréer les Seisme apres avoir recuperer les data
         *
         * @return      new Seisme
         *
         * @see         Seisme
         */

        /*Permet de recupérer les valeurs depuis l'ArrayList sauf X et Y qui ne sont pas utile*/
        String Identifiant = valeurs[0];
        String Date = valeurs[1];
        String Heure = valeurs[2];
        String Nom = valeurs[3];
        String Region = valeurs[4];
        String Choc = valeurs[5];
        String Latitude = valeurs[8];
        String Longitude = valeurs[9];
        String Intensite = valeurs[10];
        String Qualite = valeurs[11];

        return new Seisme(Identifiant,Date,Heure,Nom,Region,Choc,Latitude,Longitude,Intensite,Qualite);
    }

    //------------PARTIE FILTRE--------------
    public static ArrayList<Seisme> filtrer(String filtre, String filtre2, String idfiltre){
        //IN : String filtre, String filtre2 (dans le cas d'une intervalle de date), String idfiltre
        //OUT : ArrayList<Seisme> dataFiltrer

        //On créer un iterator pour lire chaque Seisme de notre dataFiltrer
        Iterator itr = dataFiltrer.iterator();
        while (itr.hasNext()) {
            Seisme var = (Seisme) itr.next();
            switch (idfiltre) {
                case "Identifiant":
                    if (!Objects.equals(var.getIdentifiant(), filtre))
                        itr.remove();
                    break;
                case "Date":
                    if (isAvantApres(var.getDate(), filtre).equals("avant"))
                        itr.remove();
                    if (isAvantApres(var.getDate(), filtre2).equals("apres"))
                        itr.remove();
                    break;
                case "Heure":
                    if (!Objects.equals(var.getHeure(), filtre))
                        itr.remove();
                case "Nom":
                    if (!Objects.equals(var.getNom(), filtre))
                        itr.remove();
                    break;
                case "Region":
                    if (!Objects.equals(var.getRegion(), filtre))
                        itr.remove();
                    break;
                case "Choc":
                    if (!Objects.equals(var.getChoc(), filtre))
                        itr.remove();
                    break;
                case "Latitude":
                    if (!Objects.equals(var.getLatitude(), filtre))
                        itr.remove();
                    break;
                case "Longitude":
                    if (!Objects.equals(var.getLongitude(), filtre))
                        itr.remove();
                    break;
                case "Intensite":
                    if (!Objects.equals(var.getIntensite(), filtre))
                        itr.remove();
                    break;
                case "Qualite":
                    if (!Objects.equals(var.getQualite(), filtre))
                        itr.remove();
                    break;
            }
        }
        return dataFiltrer;
    }

    public static String isAvantApres(String date1, String date2){
        date1 += "00/00";
        date2 += "00/00";
        String[] d1 = date1.split("/");
        String[] d2 = date2.split("/");
        int AnDate1 = Integer.parseInt(d1[0]),AnDate2 = Integer.parseInt(d2[0]);
        int MDate1 = Integer.parseInt(d1[1]),MDate2 = Integer.parseInt(d2[1]);
        int JDate1 = Integer.parseInt(d1[2]),JDate2 = Integer.parseInt(d2[2]);
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
        dataFiltrer = data;
        return dataFiltrer;
    }
}
