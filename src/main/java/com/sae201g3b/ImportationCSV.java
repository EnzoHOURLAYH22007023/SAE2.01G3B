package com.sae201g3b;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class ImportationCSV {
    private static ArrayList<Seisme> CSV;
    public static void main(String... args) {
        CSV = new ArrayList<>();
        String cheminFichier = "/amuhome/c22008838/Bureau/JavaFX/SAE2.01G3B/src/main/resources/com/sae201g3b/SisFrance_seismes_20230604151458.csv";
        try (BufferedReader br = new BufferedReader(new FileReader(cheminFichier))) {
            String ligne;
            br.readLine();

            while ((ligne = br.readLine()) != null) {
                String[] valeurs = ligne.split(",");
                Seisme seisme = createSeisme(valeurs);
                CSV.add(seisme);

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static Seisme createSeisme(String[] valeurs) {
        for (int i = 0; i < valeurs.length; ++i){
            if (valeurs[i] == ""){
                valeurs[i] = "0";
            }
        }
        Float Identifiant = parseFloatOrDefault(valeurs[0], 0.0f);
        String Date = valeurs[1];
        String Heure = valeurs[2];
        String Nom = valeurs[3];
        String Region = valeurs[4];
        String Choc = valeurs[5];
        Float X = parseFloatOrDefault(valeurs[6], 0.0f);
        Float Y = parseFloatOrDefault(valeurs[7], 0.0f);
        Float Latitude = parseFloatOrDefault(valeurs[8], 0.0f);
        Float Longitude = parseFloatOrDefault(valeurs[9], 0.0f);
        Float Intensite = parseFloatOrDefault(valeurs[10], 0.0f);
        String Qualite = valeurs[11];
        Seisme s = new Seisme(Identifiant,Date,Heure,Nom,Region,Choc,X,Y,Latitude,Longitude,Intensite,Qualite);
        getSeisme(s);
        return s;
    }
    private static Float parseFloatOrDefault(String value, Float defaultValue) {
        try {
            return Float.parseFloat(value);
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }
    private static void getSeisme(Seisme seisme){
        seisme.toString();
    }
}
