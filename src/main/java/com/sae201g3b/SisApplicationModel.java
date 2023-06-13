package com.sae201g3b;

/**
 * SisApplicationModel est une class parent des controlleur permettant de garder de façon globale les donnée provanant de la class Database
 *
 * @version 2.0
 *
 * @see com.sae201g3b.Database
 *
 * @author Alexandre Crespin
 */

import javafx.scene.control.TextField;
import org.controlsfx.control.RangeSlider;

public class SisApplicationModel {
    /*Les attribut ne sont pas lié à des FXML ici mais seulement dans les class enfants*/
    private TextField id,region,de,jusqua;
    private RangeSlider intensite;
    private Database CSV = new Database();


    /*Getter et Setter des attributs*/
    public Database getCSV() {
        return CSV;
    }
    public void setId(TextField id) {
        this.id = id;
    }
    public void setRegion(TextField region) {
        this.region = region;
    }
    public void setDe(TextField de) {
        this.de = de;
    }
    public void setJusqua(TextField jusqua) {
        this.jusqua = jusqua;
    }
    public void setIntensite(RangeSlider intensite) {
        this.intensite = intensite;
    }


    public void resetFiltreControlleur(){
        /**
         * resetFiltreControlleur permet de reinitialisé les filtre de saisie de l'utilisateur
         * en plus de reinitialiser les data en réimportant les données
         *
         */
        CSV.getData().clear();
        CSV.ImportCSV();            /*On est obligé de reimporter les données car on a eu de nombreux problème quand les données sont stockées*/
        id.clear();
        region.clear();
        de.clear();
        jusqua.clear();
    }

    public void appliquerChangement(){
        /**
         * appliquerChangement permet d'appliquer les filtres de l'utilisateur
         *
         */
        CSV.getData().clear();
        CSV.ImportCSV();        /*On est obligé de reimporter les données car on a eu de nombreux problème quand les données sont stockées*/
        String filtre = "";
        String filtre2 = "";
        String idFiltre = "";
        if (id.getText() != ""){
            filtre = id.getText();
            idFiltre = "Identifiant";
            CSV.filtrer(filtre,filtre2,idFiltre);
        }
        if (region.getText() != ""){
            filtre = region.getText().toUpperCase();
            idFiltre = "Region";
            CSV.filtrer(filtre,filtre2,idFiltre);
        }
        if (jusqua.getText() != ""){
            if (jusqua.getText().length() == 4 || jusqua.getText().length() == 7)           /*Si les dates ne sont pas marqué complétement*/
                filtre2 = jusqua.getText() + "/";
            else
                filtre2 = jusqua.getText();
        }
        if (de.getText() != ""){
            if (de.getText().length() == 4 || de.getText().length() == 7)                   /*Si les dates ne sont pas marqué complétement*/
                filtre = de.getText() + "/";
            else
                filtre = de.getText();
            idFiltre = "Date";
            CSV.filtrer(filtre,filtre2,idFiltre);
        }
        if (intensite.getHighValue() != 0){
            filtre2 = Float.toString((float) intensite.getHighValue());
        }
        if (intensite.getLowValue() != 0){
            filtre = Float.toString((float) intensite.getLowValue());
            idFiltre = "Intensite";
            CSV.filtrer(filtre,filtre2,idFiltre);
        }
    }
}
