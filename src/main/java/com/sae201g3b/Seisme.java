package com.sae201g3b;

public class Seisme {
    //Classe : tout les attributs présent dans les données
    private Float Identifiant;
    private String Date;
    private String Heure;
    private String Nom;
    private String Région;
    private String Choc;
    private Float X;
    private Float Y;
    private Float Latitude;
    private Float Longitude;
    private Float Intensite;
    private String Qualite;

    public Seisme(Float identifiant, String date, String heure, String nom, String région, String choc, Float x, Float y, Float latitude, Float longitude, Float intensite, String qualite) {
        //Constructeur
        Identifiant = identifiant;
        Date = date;
        Heure = heure;
        Nom = nom;
        Région = région;
        Choc = choc;
        X = x;
        Y = y;
        Latitude = latitude;
        Longitude = longitude;
        Intensite = intensite;
        Qualite = qualite;
    }

    //setter et getter pour chaque attribut
    public Float getIdentifiant() {
        return Identifiant;
    }

    public void setIdentifiant(Float identifiant) {
        Identifiant = identifiant;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getHeure() {
        return Heure;
    }

    public void setHeure(String heure) {
        Heure = heure;
    }

    public String getNom() {
        return Nom;
    }

    public void setNom(String nom) {
        Nom = nom;
    }

    public String getRégion() {
        return Région;
    }

    public void setRégion(String région) {
        Région = région;
    }

    public String getChoc() {
        return Choc;
    }

    public void setChoc(String choc) {
        Choc = choc;
    }

    public Float getX() {
        return X;
    }

    public void setX(Float x) {
        X = x;
    }

    public Float getY() {
        return Y;
    }

    public void setY(Float y) {
        Y = y;
    }

    public Float getLatitude() {
        return Latitude;
    }

    public void setLatitude(Float latitude) {
        Latitude = latitude;
    }

    public Float getLongitude() {
        return Longitude;
    }

    public void setLongitude(Float longitude) {
        Longitude = longitude;
    }

    public Float getIntensite() {
        return Intensite;
    }

    public void setIntensite(Float intensite) {
        Intensite = intensite;
    }

    public String getQualite() {
        return Qualite;
    }

    public void setQualite(String qualite) {
        Qualite = qualite;
    }

    @Override
    public String toString() {
        //Out : String
        //Permet d'afficher dans la console les données de notre object
        return "Seisme{" +
                "Identifiant=" + Identifiant +
                ", Date='" + Date + '\'' +
                ", Heure='" + Heure + '\'' +
                ", Nom='" + Nom + '\'' +
                ", Région='" + Région + '\'' +
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