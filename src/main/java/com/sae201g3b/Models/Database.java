/**
 * Database permet l'importation d'un .csv dans un ArrayList et ensuite son filtrage selon ce que donne l'utilisateur
 *
 * @version 2.0
 *
 * @author Alexandre Crespin
 */

package com.sae201g3b.Models;

import com.sae201g3b.Seisme;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

public class Database {
    /*Un ListProperty qui permet de stocker toutes les données du CSV*/
    private ListProperty<Seisme> data;

    //------------PARTIE IMPORTATION DES DATA--------------
    public Database(){
        /**
         * Constructeur de la class Database
         *
         * @author      Enzo Hourlay / Alexandre Crespin
         */
        data = new SimpleListProperty<>(FXCollections.observableArrayList());               /*Initialisation de data comme une SimpleListProperty*/
        ImportCSV();                                                                        /*Importation du CSV*/
    }

    /*Getters et Setters de data, dataFiltrer et data FiltrerProperty*/
    public List<Seisme> getData() {return data;}
    public void setData(ObservableList<Seisme> data) {this.data.set(data);}
    public ListProperty<Seisme> dataProperty(){return data;}

    public void ImportCSV() {
        /**
         * ImportCSV permet l'importation du .csv
         * Elle modifie data pour lui ajouté toute les seisme
         *
         *
         * @see         Seisme
         */
        /*On donne le chemin vers le fichier*/
        String cheminFichier = "src/main/resources/com/sae201g3b/SisFrance_seismes_20230604151458.csv";

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
    }

    private Seisme createSeisme(String[] valeurs) {
        /**
         * createSeisme permet de recréer les Seisme apres avoir recuperer les data
         *
         * @return      new Seisme
         * @param       String[] valeurs
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
    public void filtrer(String filtre, String filtre2, String idFiltre){
        /**
         * filtrer permet de supprimer les Seisme qui ne corresponde pas à nos filtre
         *
         * @param       String filtre : le filtre entrer par l'utilisateur (represente aussi la première date dans un inteervalle de date)
         * @param       String filtre2 : dans le cas d'un intervalle de date, represente la deuxième date
         * @param       String idFiltre : permet de spécifier quelle attribut du csv est à modifier
         *
         * @see         Seisme
         */
        /*On créer un iterator pour lire chaque Seisme de notre dataFiltrer*/
        Iterator itr = data.iterator();
        while (itr.hasNext()) {
            Seisme var = (Seisme) itr.next();
            switch (idFiltre) {
                /*Permet de verifier quelle attribut est a modifier*/
                case "Identifiant":
                    if (!Objects.equals(var.getIdentifiant(), filtre))
                        itr.remove();
                    break;

                case "Date":
                    /*Le cas des Dates est différent car nous pouvons soit ne donner qu'une date (à partir) soit donner 2 dates (de ... à ...)*/
                    if (isAvantApres(var.getDate(), filtre).equals("avant"))
                        itr.remove();
                    if (isAvantApres(var.getDate(), filtre2).equals("apres"))
                        itr.remove();
                    break;

                case "Region":
                    if (!Objects.equals(var.getRegion(), filtre))
                        itr.remove();
                    break;

                case "Intensite":
                    /*L'intensite est un RangeSlider donc il faut vérifier les valeur pour le hight poin et le low point*/
                    if (isDansIntervalleIntensite(var.getIntensite(),filtre).equals("avant"))
                        itr.remove();
                    if (isDansIntervalleIntensite(var.getIntensite(),filtre2).equals("apres"))
                        itr.remove();
                    break;
            }
        }
    }

    public String isAvantApres(String date1, String date2){
        /**
         * isAnvantApres permet de determiner si la premiere date est avant, apres ou la même que la deuxieme date
         *
         * @return      String avant si date1 avant date2 / String apres si date1 apres date2 / String egaux si date1 = date2
         * @param       String date1 : la date a verifier
         * @param       String date2 : la date de reference
         *
         */

        /*Permet d'éviter le manque de date (ex. dans la première entrer du csv il n'y a ni mois ni jour)*/
        date1 += "00/00";
        date2 += "00/00";

        /*Permet de récupérer les date en temps que int que l'on peut comparer ensuite*/
        String[] d1 = date1.split("/");
        String[] d2 = date2.split("/");
        int     AnDate1 = Integer.parseInt(d1[0]), AnDate2 = Integer.parseInt(d2[0]),
                MDate1 = Integer.parseInt(d1[1]), MDate2 = Integer.parseInt(d2[1]),
                JDate1 = Integer.parseInt(d1[2]), JDate2 = Integer.parseInt(d2[2]);

        /*On commence par les annees car c'est le plus significatif*/
        if (AnDate1 < AnDate2){
            return "avant";
        } else if (AnDate1 > AnDate2) {
            return "apres";
        } else {
            /*On passe ensuite au mois*/
            if (MDate1 < MDate2){
                return "avant";
            } else if (MDate1 > MDate2) {
                return "apres";
            } else {
                /*On passe enfin au jour*/
                if (JDate1 < JDate2){
                    return "avant";
                } else if (JDate1 > JDate2) {
                    return "apres";
                } else {
                    /*Dans le cas ou l'annee, le mois et le jour sont les même alors la date est la même*/
                    return "egaux";
                }
            }
        }
    }

    public String isDansIntervalleIntensite(String intensite1, String intensite2){
        /**
         * isDansIntervalleIntensite permet de determiner si la premiere intensite est avant, apres ou la même que la deuxieme intensite
         *
         * @return      String avant si intensite1 avant intensite2 / String apres si intensite1 apres intensite2 / String egaux si intensite1 = intensite2
         * @param       String intensite1 : l'intensite a verifier
         * @param       String intensite2 : l'intensite de reference
         *
         */

        /*On transforme les String en Float pour le calcul*/
        Float int1 = Float.parseFloat(intensite1);
        Float int2 = Float.parseFloat(intensite2);


        if (int1 < int2){
            return "avant";
        }
        else if (int1 > int2){
            return "apres";
        }
        else {
            return "egaux";
        }
    }
}
