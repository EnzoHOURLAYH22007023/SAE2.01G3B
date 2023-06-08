package com.sae201g3b;

public class Seisme {
    //Classe : tous les attributs présents dans les données
    private String Identifiant;
    private String Date;
    private String Heure;
    private String Nom;
    private String Region;
    private String Choc;
    private String X;
    private String Y;
    private String Latitude;
    private String Longitude;
    private String Intensite;
    private String Qualite;

    public Seisme(String identifiant, String date, String heure, String nom, String region, String choc, String x, String y, String latitude, String longitude, String intensite, String qualite) {
        //Constructeur
        Identifiant = identifiant;
        Date = date;
        Heure = heure;
        Nom = nom;
        Region = region;
        Choc = choc;
        X = x;
        Y = y;
        Latitude = latitude;
        Longitude = longitude;
        Intensite = intensite;
        Qualite = qualite;
    }

    //setter et getter pour chaque attribut
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

    public String getRegion() {
        return Region;
    }

    public String getChoc() {
        return Choc;
    }

    public String getX() {
        return X;
    }

    public String getY() {
        return Y;
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
        //In : void
        //Out : String
        //Permet d'afficher dans la console les données de notre object
        return "Seisme{" +
                "Identifiant=" + Identifiant +
                ", Date='" + Date + '\'' +
                ", Heure='" + Heure + '\'' +
                ", Nom='" + Nom + '\'' +
                ", Région='" + Region + '\'' +
                ", Choc=" + Choc +
                ", X=" + X +
                ", Y=" + Y +
                ", Latitude=" + Latitude +
                ", Longitude=" + Longitude +
                ", Intensite=" + Intensite +
                ", Qualite='" + Qualite + '\'' +
                '}';
    }
}