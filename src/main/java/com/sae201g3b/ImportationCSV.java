package com.sae201g3b;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ImportationCSV {
    public static void main(String... args) {
        String cheminFichier = "/amuhome/c22008838/Bureau/JavaFX/SAE2.01G3B/src/main/resources/com/sae201g3b/SisFrance_seismes_20230604151458.csv";
        int i = 0;
        try (BufferedReader br = new BufferedReader(new FileReader(cheminFichier))) {
            String ligne;

            while ((ligne = br.readLine()) != null) {
                if (i == 10){return;}
                i += 1;
                String[] valeurs = ligne.split(",");
                for (String valeur : valeurs) {
                    System.out.print(valeur + " ");
                }
                System.out.println();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
