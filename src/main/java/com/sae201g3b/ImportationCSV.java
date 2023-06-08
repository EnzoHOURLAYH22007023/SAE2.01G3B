package com.sae201g3b;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class ImportationCSV {
    //Un Array qui permet de stocker toutes les données du CSV
    private static ArrayList<Seisme> CSV;
    public static ArrayList<Seisme> ImportCSV() {
        CSV = new ArrayList<>();

        //On donne le chemin vers le fichier
        String cheminFichier = "C:\\Users\\Massko\\Documents\\GitHub\\SAE2.01G3B/src/main/resources/com/sae201g3b/SisFrance_seismes_20230604151458.csv";

        try (BufferedReader br = new BufferedReader(new FileReader(cheminFichier))) {
            String ligne;

            //On lit la première ligne, car elle n'est pas composée de données, mais des titres des attributs
            br.readLine();

            //On fait une boucle dans laquelle on va lire chaque ligne et extraire les données dans l'ArrayList CSV
            while ((ligne = br.readLine()) != null) {
                String[] valeurs = ligne.split(",");
                Seisme seisme = createSeisme(valeurs);
                CSV.add(seisme);

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return CSV;
        //Pour affichage dans la console
        //for (Seisme seisme : CSV){
        //    System.out.println(seisme);
        //}
    }

    private static Seisme createSeisme(String[] valeurs) {
        //In : String[] valeurs
        //Out : Seisme
        //Permet de recréer les séismes après avoir récupéré les données
        Float Identifiant = parseFloatOrDefault(valeurs[0]);
        String Date = valeurs[1];
        String Heure = valeurs[2];
        String Nom = valeurs[3];
        String Region = valeurs[4];
        String Choc = valeurs[5];
        Float X = parseFloatOrDefault(valeurs[6]);
        Float Y = parseFloatOrDefault(valeurs[7]);
        Float Latitude = parseFloatOrDefault(valeurs[8]);
        Float Longitude = parseFloatOrDefault(valeurs[9]);
        Float Intensite = parseFloatOrDefault(valeurs[10]);
        String Qualite = valeurs[11];
        return new Seisme(Identifiant,Date,Heure,Nom,Region,Choc,X,Y,Latitude,Longitude,Intensite,Qualite);
    }
    private static Float parseFloatOrDefault(String valeur) {
        //In : String value
        //Out : Float
        //Permet de mettre des valeurs par default s'il n'y en a pas dans les données
        try {
            return Float.parseFloat(valeur);
        } catch (NumberFormatException e) {
            return 0.0f;
        }
    }
    private void getSeisme(Seisme seisme){
        //In : Seisme seisme
        //Out : void
        //Permet d'afficher les données d'un séisme
        seisme.toString();
    }
}
