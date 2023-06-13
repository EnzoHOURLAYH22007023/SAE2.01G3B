package com.sae201g3b;

/**
 * Seisme permet la reconstruction et la manipulation des seismes contenue dans le .csv et que l'on recupère
 * Dans notre récupération nous lisons ligne par ligne le .csv les donnees sont alors sans aucune reference
 * Cette class permet de retrouver cette coherence des donnees
 *
 * @version 2.0
 *
 * @see Database#ImportCSV()
 * @author Alexandre Crespin
 */


public class Seisme {
    /*Tout les attributs que l'on retrouve dans le .csv sauf pour X et Y qui ne sont pas utile pour nous*/
    private String Identifiant;
    private String Date;
    private String Heure;
    private String Nom;
    private String Region;
    private String Choc;
    private String Latitude;
    private String Longitude;
    private String Intensite;
    private String Qualite;

    public Seisme(String identifiant, String date, String heure, String nom, String region, String choc, String latitude, String longitude, String intensite, String qualite) {
        /**
         * Constructeur de la classe Seisme
         *
         */
        Identifiant = identifiant;
        Date = date;
        Heure = heure;
        Nom = nom;
        Region = region;
        Choc = choc;
        Latitude = latitude;
        Longitude = longitude;
        Intensite = intensite;
        Qualite = qualite;
    }

    /*Getters des attributs de la classe Seisme*/
    public String getIdentifiant() {
        return Identifiant;
    }
    public String getDate() {
        return Date;
    }
    public String getHeure() {
        return Heure;
    }
    public String getNom() {
        return Nom;
    }
    public String getRegion() {return Region;}
    public String getChoc() {
        return Choc;
    }
    public String getLatitude() {
        return Latitude;
    }
    public String getLongitude() {
        return Longitude;
    }
    public String getIntensite() {
        return Intensite;
    }
    public String getQualite() {
        return Qualite;
    }

    @Override
    public String toString() {
        /**
         * toString permet de visualise dans la console les résultats obtenue
         *
         * @return      String
         *
         */
        return "Seisme{" +
                "Identifiant=" + Identifiant +
                ", Date='" + Date + '\'' +
                ", Heure='" + Heure + '\'' +
                ", Nom='" + Nom + '\'' +
                ", Région='" + Region + '\'' +
                ", Choc=" + Choc +
                ", Latitude=" + Latitude +
                ", Longitude=" + Longitude +
                ", Intensite=" + Intensite +
                ", Qualite='" + Qualite + '\'' +
                '}';
    }
}