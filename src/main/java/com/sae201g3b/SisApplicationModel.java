package com.sae201g3b;

import javafx.scene.control.TextField;
import org.controlsfx.control.RangeSlider;

public class SisApplicationModel {
    private TextField id,region,de,jusqua;
    private RangeSlider intensite;
    private Database CSV = new Database();


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
        CSV.getData().clear();
        CSV.ImportCSV();
        id.clear();
        region.clear();
        de.clear();
        jusqua.clear();
    }

    public void appliquerChangement(){
        CSV.getData().clear();
        CSV.ImportCSV();
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
            filtre2 = jusqua.getText();
        }
        if (de.getText() != ""){
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
